package com.king.king.util;

import com.king.king.common.enums.ErrorMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ClassPathResource;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * description: excel导出工具类.
 *
 * @author zhao_qiao_gong
 * 2019-08-14
 */
@Slf4j
public final class ExcelExportUtil {


    public static ExcelExportUtil on(Iterable<?> dataList, Class type) {
        return new ExcelExportUtil(dataList, type);
    }


    public static ExcelExportUtil on(Iterable<?> dataList, Class type, String... columns) {
        return new ExcelExportUtil(dataList, type, columns);
    }

    public static ExcelExportUtil on(Iterable<?> dataList, Class type, List<BohExportColumn> bohExportColumns) {
        return new ExcelExportUtil(dataList, type, bohExportColumns);
    }

    /**
     * excel type
     */
    private final Workbook workbook;

    /**
     * target类型
     */
    private final Class classtype;

    /**
     * 数据
     */
    private final Iterable<?> dataList;

    /**
     * 指定生成excel文件类型
     */
    private ExcelExportUtil(Iterable<?> dataList, Class cls) {
        this.workbook = new XSSFWorkbook();
        this.classtype = cls;
        this.dataList = dataList;
    }

    private ExcelExportUtil(Iterable<?> dataList, Class cls, String... columns) {
        this.workbook = new XSSFWorkbook();
        this.classtype = cls;
        this.dataList = dataList;
        this.needColumns = columns;
    }

    /**
     * boh专用导出
     **/
    private ExcelExportUtil(Iterable<?> dataList, Class cls, List<BohExportColumn> bohExportColumnsList) {
        this.workbook = new XSSFWorkbook();
        this.classtype = cls;
        this.dataList = dataList;
        this.bohExportColumnsList = bohExportColumnsList;
    }

    /**
     * default lineHeight
     */
    private static final short LINE_HEIGHT = (short) 15.625 * 22;

    /**
     * 要导出的数据列，导出的数据列按该数组排序
     * 如果该字段为空，默认导出实体类被注解的所有字段
     */
    private String[] needColumns;
    private List<BohExportColumn> bohExportColumnsList;

    /**
     * 构建excel 并输出
     *
     * @return
     */
    public byte[] buildExcel() {
        byte[] bytes = new byte[0];
        InputStream inputStream = null;
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            fillingSheet(0);
            this.workbook.write(outputStream);
            inputStream = new ByteArrayInputStream(outputStream.toByteArray());
            bytes = new byte[inputStream.available()];
            int read = inputStream.read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) inputStream.close();
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    public void buildExcel(HttpServletResponse response, String fileName) throws UnsupportedEncodingException {
        buildExcel(response, fileName, 0);
    }

    public void buildExcel(HttpServletResponse response, String fileName, int timeZone) throws UnsupportedEncodingException {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=\"" +
            java.net.URLEncoder.encode(fileName, "UTF-8") + ".xlsx" + "\" ");
        //response.setContentType("application/binary;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            //如果前端设置了标题输出参数  就按照前端给的逻辑输出.
            if (this.bohExportColumnsList != null) {
                bohFillingSheet(0, timeZone);
            } else {//否则就按照 bean类的注解配置输出
                fillingSheet(0, timeZone);
            }
//            fillingSheet(0);
            outputStream = response.getOutputStream();
           // String fn = new String(fileName.getBytes(), StandardCharsets.UTF_8);
//            response.setHeader("Content-disposition", "attachment; filename=" + fn + ".xlsx");
//            response.setContentType("application/vnd.ms-excel");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            this.workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @SuppressWarnings("unchecked")
    private List<ExcelFieldMethod> sortMethod() {
        List<ExcelFieldMethod> methodList = new ArrayList<>();
        Field[] fields = this.classtype.getDeclaredFields();
        String fieldName;
        StringBuilder fieldNameG;
        Method method;
        if (needColumns == null || needColumns.length == 0) {
            List<String> columns = new ArrayList<>();
            for (Field field : fields) {
                if (field.getAnnotation(ExcelField.class) != null) {
                    fieldName = field.getName();
                    fieldNameG = new StringBuilder(fieldName);
                    fieldNameG.setCharAt(0, (char) (fieldNameG.charAt(0) - (32)));
                    fieldNameG.insert(0, "get");
                    try {
                        method = this.classtype.getDeclaredMethod(fieldNameG.toString());
                        ExcelFieldMethod excelFieldMethod = new ExcelFieldMethod();
                        excelFieldMethod.setMethod(method);
                        excelFieldMethod.setField(field);
                        methodList.add(excelFieldMethod);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    columns.add(field.getAnnotation(ExcelField.class).title());
                }
            }
            needColumns = columns.toArray(new String[0]);
        } else {
            for (int i = 0; i < needColumns.length; i++) {
                for (Field field : fields) {
                    if (StringUtils.isNotBlank(needColumns[i]) && field.getAnnotation(ExcelField.class) != null && needColumns[i].equals(field.getAnnotation(ExcelField.class).title()) && field.getAnnotation(ExcelField.class).isShow()) {
                        fieldName = field.getName();
                        fieldNameG = new StringBuilder(fieldName);
                        fieldNameG.setCharAt(0, (char) (fieldNameG.charAt(0) - (32)));
                        fieldNameG.insert(0, "get");
                        try {
                            method = this.classtype.getDeclaredMethod(fieldNameG.toString());
                            ExcelFieldMethod excelFieldMethod = new ExcelFieldMethod();
                            excelFieldMethod.setMethod(method);
                            excelFieldMethod.setField(field);
                            methodList.add(excelFieldMethod);
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                }
            }
        }
        return methodList;
    }


    /***boh专用***/
    @SuppressWarnings("unchecked")
    private List<ExcelFieldMethod> bohSortMethod() {
//        this.bohExportColumnsList;
//        this.dataList;

        //bohExportColumnsList 前端出入的导出字段对象List
        List<ExcelFieldMethod> methodList = new ArrayList<>();
        Field[] fields = this.classtype.getDeclaredFields();
        String fieldName;
        StringBuilder fieldNameG;
        Method method;

        for (BohExportColumn bohExportColumn : bohExportColumnsList) {
            for (Field field : fields) {
                field.getName();
                if (StringUtils.isNotBlank(bohExportColumn.getDataIndex())
                    && field.getAnnotation(ExcelField.class) != null
                    && bohExportColumn.getDataIndex().equals(field.getName())
                    && field.getAnnotation(ExcelField.class).isShow()) {

                    fieldName = field.getName();
                    fieldNameG = new StringBuilder(fieldName);
                    fieldNameG.setCharAt(0, (char) (fieldNameG.charAt(0) - (32)));
                    fieldNameG.insert(0, "get");

                    try {
                        method = this.classtype.getDeclaredMethod(fieldNameG.toString());
                        ExcelFieldMethod excelFieldMethod = new ExcelFieldMethod();
                        excelFieldMethod.setMethod(method);
                        excelFieldMethod.setField(field);
                        methodList.add(excelFieldMethod);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
        return methodList;
    }

    private void bohFillingSheet(int startRow) {
        bohFillingSheet(startRow, 0);
    }

    /***boh 专用**/
    private void bohFillingSheet(int startRow, int timeZone) {
        Sheet sheet = workbook.createSheet("Sheet1");
        sheet.setDefaultRowHeight(LINE_HEIGHT);
        List<ExcelFieldMethod> methods = bohSortMethod();
        //填充title行
        Row titleRow = sheet.createRow(startRow);
        Cell titleCell;
        titleRow.setHeight((short) (15.625 * 25));
        //输出标题
        for (int i = 0; i < bohExportColumnsList.size(); i++) {
            titleCell = titleRow.createCell(i);
            titleCell.setCellValue(bohExportColumnsList.get(i).getTitle());
            titleCell.setCellStyle(titleStyle());
            int width = bohExportColumnsList.get(i).getTitle().getBytes().length * 256;
            sheet.setColumnWidth(i, width);
        }

        //填充list中的数据
        Cell normalCell;
        // StringBuilder cellValue;
        for (Object obj : dataList) {
            startRow++;
            Row normalRow = sheet.createRow(startRow);
            normalRow.setHeight(LINE_HEIGHT);
            for (int j = 0; j < methods.size(); j++) {
                normalCell = normalRow.createCell(j);
                try {
                    Object val = methods.get(j).getMethod().invoke(obj);
                    Field field = methods.get(j).getField();
                    //cellValue = new StringBuilder();
                    // 时间类型时区处理
                    Type type = field.getGenericType();
                    if (type instanceof Class<?>) {
                        Class<?> cls = (Class<?>) type;
                        if (Instant.class.isAssignableFrom(cls)) {
                            Instant i = Instant.parse(val.toString());
                            // 为了避免因其它导入模块，没有timezone的情况时，默认的0时区将 时区的格式删除，造成数据不准确，else特别控制
                            if (timeZone > 0) {
//                                System.out.println(i.minusMillis(Math.abs(timeZone)* 60*60*1000));
                                val = i.minusMillis(Math.abs(timeZone) * 60 * 60 * 1000);
                            } else if (timeZone < 0) {
//                                System.out.println(i.plusMillis(Math.abs(timeZone)* 60*60*1000));
                                val = i.plusMillis(Math.abs(timeZone) * 60 * 60 * 1000);
                            }

                            val = val.toString().replaceAll("T", " ").replaceAll("Z", " "); // 暴力去除 时区的标识
                        }
                    }
                    cellValueDate(normalCell, val, field);

                } catch (Exception e) {
                    log.error(ErrorMessageEnum.NG_EXPORT_EXCEL.getDefaultText(), e);
                    throw new EdpOpException(null, ErrorMessageEnum.NG_EXPORT_EXCEL.getDefaultText());
                }

            }
        }

    }


    private void fillingSheet(int startRow) {
        fillingSheet(startRow, 0);
    }

    /**
     * 填充sheet数据
     *
     * @param startRow
     * @throws Exception
     */
    private void fillingSheet(int startRow, int timeZone) {
        Sheet sheet = workbook.createSheet("Sheet1");
        sheet.setDefaultRowHeight(LINE_HEIGHT);
        List<ExcelFieldMethod> methods = sortMethod();
        //填充title行
        Row titleRow = sheet.createRow(startRow);
        Cell titleCell;
        titleRow.setHeight((short) (15.625 * 25));
        for (int i = 0; i < needColumns.length; i++) {
            titleCell = titleRow.createCell(i);
            titleCell.setCellValue(needColumns[i]);
            titleCell.setCellStyle(titleStyle());
            int width = needColumns[i].getBytes().length * 256;
            sheet.setColumnWidth(i, width);
        }


        //填充list中的数据
        Cell normalCell;
        // StringBuilder cellValue;
        for (Object obj : dataList) {
            startRow++;
            Row normalRow = sheet.createRow(startRow);
            normalRow.setHeight(LINE_HEIGHT);
            for (int j = 0; j < methods.size(); j++) {
                normalCell = normalRow.createCell(j);
                try {

                    Object val = methods.get(j).getMethod().invoke(obj);
                    Field field = methods.get(j).getField();
                    // 时间类型时区处理
                    Type type = field.getGenericType();
                    if (type instanceof Class<?>) {
                        Class<?> cls = (Class<?>) type;
                        if (Instant.class.isAssignableFrom(cls)) {
                            Instant i = Instant.parse(val.toString());
                            // 为了避免因其它导入模块，没有timezone的情况时，默认的0时区将 时区的格式删除，造成数据不准确，else特别控制
                            if (timeZone > 0) {
//                                System.out.println(i.minusMillis(Math.abs(timeZone)* 60*60*1000));
                                val = i.minusMillis(Math.abs(timeZone) * 60 * 60 * 1000);
                            } else if (timeZone < 0) {
//                                System.out.println(i.plusMillis(Math.abs(timeZone)* 60*60*1000));
                                val = i.plusMillis(Math.abs(timeZone) * 60 * 60 * 1000);
                            }

                            val = val.toString().replaceAll("T", " ").replaceAll("Z", " "); // 暴力去除 时区的标识
                        }
                    }
                    cellValueDate(normalCell, val, field);
                    //cellValue = new StringBuilder();
                    // 设置表体样式


                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
    }

    /**
     * 根据实体的注解类型,来控制excel文件列的类型格式
     **/
    private void cellValueDate(Cell normalCell, Object val, Field field) {
        if (val == null) {
            //为sheet单元格设置值normalCell.setCellValue("");
        } else {
            //获取字段类型 转换百分比 钱 和日期的格式
            Annotation a = field.getAnnotation(ExcelField.class);
            if (((ExcelField) a).fieldtype().equals(ExcleFieldTypeEnum.PERCENTAGE)) {//百分比
                normalCell.setCellType(CellType.NUMERIC);
                normalCell.setCellValue((Double.valueOf(val.toString()) * 100) + "%");
            } else if (((ExcelField) a).fieldtype().equals(ExcleFieldTypeEnum.MONEY)) {//钱

                normalCell.setCellType(CellType.NUMERIC);
                normalCell.setCellValue(Double.valueOf(val.toString()));
                CellStyle cellStyle = workbook.createCellStyle();
                DataFormat format = workbook.createDataFormat();
                // cellStyle.setDataFormat(format.getFormat("#,###.00"));

                cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));

                normalCell.setCellStyle(cellStyle);
            } else if (((ExcelField) a).fieldtype().equals(ExcleFieldTypeEnum.DATE)) {//日期
                if (val.getClass().equals(Instant.class)) {
                    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss").withZone(ZoneId.systemDefault());
                    normalCell.setCellValue(dateFormat.format((Instant) val));
                } else {
                    normalCell.setCellValue(val.toString());
                }
            } else {
                normalCell.setCellValue(val.toString());
            }
        }
    }

//    /**
//     * map转excel
//     *
//     * @param mapList
//     * @param startRow
//     */
//    private void fillingSheetMap(List<Map<String, Object>> mapList, int startRow) {
//        Sheet sheet = workbook.createSheet("Sheet1");
//        if (mapList == null || mapList.size() == 0) {
//            return;
//        }
//        sheet.setDefaultRowHeight(LINE_HEIGHT);
//        //填充title行
//        Row titleRow = sheet.createRow(startRow);
//        Cell titleCell;
//        titleRow.setHeight((short) (15.625 * 25));
//        if (needColumns == null || needColumns.length == 0) {
//            Map<String, Object> map1 = mapList.get(0);
//            Set<Map.Entry<String, Object>> set1 = map1.entrySet();
//            List<String> titleList = new ArrayList<>();
//            set1.forEach(e -> {
//                titleList.add(e.getKey());
//            });
//            needColumns = new String[titleList.size()];
//            titleList.toArray(needColumns);
//        }
//        for (int i = 0; i < needColumns.length; i++) {
//            titleCell = titleRow.createCell(i);
//            titleCell.setCellStyle(titleStyle());
//            titleCell.setCellValue(needColumns[i]);
//            int width = needColumns[i].getBytes().length * 256;
//            sheet.setColumnWidth(i, width);
//        }
//        //填充list中的数据
//        Row normalRow;
//        Cell normalCell;
//        StringBuilder cellValue = new StringBuilder();
//        for (int i = 0; i < mapList.size(); i++) {
//            normalRow = sheet.createRow(startRow + 1 + i);
//            normalRow.setHeight(LINE_HEIGHT);
//            for (int j = 0; j < needColumns.length; j++) {
//                normalCell = normalRow.createCell(j);
//                Object val = mapList.get(i).get(needColumns[j]);
//                //如果得到的值为空，若要对值进行格式化也可以在这操作
//                if (val == null) {
//                    cellValue = new StringBuilder();
//                } else {
//                    cellValue = new StringBuilder(val.toString());
//                    // 设置表体样式
//                    makeColumnWidth(cellValue.toString(), j, sheet);
//                }
//                //为sheet单元格设置值
//                normalCell.setCellValue(cellValue.toString());
//            }
//        }
//    }

    /**
     * 设置title样式
     */
    private CellStyle titleStyle() {
        CellStyle titleStyle = workbook.createCellStyle();
        // 设置表头样式
        Font titleFont = workbook.createFont();
        //粗体显示
        titleFont.setBold(true);
        titleFont.setFontHeightInPoints((short) 12);
        titleFont.setFontName("宋体");
        String dateStyle = "yyyy-MM-dd'T'HH:mm:ss";
        DataFormat dataFormat = workbook.createDataFormat();
        //设置字体
        titleStyle.setFont(titleFont);
        // 是否换行
        titleStyle.setWrapText(false);
        //设置日期格式
        titleStyle.setDataFormat(dataFormat.getFormat(dateStyle));
        titleStyle.setFont(titleFont);
        return titleStyle;
    }

    /**
     * 设置宽度
     **/
    private void makeColumnWidth(String columnValue, Integer columnIndex, Sheet sheet) {
        // 单据号Byte长度控制
        if (columnIndex == 0) {
            sheet.setColumnWidth(columnIndex, 4608);
        }
        if (columnValue.getBytes().length * 256 > sheet.getColumnWidth(columnIndex) && columnIndex != 0) {
            if (columnValue.getBytes().length > 255) {
                sheet.setColumnWidth(columnIndex, 255 * 256);
            } else {
                sheet.setColumnWidth(columnIndex, columnValue.getBytes().length * 256);
            }
        }
    }


//    /***niuchen web导出方法封装
//     * filename 文件名
//     * response 导出流
//     * list 导出集合
//     * type  导出集合类型
//     * ***/
//    public static void exportUtil(String filename, HttpServletResponse response, Iterable<?> list, Class type)   {
//        byte[] bytes = ExportExcelUtil.on(list, type).buildExcel();
//        response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
//        response.addHeader("Content-Length", "" + bytes.length);
//        response.setContentType("application/vnd.ms-excel");
//        response.setCharacterEncoding("utf-8");
//        try {
//            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
//            toClient.write(Base64.getEncoder().encode(bytes));
//            toClient.flush();
//            toClient.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }

    /***niuchen web导出方法封装
     * filename 文件名
     * response 导出流
     * list 导出集合
     * type  导出集合类型
     * ***/
    public static void exportUtil(String filename, HttpServletResponse response, Iterable<?> list, Class type) throws IOException {
        response.setContentType("application/force-download");
        response.setHeader("Content-Disposition", "attachment;filename=\"" +
            java.net.URLEncoder.encode(filename, "UTF-8") + ".xlsx" + "\" ");
        // wb.write(response.getOutputStream());
        byte[] bytes = ExcelExportUtil.on(list, type).buildExcel();
        response.getOutputStream().write(bytes);
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

    /**
     * excel 模板 通用下载
     *
     * @param response 响应
     * @param filePath 文件相对路径  eg: templates/boh  (完整文件相对路径为 templates/boh/boh.xlsx)
     * @param fileName 文件名(带格式) eg: boh.xlsx
     */
    public static void exportTemplateUtil(HttpServletResponse response, String filePath, String fileName) {
        ClassPathResource classPathResource = new ClassPathResource(filePath + File.separator + fileName);
        InputStream inputStream = null;
        ServletOutputStream outputStream = null;
        try {
            inputStream = classPathResource.getInputStream();
            outputStream = response.getOutputStream();

            String fn = new String(fileName.getBytes(), StandardCharsets.UTF_8);
            response.setHeader("Content-disposition", "attachment; filename=" +  java.net.URLEncoder.encode(fn, "UTF-8") + ".xlsx");
            response.setContentType("application/force-download");
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            Workbook workbook = new XSSFWorkbook(inputStream);
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.flush();
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}

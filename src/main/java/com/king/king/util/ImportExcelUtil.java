package com.king.king.util;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * 导入Excel封装
 *
 * @author Danfeng
 * @since 2018/8/8
 */
public class ImportExcelUtil {
    private static final Logger log = LoggerFactory.getLogger(ImportExcelUtil.class);

    private static final String XLS = ".xls";
    private static final String XLSX = ".xlsx";

    /**
     * 待转换文件
     */
    private MultipartFile multipartFile;

    /**
     * 目标类
     */
    private Class<?> cls;

    /**
     * 表格
     */
    private List<Sheet> sheets;

    /**
     * 表头
     * key 为列号，value为值
     */
    private List<Map<Integer, String>> titles;

    public ImportExcelUtil(MultipartFile multipartFile, Class<?> cls) {
        this.cls = cls;
        this.multipartFile = multipartFile;
        this.sheets = new ArrayList<>();
        this.titles = new ArrayList<>();
        readTitle();
    }

    public List<?> excute() throws IllegalArgumentException {
        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new RuntimeException("file is null");
        }
        if (cls == null) {
            throw new RuntimeException("target class can not to be null");
        }
        List<Object> results = new ArrayList<>();
        Field[] fields = cls.getDeclaredFields();
        this.sheets.forEach(sheet -> {
            sheet.removeRow(sheet.getRow(0));
            Iterator<Row> rowIterator = sheet.rowIterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                try {
                    Object obj = cls.newInstance();
                    AtomicInteger containTitleFlag = new AtomicInteger();
                    Stream.of(fields).forEach(field -> {
                        field.setAccessible(true);
                        ExcelField annotation = field.getAnnotation(ExcelField.class);
                        if (null != annotation) {
                            String title = annotation.title();
                            titles.forEach(item -> {
                                Set<Map.Entry<Integer, String>> entries = item.entrySet();
                                for (Map.Entry<Integer, String> entry : entries) {
                                    if (entry.getValue().equals(title)) {
                                        containTitleFlag.addAndGet(1);
                                        Cell cell = row.getCell(entry.getKey());
                                        String value = getCellValue(cell);
                                        // if (annotation.isRequired() && StringUtils.isEmpty(value)) {
                                        //    throw new IllegalArgumentException("第" + (row.getRowNum() + 1) + "行" + "第" + (entry.getKey() + 1) + "列不能为空");
                                        // }
                                        try {
                                            String fieldName = field.getName();
                                            String fldtype = field.getType().getSimpleName();
                                            String setMethod = pareSetName(fieldName);
                                            Method method = cls.getMethod(setMethod, field.getType());
                                            method.setAccessible(true);
                                            if (!StringUtils.isEmpty(value)) {
                                                try {
                                                    if ("String".equals(fldtype)) {
                                                       // if (value.indexOf("E") != -1 || value.indexOf(".") != -1 ||value.indexOf("e") != -1) {
                                                         //   BigDecimal code = new BigDecimal(value);
                                                        //    value = code.toString();
                                                       // }
                                                        method.invoke(obj, value);
                                                    } else if ("Date".equals(fldtype)) {
                                                        Date temp = parseDate(value);
                                                        method.invoke(obj, temp);
                                                    } else if ("Integer".equals(fldtype) || "int".equals(fldtype)) {
                                                        Double temp = Double.parseDouble(value);
                                                        method.invoke(obj, temp.intValue());
                                                    } else if ("Long".equalsIgnoreCase(fldtype)) {
                                                        Double temp = Double.parseDouble(value);
                                                        method.invoke(obj, temp.longValue());
                                                    } else if ("Float".equalsIgnoreCase(fldtype)) {
                                                        Float f = Float.parseFloat(value);
                                                        method.invoke(obj, f);
                                                    } else if ("Double".equalsIgnoreCase(fldtype)) {
                                                        Double temp = Double.parseDouble(value);
                                                        method.invoke(obj, temp);
                                                    } else if ("Boolean".equalsIgnoreCase(fldtype)) {
                                                        Boolean temp = Boolean.parseBoolean(value);
                                                        method.invoke(obj, temp);
                                                    } else if ("LocalDate".equals(fldtype)) {
                                                        LocalDate temp = LocalDate.parse(value);
                                                        method.invoke(obj, temp);
                                                    } else if ("LocalDateTime".equals(fldtype)) {
                                                        LocalDateTime temp = LocalDateTime.parse(value);
                                                        method.invoke(obj, temp);
                                                    } else {
                                                        log.debug("not supper type" + fldtype);
                                                    }
                                                } catch (NumberFormatException | DateTimeParseException e) {
                                                    e.printStackTrace();
                                                    // throw new IllegalArgumentException("第" + (row.getRowNum() + 1) + "行" + "第" + (cell.getColumnIndex() + 1) + "列录入内容格式错误！请录入后重新上传！");
                                                }
                                            }
                                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            });
                        }
                    });
                    if (containTitleFlag.get() > 0) {
                        // 设置行号
                        Method method = cls.getMethod("setLineNo", Integer.class);
                        method.setAccessible(true);
                        method.invoke(obj, row.getRowNum() + 1);
                        results.add(obj);
                    } else {
                        throw new IllegalArgumentException("请上传正确的模版文件");
                    }
                } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
        return results;
    }

    private void readTitle() {
        String originalFilename = multipartFile.getOriginalFilename();
        try {
            InputStream inputStream = multipartFile.getInputStream();
            Workbook workBook;
            if (originalFilename.endsWith(XLS)) {
                workBook = new HSSFWorkbook(inputStream);
            } else if (originalFilename.endsWith(XLSX)) {
                workBook = new XSSFWorkbook(inputStream);
            } else {
                throw new IllegalArgumentException("文件格式不正确！");
            }
            Iterator<Sheet> sheetIterator = workBook.sheetIterator();
            while (sheetIterator.hasNext()) {
                Sheet sheet = sheetIterator.next();
                sheets.add(sheet);
                int lastRowNum = sheet.getLastRowNum();
                if (lastRowNum > 0) {
                    Row row = sheet.getRow(0);
                    Iterator<Cell> cellIterator = row.cellIterator();
                    while (cellIterator.hasNext()) {
                        Cell cell = cellIterator.next();
                        Map<Integer, String> map = new HashMap<>(1);
                        String cellValue = getCellValue(cell);
                        if (!StringUtils.isEmpty(cellValue)) {
                            map.put(cell.getColumnIndex(), cellValue);
                            titles.add(map);
                        } else {
                            throw new IllegalArgumentException("表头名称不能为空!");
                        }
                    }
                } else {
                    throw new IllegalArgumentException("上传表格数据为空，请录入数据后重新上传！");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String getCellValue(Cell cell) {
        String strCell = "";
        if (cell != null) {
            switch (cell.getCellTypeEnum()) {
                case NUMERIC: {
                    BigDecimal code = new BigDecimal(cell.getNumericCellValue());
                    strCell = String.valueOf(code);
                    break;
                }
                case STRING: {
                    strCell = cell.getStringCellValue();
                    break;
                }
                case FORMULA: {
                    strCell = cell.getCellFormula();
                    break;
                }
                case BOOLEAN: {
                    strCell = String.valueOf(cell.getBooleanCellValue());
                    break;
                }
                case ERROR: {
                    strCell = String.valueOf(cell.getErrorCellValue());
                    break;
                }
                default:
                    break;
            }
            return strCell;
        } else {
            return "";
        }
    }

    /**
     * 拼接某属性set 方法
     *
     * @param fldname
     * @return
     */
    private String pareSetName(String fldname) {
        if (null == fldname || "".equals(fldname)) {
            return null;
        }
        String pro = "set" + fldname.substring(0, 1).toUpperCase() + fldname.substring(1);
        return pro;
    }

    /**
     * 格式化string为Date
     *
     * @param datestr
     * @return date
     */
    private Date parseDate(String datestr) {
        if (null == datestr || "".equals(datestr)) {
            return null;
        }
        try {
            String fmtstr;
            if (datestr.indexOf(':') > 0) {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            } else {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr);
            return sdf.parse(datestr);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

}

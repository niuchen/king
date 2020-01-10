package com.king.king.api.mapper;


import com.king.king.api.controller.po.AttachPayload;
import com.king.king.api.controller.po.AttachQuery;
import com.king.king.api.enty.PbAttach;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @Auther: shuaiyin.shang
 * @Date: 2018/6/20
 * @Description: 附件Mapper
 */
public interface AttachMapper {

    class SqlBuilder extends SQL {

        public static final String UPDATE = "update";

        public String update(AttachPayload attachPayload){
            UPDATE("PB_ATTACH");
            SET("ENTRY_ID=#{entryId}");
            SET("ENTRY_CLS=#{entryCls}");
            WHERE("ID=#{id}");
            return toString();
        }

        public static final String INSERT = "insert";

        public String insert(PbAttach pbAttach){
            INSERT_INTO("PB_ATTACH");
            VALUES("ENTRY_CLS", "#{entryCls}");
            VALUES("ENTRY_ID", "#{entryId}");
            VALUES("ATTACH_NAME", "#{attachName}");
            VALUES("ATTACH_TYPE2", "#{attachType2}");
            VALUES("ATTACH_TYPE3", "#{attachType3}");
            VALUES("ATTACH_DATA_TYPE", "#{attachDataType}");
            VALUES("ATTACH_PATH", "#{attachPath}");
            VALUES("UPLOAD_TIME", "#{uploadTime}");
            VALUES("OU_CODE", "#{ouCode}");
            return toString();
        }

        public static final String DELETE_BY_ID = "deleteById";

        public String deleteById(Long id){
            UPDATE("PB_ATTACH");
            SET("DELETE_FLAG=1");
            WHERE("ID=#{id}");
            return toString();
        }

        public static final String FIND_ALL_BY_EID_CLASS = "findAllByEidClass";

        public String findAllByEidClass(AttachQuery attachQuery){
            SELECT_ALL();
            FROM_SQL();
            WHERE(" T.ENTRY_ID = #{entryId}");
            WHERE(" T.ENTRY_CLS = #{entryCls}");
            WHERE_DELETEFLAG_SQL();
            if (!StringUtils.isEmpty(attachQuery.getLimit())) {
                ORDER_BY("CREATE_TIME DESC LIMIT #{limit}");
            }
            return toString();
        }

        public static final String FIND_BY_ID = "findById";

        public String findById(Long id){
            SELECT_ALL();
            FROM_SQL();
            WHERE(" ID = #{id}");
            WHERE_DELETEFLAG_SQL();
            return toString();
        }
        private void SELECT_ALL() {
            SELECT(" T.ID  id");
            SELECT(" T.ENTRY_ID  entryId");
            SELECT(" T.ENTRY_CLS  entryCls");
            SELECT(" T.ATTACH_TYPE2  attachType2");
            SELECT(" T.ATTACH_TYPE3  attachType3");
            SELECT(" T.ATTACH_NAME  attachName");
            SELECT(" T.ATTACH_DATA_TYPE  attachDataType");
            SELECT(" T.ATTACH_PATH  attachPath");
            SELECT(" T.UPLOAD_TIME  uploadTime");
            SELECT(" T.REMARK  remark");
            SELECT(" T.OU_CODE  ouCode");
        }

        private void FROM_SQL() {
            FROM("PB_ATTACH T");
        }

        private void WHERE_DELETEFLAG_SQL() {
            WHERE("(T.DELETE_FLAG <> 1 OR T.DELETE_FLAG IS NULL)");
        }


        static final String FIND_ALL_SQL = "findAllSql";

        public String findAllSql() {
            SELECT_ALL();
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            return toString();
        }


        public String findCountSql(AttachQuery query) {
            SELECT(" COUNT(1) n ");
            FROM_SQL();
            WHERE_DELETEFLAG_SQL();
            return toString();
        }


    }

    /**
     * 查询全部附件
     *
     * @return
     */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_ALL_SQL)
    List<PbAttach> findAll();
    /*
     * 通过附件类型和所属类型id查询
     * */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_ALL_BY_EID_CLASS)
    List<PbAttach> findAllByEidClass(AttachQuery attachQuery);
    /*
    * 主键id查询
    * */
    @SelectProvider(type = SqlBuilder.class, method = SqlBuilder.FIND_BY_ID)
    PbAttach findById(Long id);

    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.DELETE_BY_ID)
    void deleteById(Long id);

    @UpdateProvider(type = SqlBuilder.class, method = SqlBuilder.UPDATE)
    void update(AttachPayload attachPayload);

    @InsertProvider(type = SqlBuilder.class, method = SqlBuilder.INSERT)
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="ID")
    void insert(PbAttach pbAttach);



    @Update({
        "update  PB_ATTACH pa set   pa.DELETE_FLAG = 1 ",
        "where ENTRY_CLS = #{entryCls} and IFNULL(DELETE_FLAG,0)  !=1 and ENTRY_ID=#{entryId}"
    })
    int deleteByClsId(String entryCls, Long entryId);
}

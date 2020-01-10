package com.king.king.api.controller.vo;


import com.king.king.util.SqlUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.apache.ibatis.jdbc.SQL;

import java.io.Serializable;
import java.util.Set;

@Data
@ToString(callSuper = true)
/***用户数据权限对象niuchen 20191009**/
public class PermssionsUserOjb implements Serializable {
    private static final long serialVersionUID = -2976531815901679125L;
    @ApiModelProperty(value = "管理的客户集合 空为全部")
    private Set<String> custList;
    @ApiModelProperty(value = "管理的产品线集合 空为全部")
    private Set<String> PlList;
    @ApiModelProperty(value = "管理的用户集合 空为全部")
    private Set<Long> userList;

    /****一个快速设置查询条件的工具方法
     * tableAs  主表别名**/
    public void permssionsSql(String tableAs, SQL sql) {
        if ((this.getCustList() != null && this.getCustList().size() > 0)
            && (this.getPlList() != null && this.getPlList().size() > 0)) {
            sql.WHERE("(" + tableAs + ".CUST_CODE IN " + SqlUtil.toSqlStringSet(this.getCustList())
                + " and (" + tableAs + ".PL_CODE in " + SqlUtil.toSqlStringSet(this.getPlList()) + " OR  " + tableAs + ".PL_CODE IS NULL  OR " + tableAs + ".PL_CODE = '')) "
            );
        } else {
            if (this.getCustList() != null && this.getCustList().size() > 0) {
                sql.WHERE(tableAs + ".CUST_CODE IN " + SqlUtil.toSqlStringSet(this.getCustList()));
            }
            if (this.getPlList() != null && this.getPlList().size() > 0) {
                sql.WHERE("(" + tableAs + ".PL_CODE IN " + SqlUtil.toSqlStringSet(this.getPlList()) + " OR  " + tableAs + ".PL_CODE IS NULL  OR " + tableAs + ".PL_CODE = '' )");
            }
        }
    }

    /****一个快速设置查询条件的工具方法  只开启客户权限  不使用产品线
     * tableAs  主表别名
     * custCode 客户字段别名 **/
    public void permssionsSql(String tableAs, String custCode, SQL sql) {
        if (custCode == null) {
            custCode = "CUST_CODE";
        }
        if (this.getCustList() != null && this.getCustList().size() > 0) {
            sql.WHERE(tableAs + "." + custCode + " IN " + SqlUtil.toSqlStringSet(this.getCustList()));
        }

    }

    /***判断给的客户编码是否在权限集合了存在  true 存在 false不存在**/
    public boolean isCustPermssion(String custCode){
        if(custList!=null){
            if(custList.size()==0){//空表示所有权限 所有返回存在
                return true;
            }
            for(String custCodeset: custList){
                if(custCodeset.equals(custCode)){
                    return true;
                }
            }
         }else{//空表示所有权限 所有返回存在
            return true;
        }
          return false;
    }
}

package com.king.king.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**shihao.ma 2019/12/31 猫哆哩迁移**/
@ApiModel("数据校验错")
public final class EdpValidationError {
    @ApiModelProperty("属性路径")
    private final String path;
    @ApiModelProperty("错误代码或描述")
    private final String code;

    public static EdpValidationError of(String path, OpResult result) {
        return of(path, result.getCode());
    }

    private EdpValidationError(final String path, final String code) {
        this.path = path;
        this.code = code;
    }

    public static EdpValidationError of(final String path, final String code) {
        return new EdpValidationError(path, code);
    }

    public String getPath() {
        return this.path;
    }

    public String getCode() {
        return this.code;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof EdpValidationError)) {
            return false;
        } else {
            EdpValidationError other = (EdpValidationError)o;
            Object this$path = this.getPath();
            Object other$path = other.getPath();
            if (this$path == null) {
                if (other$path != null) {
                    return false;
                }
            } else if (!this$path.equals(other$path)) {
                return false;
            }

            Object this$code = this.getCode();
            Object other$code = other.getCode();
            if (this$code == null) {
                if (other$code != null) {
                    return false;
                }
            } else if (!this$code.equals(other$code)) {
                return false;
            }

            return true;
        }
    }

    public int hashCode() {
//        int PRIME = true;
//        int result = 1;
//        Object $path = this.getPath();
//        int result = result * 59 + ($path == null ? 43 : $path.hashCode());
//        Object $code = this.getCode();
//        result = result * 59 + ($code == null ? 43 : $code.hashCode());
        return 1;
    }

    public String toString() {
        return "EdpValidationError(path=" + this.getPath() + ", code=" + this.getCode() + ")";
    }
}

package com.ralap.blog.bussiness.enums;

/**
 * AvailableEnum
 *
 * @author: ralap
 * @date: created at 2018/6/29 18:25
 */
public enum AvailableEnum {

    ENABLE(true, "启用"), DISABLE(false, "不启用");

    private boolean code;
    private String desc;

    AvailableEnum(boolean code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static AvailableEnum get(boolean code) {
        if (code) {
            return ENABLE;
        }else{

            return DISABLE;
        }
    }

    public boolean isCode() {
        return code;
    }

    public void setCode(boolean code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

package com.ralap.blog.bussiness.enums;

/**
 * @author: ralap
 * @date: created at 2018/5/19 11:29
 */
public enum ResponseStatus {

    SUCCESS(200, "操作成功"),
    ERROR(500, "服务器未知错误");

    private Integer code;
    private String msg;

    ResponseStatus(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResponseStatus getResponseStatus(String msg) {
        for (ResponseStatus rs : ResponseStatus.values()) {
            if (rs.getMsg() == msg) {
                return rs;
            }
        }
        return null;
    }

    public static ResponseStatus getResponseStatus(Integer code) {
        for (ResponseStatus rs : ResponseStatus.values()) {
            if (rs.getCode() == code) {
                return rs;
            }
        }
        return null;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package com.ralap.blog.bussiness.enums;

/**
 * @author: ralap
 * @date: created at 2018/5/20 13:00
 */
public enum ArticleStatusEnum {

    PUBLISHED(1, "发布"),
    UNPUBLISHED(0, "草稿");

    private int code;
    private String desc;

    ArticleStatusEnum(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static ArticleStatusEnum get(Integer code) {
        if (code == null) {
            return UNPUBLISHED;
        }
        ArticleStatusEnum[] statusEnums = ArticleStatusEnum.values();
        for (ArticleStatusEnum statusEnum : statusEnums) {
            if (code == statusEnum.getCode()) {
                return statusEnum;
            }
        }
        return UNPUBLISHED;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

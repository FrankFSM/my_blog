package com.ralap.blog.util;

import java.util.Date;
import org.springframework.util.StringUtils;

/**
 * @author: ralap
 * @date: created at 2018/4/26 14:07
 */
public class StringUtil extends StringUtils {

    /**
     * 生成指定前缀的流水
     *
     * @param prefix 前缀
     */
    public static String createSerialNo(String prefix) {
        return prefix + "_" + DateUtils.dateStr(new Date(), "yyyyMMddHHmmssSSS")
                + (int) (Math.random() * 9 + 1);
    }

}

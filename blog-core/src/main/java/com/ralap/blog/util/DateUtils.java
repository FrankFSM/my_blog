package com.ralap.blog.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: ralap
 * @date: created at 2018/4/26 14:08
 */
public class DateUtils {

    /**
     * 时间转指定类型的字符串
     *
     * @param date 时间
     * @param format 格式
     * @return 时间字符串
     */
    public static String dateStr(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        String str = dateFormat.format(date);
        return str;
    }
}

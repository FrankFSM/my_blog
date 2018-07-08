package com.ralap.blog.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * BCrypyCoderUtil
 *
 * @author: ralap
 * @date: created at 2018/6/7 21:55
 */
public class BCrypyCoderUtil {

    /**
     * BCryptPasswordEncoder加密
     *
     * @param raw 未加密
     * @return 加密密码
     */
    public static String encoder(String raw) {
        return new BCryptPasswordEncoder().encode(raw);
    }

    public static void main(String[] args) {
        System.out.println(encoder("123456"));
    }
}

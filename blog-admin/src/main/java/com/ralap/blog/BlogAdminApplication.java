package com.ralap.blog;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;

@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableCaching
public class BlogAdminApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(BlogAdminApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments applicationArguments) {
        log.info("博客后台启动完成");
    }
}

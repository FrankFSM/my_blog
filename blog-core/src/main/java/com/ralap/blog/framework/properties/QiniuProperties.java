package com.ralap.blog.framework.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * QiniuProperties
 *
 * @author: ralap
 * @date: created at 2018/5/19 14:19
 */
@Data
@ConfigurationProperties(prefix = "qiniu")
@Component
public class QiniuProperties {

    private String accessKey;
    private String secretKey;
    private String bucketName;

}

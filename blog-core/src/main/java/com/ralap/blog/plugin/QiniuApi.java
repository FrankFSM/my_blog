package com.ralap.blog.plugin;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import com.ralap.blog.framework.holder.SpringContextHolder;
import com.ralap.blog.framework.properties.QiniuProperties;
import com.ralap.blog.util.DateUtils;
import com.ralap.blog.util.FileUtils;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;

/**
 * QiniuApi
 *
 * @author: ralap
 * @date: created at 2018/5/19 14:19
 */
@Slf4j
public class QiniuApi {

    private static Object object = new Object();
    private QiniuProperties qiniuProperties;
    private UploadManager uploadManager;
    private Auth auth;
    private String uploadToken;
    private String filePath;


    public static final Integer QINIU_RESPONSE_CODE_SUCCESS = 200;
    public static final String QINIU_SERVER_PATH = "http://p8lakn11w.bkt.clouddn.com/";

    private QiniuApi() {
        qiniuProperties = SpringContextHolder.getBean(QiniuProperties.class);
        auth = Auth.create(qiniuProperties.getAccessKey(), qiniuProperties.getSecretKey());
        Configuration cfg = new Configuration(Zone.zone1());
        uploadManager = new UploadManager(cfg);
        uploadToken = auth.uploadToken(qiniuProperties.getBucketName());
    }

    public static QiniuApi getInstance() {
        synchronized (object) {
            return new QiniuApi();
        }
    }

    public QiniuApi getFileName(String key) {
        String suffix = FileUtils.getSuffix(key);
        String fileName = DateUtils.dateStr(new Date(), "yyyyMMddHHmmssSSS");
        this.filePath = "ralap/" + fileName + suffix;
        return this;
    }

    public String upload(byte[] bytes) {
        try {
            log.info("七牛云图片上传开始······");
            Response response = this.uploadManager.put(bytes, filePath, uploadToken);
            StringMap resMap = qiniuResponseAnaly(response);
            String url = String.valueOf(resMap.get("key"));
            String picUrl = QiniuApi.QINIU_SERVER_PATH + url;
            log.info("七牛云图片上传成功,路径【{}】", picUrl);
            return picUrl;
        } catch (QiniuException e) {
            log.error("七牛云图片上传失败");
        }
        return null;
    }

    /**
     * 解析七牛返回结果
     */
    public StringMap qiniuResponseAnaly(Response response) {

        int statusCode = response.statusCode;
        if (statusCode == QiniuApi.QINIU_RESPONSE_CODE_SUCCESS) {
            try {
                StringMap map = response.jsonToMap();
                return map;
            } catch (QiniuException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

package com.ralap.blog.util;

import com.ralap.blog.plugin.QiniuApi;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: ralap
 * @date: created at 2018/5/15 11:49
 */
public class FileUtils {

    private static final Logger LOG = LoggerFactory.getLogger(FileUtils.class);
    /**
     * 常用图片后缀
     */
    private static final String[] PIC_SUFFIX = {".bmp", ".jpg", ".gif", ".jpeg", ".png"};

    public static String getPrefix(String fileName) {
        int idx = fileName.lastIndexOf(".");
        int xie = fileName.lastIndexOf("/");
        idx = idx == -1 ? fileName.length() : idx;
        xie = xie == -1 ? 0 : xie + 1;
        return fileName.substring(xie, idx);
    }

    public static String getSuffix(File file) {
        return getSuffix(file.getName());
    }

    public static String getSuffix(String fileName) {
        int idx = fileName.lastIndexOf(".");
        idx = idx == -1 ? fileName.length() : idx;
        return fileName.substring(idx);
    }

    /**
     * 上传图片
     */
    public static String uploadPicFile(MultipartFile file, String path) {
        String filePaht = "";
        if (file == null) {
            throw new RuntimeException("请选择图片");
        }
        String originalFilename = file.getOriginalFilename();
        if (!FileUtils.isPicture(originalFilename)) {
            throw new RuntimeException("格式错误,请上传图片！！");
        }

        try {
            filePaht = QiniuApi.getInstance().getFileName(originalFilename)
                    .upload(file.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePaht;


    }

    /**
     * 判断是否为图片
     *
     * @param fileName 文件名
     */
    public static boolean isPicture(String fileName) {
        if (StringUtil.isEmpty(fileName)) {
            return false;
        }
        if (Arrays.asList(PIC_SUFFIX).contains(FileUtils.getSuffix(fileName).toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }
}

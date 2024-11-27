package com.gdou.support;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author Raqtpie
 */
public interface MinioHelper {
    /**
     * 上传文件
     * @param file 文件
     * @param filename 文件名
     * @return 文件url
     */
    String uploadFile(MultipartFile file, String filename);

    /**
     * 上传文件
     * @param inputStream 文件流
     * @param filename 文件名
     * @param contentType 文件类型
     * @return 文件url
     */
    String uploadFile(InputStream inputStream, String filename, String contentType);

    /**
     * 删除文件
     * @param filename 文件名
     */
    void deleteFile(String filename);

    /**
     * 通过url获取文件名
     * @param url 文件url
     * @return 文件名
     */
    String getFilenameByUrl(String url);
}

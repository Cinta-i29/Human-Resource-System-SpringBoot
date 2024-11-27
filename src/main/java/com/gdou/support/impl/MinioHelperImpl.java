package com.gdou.support.impl;

import com.gdou.exception.CustomException;
import com.gdou.support.MinioHelper;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.errors.MinioException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author Raqtpie
 */
@RequiredArgsConstructor
@Component
public class MinioHelperImpl implements MinioHelper {
    @Value("${minio.endpoint}")
    private String endpoint;

    @Value("${minio.bucket}")
    private String bucket;

    private final MinioClient minioClient;

    @Override
    public String uploadFile(MultipartFile file, String filename) {
        try {
            InputStream inputStream = file.getInputStream();
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    // 文件名为id.后缀名
                    .object(filename)
                    .contentType(file.getContentType())
                    .stream(inputStream, inputStream.available(), -1)
                    .build());
        } catch (IOException e) {
            throw new CustomException("文件读取失败");
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new CustomException("文件上传失败");
        }
        return endpoint + bucket + "/" + filename;
    }

    @Override
    public String uploadFile(InputStream inputStream, String filename, String contentType) {
        try {
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucket)
                    // 文件名为id.后缀名
                    .object(filename)
                    .contentType(contentType)
                    .stream(inputStream, inputStream.available(), -1)
                    .build());
        } catch (IOException e) {
            throw new CustomException("文件读取失败");
        } catch (MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new CustomException("文件上传失败");
        }
        return endpoint + bucket + "/" + filename;
    }


    @Override
    public void deleteFile(String filename) {
        try {
            minioClient.removeObject(RemoveObjectArgs.builder()
                    .bucket(bucket)
                    .object(filename)
                    .build()
            );
        } catch (IOException | MinioException | InvalidKeyException | NoSuchAlgorithmException e) {
            throw new CustomException("文件删除失败");
        }
    }

    @Override
    public String getFilenameByUrl(String url) {
        return url.substring(url.lastIndexOf("/") + 1);
    }
}

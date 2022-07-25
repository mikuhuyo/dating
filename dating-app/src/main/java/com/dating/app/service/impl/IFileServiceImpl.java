package com.dating.app.service.impl;

import com.dating.app.minio.MinIoComponent;
import com.dating.app.service.IFileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
@Service
public class IFileServiceImpl implements IFileService {

    @Value("${minio.bucketImageName}")
    private String bucketImageName;
    @Value("${minio.serviceName}")
    private String serviceName;

    @Autowired
    private MinIoComponent minIoComponent;

    @Override
    public String uploadImage(String fileName, String fileType, InputStream inputStream) throws Exception {
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        String format = simpleDateFormat.format(date);

        String newFileName = serviceName
                + "/" + format + "/"
                + UUID.randomUUID().toString().replaceAll("-", "")
                + fileName.substring(fileName.lastIndexOf("."));

        minIoComponent.putObject(bucketImageName, newFileName, fileType, inputStream);

        inputStream.close();

        // 获取访问url路径
        return minIoComponent.getObjectUrl(bucketImageName, newFileName);
    }
}

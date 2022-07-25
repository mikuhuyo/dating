package com.dating.app.service;

import java.io.InputStream;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IFileService {
    /**
     * 图片文件上传
     *
     * @param fileName    文件名称
     * @param fileType    文件类型
     * @param inputStream 输入流
     * @return
     * @throws Exception
     */
    String uploadImage(String fileName, String fileType, InputStream inputStream) throws Exception;
}

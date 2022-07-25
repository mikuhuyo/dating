package com.dating.app.service;

import org.json.JSONException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IBaiduService {

    /**
     * 校验图片中是否包含人脸(API方式)
     *
     * @param imageUrl 图片地址
     * @return 响应JSON字符串
     */
    String verifyThatTheImageContainsFaceFromAPI(String imageUrl);

    /**
     * 校验图片中是否包含人脸(SDK方式)
     *
     * @param imageUrl 图片地址
     * @return true-图片包含人脸, false图片不包含人脸
     */
    boolean verifyThatTheImageContainsFaceFromSDK(String imageUrl) throws JSONException;
}

package com.dating.app.thirdparty.baidu.config;

import com.baidu.aip.face.AipFace;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 百度人脸识别配置类
 *
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Configuration
public class BaiDuFaceConfig {
    @Value("${baidu.face.appId}")
    private String appId;
    @Value("${baidu.face.apiKey}")
    private String apiKey;
    @Value("${baidu.face.secretKey}")
    private String secretKey;

    @Bean
    public AipFace aipFace() {
        AipFace client = new AipFace(appId, apiKey, secretKey);

        // 可选: 设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(6000);

        return client;
    }
}

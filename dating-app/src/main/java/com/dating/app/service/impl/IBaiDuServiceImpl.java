package com.dating.app.service.impl;

import com.baidu.aip.face.AipFace;
import com.dating.app.thirdparty.baidu.utils.GsonUtils;
import com.dating.app.thirdparty.baidu.utils.HttpUtil;
import com.dating.app.service.IBaiduService;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
@Service
public class IBaiDuServiceImpl implements IBaiduService {
    @Value("${baidu.face.apiKey}")
    private String apiKey;
    @Value("${baidu.face.secretKey}")
    private String secretKey;
    @Value("${baidu.face.imageType}")
    private String imageType;
    @Value("${baidu.face.authUrl}")
    private String authUrl;
    @Value("${baidu.face.faceUrl}")
    private String faceUrl;

    @Autowired
    private AipFace client;

    /**
     * 获取认证token(获取的token字符串默认有效期为30天, 为了避免浪费我们应该使用redis方式进行token的存储)
     *
     * @return token字符串
     */
    private String getAuth() {
        String getAccessTokenUrl = authUrl
                + "grant_type=client_credentials"
                + "&client_id=" + apiKey
                + "&client_secret=" + secretKey;

        try {
            URL realUrl = new URL(getAccessTokenUrl);
            // 打开和URL之间的连接
            HttpURLConnection connection = (HttpURLConnection) realUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            // 定义 BufferedReader输入流来读取URL的响应
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                result.append(line);
            }

            log.info("getAuth() -> 百度人脸识别认证API响应为: " + result);
            JSONObject jsonObject = new JSONObject(result.toString());

            return jsonObject.getString("access_token");
        } catch (Exception e) {
            log.error("获取token失败");
        }

        return null;
    }

    @Override
    public String verifyThatTheImageContainsFaceFromAPI(String imageUrl) {
        try {
            Map<String, Object> options = new HashMap<>();
            options.put("face_field", "age");
            options.put("max_face_num", "2");
            options.put("face_type", "LIVE");
            options.put("liveness_control", "LOW");

            String param = GsonUtils.toJson(options);
            String accessToken = getAuth();

            String result = HttpUtil.post(faceUrl, accessToken, "application/json", param);

            log.info("verifyThatTheImageContainsFaceFromAPI() -> 百度人脸识别API识别成功, 响应结果为: " + result);

            return result;
        } catch (Exception e) {
            log.error("verifyThatTheImageContainsFaceFromAPI() -> 百度人脸识别API识别失败");
        }

        return null;
    }

    @Override
    public boolean verifyThatTheImageContainsFaceFromSDK(String imageUrl) throws JSONException {
        HashMap<String, String> options = new HashMap<>();
        options.put("face_field", "age");
        options.put("max_face_num", "2");
        options.put("face_type", "LIVE");
        options.put("liveness_control", "LOW");

        // 人脸检测
        JSONObject res = client.detect(imageUrl, imageType, options);
        log.info("verifyThatTheImageContainsFace() -> 百度人脸识别SDK获取响应结果: " + res.toString());

        /**
         * Integer 源代码中有一个缓冲区一样的东西, 当数组超过一定范围的时候, 使用==进行比较就会失效
         */
        Integer errorCode = (Integer) res.get("error_code");

        return errorCode.equals(0);
    }
}

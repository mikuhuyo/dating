package com.dating.app.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.dating.app.service.ISmsService;
import com.dating.model.exception.BusinessException;
import com.dating.model.exception.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Slf4j
@Service
public class ISmsServiceImpl implements ISmsService {
    @Value("${sms.name}")
    private String name;
    @Value("${sms.url}")
    private String url;
    @Value("${sms.effectiveTime}")
    private String effectiveTime;

    @Resource(name = "redisTemplate")
    private RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean verificationKey(String mobile, String smsCode) throws IOException {
        String key = Objects.requireNonNull(redisTemplate.opsForValue().get(mobile + ".sms.key")).toString();

        String smsUrl = url + "/verify?"
                + "name=" + name
                + "&verificationCode=" + smsCode
                + "&verificationKey=" + key;

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("text/plain");
        RequestBody body = RequestBody.create(mediaType, "");
        Request request = new Request.Builder()
                .url(smsUrl)
                .method("POST", body)
                .build();

        String string = Objects.requireNonNull(client.newCall(request).execute().body()).string();
        log.info("调用腾讯云短信服务-获取响应: {}", string);

        Map<String, Object> map = JSONObject.parseObject(string, new TypeReference<Map<String, Object>>() {
        });

        if (!(Boolean) map.get("result")) {
            log.error("com.dating.app.service.impl.ISmsServiceImpl#verificationKey(String mobile, String smsCode) -> {}", ErrorResult.loginError().getErrMessage());
            throw new BusinessException(ErrorResult.loginError());
        }

        // 删除缓存
        redisTemplate.delete(mobile + ".sms.key");
        return true;
    }

    @Override
    public void getSmsCode(String mobile) throws IOException {
        String smsUrl = url + "/generate?" + "effectiveTime=" + effectiveTime + "&name=" + name;

        Map<String, Object> load = new HashMap<>();
        load.put("mobile", mobile);

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, JSONObject.toJSONString(load));
        Request request = new Request.Builder()
                .url(smsUrl)
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();

        String jsonString = Objects.requireNonNull(client.newCall(request).execute().body()).string();
        log.info("调用腾讯云短信服务-获取响应: {}", jsonString);
        Map<String, Object> map = JSONObject.parseObject(jsonString, new TypeReference<Map<String, Object>>() {
        });

        if (map.get("result") == null) {
            log.error("com.dating.app.service.impl.ISmsServiceImpl#getSmsCode(String mobile) -> {}", ErrorResult.fail().getErrMessage());
            throw new BusinessException(ErrorResult.fail());
        }

        Map<String, Object> result = JSONObject.parseObject(JSONObject.toJSONString(map.get("result")), new TypeReference<Map<String, Object>>() {
        });

        String key = result.get("key").toString();

        // 保存秘钥, 设置失效时间5分钟
        redisTemplate.opsForValue().set(mobile + ".sms.key", key, Duration.ofMinutes(5L));
    }
}

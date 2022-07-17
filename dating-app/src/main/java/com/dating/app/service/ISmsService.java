package com.dating.app.service;

import java.io.IOException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface ISmsService {
    /**
     * 验证码校验
     *
     * @param mobile  手机号
     * @param smsCode 验证码
     * @return true/false
     */
    boolean verificationKey(String mobile, String smsCode) throws IOException;

    /**
     * 获取短信验证码秘钥
     *
     * @param mobile 手机号
     * @return 秘钥
     * @throws IOException
     */
    void getSmsCode(String mobile) throws IOException;
}

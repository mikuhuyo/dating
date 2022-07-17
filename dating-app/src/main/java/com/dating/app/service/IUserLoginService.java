package com.dating.app.service;

import com.dating.interfaces.dto.UserLoginDTO;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IUserLoginService {
    /**
     * 用户注册与登录
     *
     * @param mobile 手机号
     * @return UserLoginDTO 登录响应实体
     */
    UserLoginDTO userLogin(String mobile);
}

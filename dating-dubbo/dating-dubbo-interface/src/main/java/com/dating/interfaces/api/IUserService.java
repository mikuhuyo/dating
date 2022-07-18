package com.dating.interfaces.api;

import com.dating.model.domain.User;
import com.dating.model.domain.UserInfo;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IUserService {
    /**
     * 添加用户信息
     *
     * @param userInfo 用户信息实体
     */
    void createUserInfo(UserInfo userInfo);

    /**
     * 添加用户
     *
     * @param user
     * @return 用户ID
     */
    long createUser(User user);

    /**
     * 根据手机号查询用户
     *
     * @param mobile 手机号
     * @return 用户实体
     */
    User findUserByMobile(String mobile);
}

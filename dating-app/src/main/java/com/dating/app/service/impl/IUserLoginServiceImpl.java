package com.dating.app.service.impl;

import com.dating.app.service.IUserLoginService;
import com.dating.commons.utils.JwtUtils;
import com.dating.interfaces.api.IUserService;
import com.dating.interfaces.dto.UserLoginDTO;
import com.dating.model.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Service
public class IUserLoginServiceImpl implements IUserLoginService {
    @Reference
    private IUserService userService;

    @Override
    public UserLoginDTO userLogin(String mobile) {
        Map<String, Object> data = new HashMap<>();

        UserLoginDTO userLoginDTO = new UserLoginDTO();
        userLoginDTO.setIsNew(false);

        User userByMobile = userService.findUserByMobile(mobile);
        if (userByMobile == null) {
            User user = new User();
            user.setMobile(mobile);
            user.setPassword(DigestUtils.md5Hex("mikuhuyo"));
            long userId = userService.createUser(user);
            data.put("id", userId);

            userLoginDTO.setIsNew(true);
        } else {
            data.put("id", userByMobile.getId());
        }

        data.put("mobile", mobile);

        String token = JwtUtils.getToken(data);
        userLoginDTO.setToken(token);

        return userLoginDTO;
    }
}

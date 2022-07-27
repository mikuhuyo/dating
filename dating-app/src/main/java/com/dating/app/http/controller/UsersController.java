package com.dating.app.http.controller;

import com.dating.app.utils.ThreadLocalUserUtil;
import com.dating.interfaces.api.IUserService;
import com.dating.model.domain.UserInfo;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
@RestController
@RequestMapping("/users")
public class UsersController {
    @Reference
    private IUserService userService;

    @PutMapping
    public ResponseEntity updateUserInfo(@RequestBody UserInfo userInfo) {
        userInfo.setId(ThreadLocalUserUtil.getUserId());

        userService.updateUserById(userInfo);
        return ResponseEntity.status(200).body(null);
    }
}

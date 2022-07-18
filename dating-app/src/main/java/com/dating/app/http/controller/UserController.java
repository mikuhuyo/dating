package com.dating.app.http.controller;

import com.dating.app.service.ISmsService;
import com.dating.app.service.IUserLoginService;
import com.dating.commons.utils.JwtUtils;
import com.dating.interfaces.api.IUserService;
import com.dating.interfaces.dto.UserLoginDTO;
import com.dating.interfaces.vo.UserLoginVO;
import com.dating.model.domain.UserInfo;
import io.jsonwebtoken.Claims;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private ISmsService smsService;
    @Autowired
    private IUserLoginService userLoginService;

    @Reference
    private IUserService userService;

    @PostMapping("/loginReginfo")
    public ResponseEntity loginReginfo(@RequestBody UserInfo userInfo, @RequestHeader("Authorization") String token) {
        Claims claims = JwtUtils.getClaims(token);
        Long id = (Long) claims.get("id");
        userInfo.setId(id);

        userService.createUserInfo(userInfo);
        return ResponseEntity.status(200).body(null);
    }

    @PostMapping("/loginVerification")
    public ResponseEntity loginVerification(@RequestBody UserLoginVO loginVO) throws IOException {
        String mobile = loginVO.getPhone();
        String code = loginVO.getVerificationCode();

        smsService.verificationKey(mobile, code);

        UserLoginDTO userLoginDTO = userLoginService.userLogin(mobile);
        return ResponseEntity.status(HttpStatus.OK.value()).body(userLoginDTO);

        // boolean isSend = smsService.verificationKey(mobile, code);
        // if (isSend) {
        //     UserLoginDTO userLoginDTO = userLoginService.userLogin(mobile);
        //     return ResponseEntity.status(200).body(userLoginDTO);
        // }
        //
        // return ResponseEntity.status(200).body("发生肾摸湿了?");
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserLoginVO userLoginVO) throws IOException {
        smsService.getSmsCode(userLoginVO.getPhone());
        return ResponseEntity.status(HttpStatus.OK.value()).body(null);

        // return ResponseEntity.status(200).body("success");
    }
}

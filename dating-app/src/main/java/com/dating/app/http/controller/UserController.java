package com.dating.app.http.controller;

import com.dating.app.service.ISmsService;
import com.dating.app.service.IUserLoginService;
import com.dating.interfaces.dto.UserLoginDTO;
import com.dating.interfaces.vo.UserLoginVO;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/loginVerification")
    public ResponseEntity loginVerification(@RequestBody UserLoginVO loginVO) throws IOException {
        String mobile = loginVO.getPhone();
        String code = loginVO.getVerificationCode();

        boolean isSend = smsService.verificationKey(mobile, code);
        if (isSend) {
            UserLoginDTO userLoginDTO = userLoginService.userLogin(mobile);
            return ResponseEntity.status(200).body(userLoginDTO);
        }

        return ResponseEntity.status(200).body("发生肾摸湿了?");
    }

    @PostMapping("/login")
    public ResponseEntity userLogin(@RequestBody UserLoginVO userLoginVO) throws IOException {
        smsService.getSmsCode(userLoginVO.getPhone());
        return ResponseEntity.status(200).body("success");
    }
}

package com.dating.app.test.sms;

import com.dating.app.DatingAppServerApplication;
import com.dating.app.service.ISmsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DatingAppServerApplication.class)
public class SmsTest {
    @Autowired
    private ISmsService smsService;

    @Test
    public void testVerificationSmsCode() throws IOException {
        smsService.verificationKey("12345678911", "022319");
    }

    @Test
    public void testSendSmsCode() throws IOException {
        smsService.getSmsCode("12345678911");
    }
}

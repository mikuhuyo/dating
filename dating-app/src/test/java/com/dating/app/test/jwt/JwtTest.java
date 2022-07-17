package com.dating.app.test.jwt;

import com.dating.app.DatingAppServerApplication;
import com.dating.commons.utils.JwtUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@SpringBootTest(classes = DatingAppServerApplication.class)
@RunWith(SpringRunner.class)
public class JwtTest {
    @Test
    public void testVerification() {
        System.out.println(JwtUtils.verifyToken("eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTgwNDU0NDksInVzZXJuYW1lIjoi6I-c54uX5a2QIn0.KJxsbKOwXf4VfvqeKIfZwhff7VNyOqjW2NqtIXj7_s4WawUJjpW68WODvjS0gKL5O8_Wi3opGrDXps7lJZsyeA"));
    }

    @Test
    public void testInfo() {
        System.out.println(JwtUtils.getClaims("eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NTgwNDU0NDksInVzZXJuYW1lIjoi6I-c54uX5a2QIn0.KJxsbKOwXf4VfvqeKIfZwhff7VNyOqjW2NqtIXj7_s4WawUJjpW68WODvjS0gKL5O8_Wi3opGrDXps7lJZsyeA"));
    }

    @Test
    public void testToken() {
        Map<String, Object> map = new HashMap<>();
        map.put("username",  "菜狗子");

        String token = JwtUtils.getToken(map);
        System.out.println(token);
    }
}

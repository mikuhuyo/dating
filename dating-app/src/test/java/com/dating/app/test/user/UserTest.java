package com.dating.app.test.user;

import com.dating.app.DatingAppServerApplication;
import com.dating.interfaces.api.IUserService;
import com.dating.model.domain.User;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@SpringBootTest(classes = DatingAppServerApplication.class)
@RunWith(SpringRunner.class)
public class UserTest {
    @Reference
    private IUserService userService;

    @Test
    public void creatUser() {
        User user = new User();
        user.setMobile("12345678911");
        user.setPassword(DigestUtils.md5Hex("mikuhuyo"));

        long id = userService.createUser(user);
        System.out.println(id);
    }

    @Test
    public void findUserByMobile() {
        System.out.println(userService.findUserByMobile("12345678911"));
    }
}

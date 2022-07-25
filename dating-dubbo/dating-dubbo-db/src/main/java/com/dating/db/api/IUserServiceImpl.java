package com.dating.db.api;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dating.db.mapper.UserInfoMapper;
import com.dating.db.mapper.UserMapper;
import com.dating.interfaces.api.IUserService;
import com.dating.model.domain.User;
import com.dating.model.domain.UserInfo;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public void updateUserById(UserInfo userInfo) {
        userInfoMapper.updateById(userInfo);
    }

    @Override
    public void createUserInfo(UserInfo userInfo) {
        userInfoMapper.insert(userInfo);
    }

    @Override
    public long createUser(User user) {
        userMapper.insert(user);
        return user.getId();
    }

    @Override
    public User findUserByMobile(String mobile) {
        return userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getMobile, mobile));
    }
}

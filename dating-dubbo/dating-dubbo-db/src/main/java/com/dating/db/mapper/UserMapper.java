package com.dating.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dating.model.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

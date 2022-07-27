package com.dating.db.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dating.model.domain.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
}

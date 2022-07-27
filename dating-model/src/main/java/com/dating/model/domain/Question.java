package com.dating.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
@Data
@TableName(value = "tb_question")
public class Question implements Serializable {
    private static final long serialVersionUID = -4719912027625525449L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "txt")
    private String txt;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;
}

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
@TableName(value = "tb_black_list")
public class BlackList implements Serializable {
    private static final long serialVersionUID = 3994689244697080739L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "black_user_id")
    private Long blackUserId;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;
}

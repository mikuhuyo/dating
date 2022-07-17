package com.dating.model.domain;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
@TableName(value = "tb_user")
public class User implements Serializable {
    private static final long serialVersionUID = 1373776472806092413L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "mobile")
    private String mobile;

    @TableField(value = "password")
    private String password;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;
}

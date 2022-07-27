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
@TableName(value = "tb_settings")
public class Settings implements Serializable {
    private static final long serialVersionUID = -8635457905581680043L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "like_notification")
    private Boolean likeNotification;

    @TableField(value = "pinglun_notification")
    private Boolean pinglunNotification;

    @TableField(value = "gonggao_notification")
    private Boolean gonggaoNotification;


    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;

}

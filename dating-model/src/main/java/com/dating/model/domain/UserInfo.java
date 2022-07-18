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
@TableName(value = "tb_user_info")
public class UserInfo implements Serializable {
    private static final long serialVersionUID = 4629275808326814601L;

    @TableId(type= IdType.INPUT)
    private Long id;

    private String nickname;

    private String avatar;

    private String birthday;

    private String gender;

    private Integer age;

    private String city;

    /**
     * 收入
     */
    private String income;

    /**
     * 学历
     */
    private String education;

    /**
     * 行业
     */
    private String profession;

    /**
     * 婚姻状态
     */
    private Integer marriage;

    /**
     * 用户标签: 多个用逗号分隔
     */
    private String tags;

    /**
     * 封面图片
     */
    @TableField(value = "cover_pic")
    private String coverPic;

    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @TableField(value = "updated", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updated;

    /**
     * 用户状态, 1为正常, 2为冻结
     */

    @TableField(exist = false)
    private final String userStatus = "1";
}

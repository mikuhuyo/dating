CREATE DATABASE IF NOT EXISTS `dating` charset = utf8mb4 COLLATE utf8mb4_general_ci;
USE `dating`;

CREATE TABLE `tb_user`
(
    `id`       BIGINT(20) NOT NULL AUTO_INCREMENT,
    `mobile`   VARCHAR(11) DEFAULT NULL COMMENT '手机号',
    `password` VARCHAR(32) DEFAULT NULL COMMENT '密码，需要加密',
    `created`  datetime    DEFAULT NULL,
    `updated`  datetime    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `mobile` (`mobile`) USING BTREE
) ENGINE = INNODB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户表';

CREATE TABLE `tb_user_info`
(
    `id`        BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`   BIGINT(20) NOT NULL COMMENT '用户id',
    `nick_name` VARCHAR(50)  DEFAULT NULL COMMENT '昵称',
    `logo`      VARCHAR(100) DEFAULT NULL COMMENT '用户头像',
    `tags`      VARCHAR(50)  DEFAULT NULL COMMENT '用户标签：多个用逗号分隔',
    `sex`       INT(1)       DEFAULT '3' COMMENT '性别，1-男，2-女，3-未知',
    `age`       INT(11)      DEFAULT NULL COMMENT '用户年龄',
    `edu`       VARCHAR(20)  DEFAULT NULL COMMENT '学历',
    `city`      VARCHAR(20)  DEFAULT NULL COMMENT '居住城市',
    `birthday`  VARCHAR(20)  DEFAULT NULL COMMENT '生日',
    `cover_pic` VARCHAR(50)  DEFAULT NULL COMMENT '封面图片',
    `industry`  VARCHAR(20)  DEFAULT NULL COMMENT '行业',
    `income`    VARCHAR(20)  DEFAULT NULL COMMENT '收入',
    `marriage`  VARCHAR(20)  DEFAULT NULL COMMENT '婚姻状态',
    `created`   datetime     DEFAULT NULL,
    `updated`   datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息表';

CREATE TABLE `tb_settings`
(
    `id`                   BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`              BIGINT(20) DEFAULT NULL,
    `like_notification`    TINYINT(4) DEFAULT '1' COMMENT '推送喜欢通知',
    `pinglun_notification` TINYINT(4) DEFAULT '1' COMMENT '推送评论通知',
    `gonggao_notification` TINYINT(4) DEFAULT '1' COMMENT '推送公告通知',
    `created`              datetime   DEFAULT NULL,
    `updated`              datetime   DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '设置表';

CREATE TABLE `tb_question`
(
    `id`      BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(20)   DEFAULT NULL COMMENT '用户id',
    `txt`     VARCHAR(200) DEFAULT NULL COMMENT '问题内容',
    `created` datetime     DEFAULT NULL,
    `updated` datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '问题表';

CREATE TABLE `tb_black_list`
(
    `id`            BIGINT(20) NOT NULL AUTO_INCREMENT,
    `user_id`       BIGINT(20) DEFAULT NULL,
    `black_user_id` BIGINT(20) DEFAULT NULL,
    `created`       datetime   DEFAULT NULL,
    `updated`       datetime   DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE = INNODB
  DEFAULT CHARSET = utf8mb4 COMMENT = '黑名单';
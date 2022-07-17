create database if not exists `dating` charset=utf8mb4 collate utf8mb4_general_ci;
use `dating`;

CREATE TABLE `tb_user`
(
    `id`       bigint(20) NOT NULL AUTO_INCREMENT,
    `mobile`   varchar(11) DEFAULT NULL COMMENT '手机号',
    `password` varchar(32) DEFAULT NULL COMMENT '密码，需要加密',
    `created`  datetime    DEFAULT NULL,
    `updated`  datetime    DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `mobile` (`mobile`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='用户表';

CREATE TABLE `tb_user_info`
(
    `id`        bigint(20) NOT NULL AUTO_INCREMENT,
    `user_id`   bigint(20) NOT NULL COMMENT '用户id',
    `nick_name` varchar(50)  DEFAULT NULL COMMENT '昵称',
    `logo`      varchar(100) DEFAULT NULL COMMENT '用户头像',
    `tags`      varchar(50)  DEFAULT NULL COMMENT '用户标签：多个用逗号分隔',
    `sex`       int(1)       DEFAULT '3' COMMENT '性别，1-男，2-女，3-未知',
    `age`       int(11)      DEFAULT NULL COMMENT '用户年龄',
    `edu`       varchar(20)  DEFAULT NULL COMMENT '学历',
    `city`      varchar(20)  DEFAULT NULL COMMENT '居住城市',
    `birthday`  varchar(20)  DEFAULT NULL COMMENT '生日',
    `cover_pic` varchar(50)  DEFAULT NULL COMMENT '封面图片',
    `industry`  varchar(20)  DEFAULT NULL COMMENT '行业',
    `income`    varchar(20)  DEFAULT NULL COMMENT '收入',
    `marriage`  varchar(20)  DEFAULT NULL COMMENT '婚姻状态',
    `created`   datetime     DEFAULT NULL,
    `updated`   datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `user_id` (`user_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='用户信息表';



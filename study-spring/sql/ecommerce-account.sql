-- ecommerce account 数据表
CREATE TABLE `t_ecommerce_account` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
    `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户 id',
    `username` varchar(64) NOT NULL DEFAULT '' COMMENT '用户名',
    `password` varchar(256) NOT NULL DEFAULT '' COMMENT 'MD5 加密之后的密码',
    `extra_info` varchar(1024) NOT NULL DEFAULT '' COMMENT '额外的信息',
    `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
    `version` int(11) NOT NULL DEFAULT '0' COMMENT '数据版本',
    `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0: normal; 1: deleted',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`),
    UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='账户信息表';

CREATE TABLE `t_ecommerce_account_vip` (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `user_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '用户 id',
   `level` int(11) NOT NULL DEFAULT '0' COMMENT 'vip 等级',
   `points` bigint(20) NOT NULL DEFAULT '0' COMMENT '积分',
   `create_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '创建时间',
   `update_time` datetime NOT NULL DEFAULT '0000-01-01 00:00:00' COMMENT '更新时间',
   `version` int(11) NOT NULL DEFAULT '0' COMMENT '数据版本',
   `deleted` tinyint(4) NOT NULL DEFAULT '0' COMMENT '0: normal; 1: deleted',
   PRIMARY KEY (`id`),
   UNIQUE KEY `user_id` (`user_id`),
   KEY `level` (`level`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='账户 vip 信息表';

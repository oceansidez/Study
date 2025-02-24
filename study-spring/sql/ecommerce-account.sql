SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
                            `id` int NOT NULL AUTO_INCREMENT COMMENT '商品ID',
                            `name` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称',
                            `name_pinyin` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品名称[拼音]',
                            `store` int NULL DEFAULT NULL COMMENT '商品库存',
                            `intro` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '商品简介',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (2, '名字-0', 'mingzi-0', 1, '介绍-0');
INSERT INTO `t_goods` VALUES (3, '名字-2', 'mingzi-2', 1, '介绍-2');

SET FOREIGN_KEY_CHECKS = 1;

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

/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80018
 Source Host           : localhost:3306
 Source Schema         : cloud_sample

 Target Server Type    : MySQL
 Target Server Version : 80018
 File Encoding         : 65001

 Date: 11/12/2019 17:06:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
BEGIN;
INSERT INTO `role` VALUES (3, 'ROLE_Admin');
INSERT INTO `role` VALUES (4, 'ROLE_User');
COMMIT;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL COMMENT '昵称',
  `phone_number` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (7, 'admin', '$2a$10$PM5VmnLbQO447rPizVQIMOsL09JZNuvIqi1papwG6MrGyyOZvP8gy', '管理员', '12345678901');
INSERT INTO `user` VALUES (8, 'test1', '$2a$10$VAr5lfI1QjeUKaPvou4iY.8rG5igSiwLA7MPNgcyeKzSJpH/VRATS', NULL, NULL);
INSERT INTO `user` VALUES (16, 'test2', '$2a$10$tMi3b/8qdJ67yjWbBjfvXe1Y.W6.BoozavULuIAicSOdQ.iv6xaGi', NULL, NULL);
INSERT INTO `user` VALUES (17, 'test3', '$2a$10$QW0djbA5VcTHg6KGtJL.zuFLIK2RDh/TCCdc.ytXfvEygCTs2TO8y', NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  KEY `FK859n2jvi8ivhui0rl0esws6o` (`user_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
BEGIN;
INSERT INTO `user_role` VALUES (7, 3);
INSERT INTO `user_role` VALUES (7, 4);
INSERT INTO `user_role` VALUES (8, 4);
INSERT INTO `user_role` VALUES (16, 3);
INSERT INTO `user_role` VALUES (16, 4);
INSERT INTO `user_role` VALUES (17, 4);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : 192.168.37.111:3306
 Source Schema         : pay

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 26/02/2020 12:46:21
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `pay_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '支付金额',
  `pay_channel` int(2) NULL DEFAULT NULL COMMENT '支付渠道 1微信 2支付宝 3银行卡',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  `order_id` int(11) NULL DEFAULT NULL COMMENT '订单ID',
  `pay_state` int(2) NULL DEFAULT NULL COMMENT '支付状态 1:成功 0:失败',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

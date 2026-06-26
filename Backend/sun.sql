/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80037
 Source Host           : localhost:3306
 Source Schema         : sun

 Target Server Type    : MySQL
 Target Server Version : 80037
 File Encoding         : 65001

 Date: 27/06/2024 20:20:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`  (
  `id` bigint(0) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES (1, '123', 'admin', '123456');

-- ----------------------------
-- Table structure for company
-- ----------------------------
DROP TABLE IF EXISTS `company`;
CREATE TABLE `company`  (
  `company_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `company_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_belong` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_principal` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `company_cost` double NULL DEFAULT 0,
  `company_exist` tinyint(1) NULL DEFAULT 0,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_memeber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`company_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of company
-- ----------------------------
INSERT INTO `company` VALUES ('1', 'DMT', '大连港', '王小明', '17301050124', 238.5, 0, '2024-06-27 11:32:18', NULL);
INSERT INTO `company` VALUES ('2', 'MNC', '天津港', '李红', '16627204434', 0, 0, '2024-06-26 18:35:08', NULL);

-- ----------------------------
-- Table structure for instore
-- ----------------------------
DROP TABLE IF EXISTS `instore`;
CREATE TABLE `instore`  (
  `in_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `in_time` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `in_carid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `in_ton` double NOT NULL,
  `in_casenum` int(0) NOT NULL,
  `in_storeid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `real_ton` double NOT NULL,
  `in_exist` tinyint(1) NULL DEFAULT 0,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_memeber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `processed_for_outstore` tinyint(1) NOT NULL,
  PRIMARY KEY (`in_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of instore
-- ----------------------------
INSERT INTO `instore` VALUES ('1806166699420991489', '2023-07-31T16:00:00.000Z', '黑EL9882', 30, 600, '1', 30, 0, '2024-06-27 11:24:32', NULL, 0);
INSERT INTO `instore` VALUES ('1806166835379355650', '2023-07-31T16:00:00.000Z', '黑ME0003', 33, 660, '1', 33, 0, '2024-06-27 11:25:05', NULL, 0);
INSERT INTO `instore` VALUES ('1806167038685659138', '2023-08-17T16:00:00.000Z', '辽CD5123', 33, 660, '1', 33, 0, '2024-06-27 11:25:53', NULL, 0);
INSERT INTO `instore` VALUES ('1806167149817937922', '2023-08-17T16:00:00.000Z', '辽BM7685', 33, 660, '1', 33, 0, '2024-06-27 11:26:20', NULL, 0);
INSERT INTO `instore` VALUES ('1806167348590198785', '2023-08-18T16:00:00.000Z', '黑MJ2996', 33, 660, '1', 33, 0, '2024-06-27 11:27:07', NULL, 0);
INSERT INTO `instore` VALUES ('1806167476281589762', '2023-08-19T16:00:00.000Z', '黑EK5309', 33, 660, '1', 33, 1, '2024-06-27 20:11:07', NULL, 0);

-- ----------------------------
-- Table structure for outstore
-- ----------------------------
DROP TABLE IF EXISTS `outstore`;
CREATE TABLE `outstore`  (
  `out_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `out_date` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `out_casenum` int(0) NOT NULL,
  `out_ton` double NOT NULL,
  `out_storeid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `out_cost` double NULL DEFAULT NULL,
  `out_exist` tinyint(1) NULL DEFAULT 0,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_memeber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`out_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of outstore
-- ----------------------------
INSERT INTO `outstore` VALUES ('1806167723728748546', '2023-08-20T16:00:00.000Z', 720, 36, '1', 126, 0, '2024-06-27 11:29:40', NULL);
INSERT INTO `outstore` VALUES ('1806168156035661825', '2023-08-20T16:00:00.000Z', 600, 30, '1', 94.5, 0, '2024-06-27 11:31:26', NULL);
INSERT INTO `outstore` VALUES ('1806168298340007938', '2023-08-31T16:00:00.000Z', 720, 36, '1', 18, 0, '2024-06-27 11:31:32', NULL);

-- ----------------------------
-- Table structure for store
-- ----------------------------
DROP TABLE IF EXISTS `store`;
CREATE TABLE `store`  (
  `store_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  `store_ton` double NOT NULL DEFAULT 0,
  `store_casenum` int(0) NOT NULL DEFAULT 0,
  `store_belongport` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `store_belongcompany` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `store_exist` tinyint(1) NULL DEFAULT 0,
  `update_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0),
  `update_memeber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`store_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of store
-- ----------------------------
INSERT INTO `store` VALUES ('1', 60, 1200, '大连港', 'DMT', 0, '2024-06-27 20:11:07', NULL);
INSERT INTO `store` VALUES ('2', 0, 0, '大连港', 'MNC', 0, '2024-06-27 11:22:04', NULL);

SET FOREIGN_KEY_CHECKS = 1;

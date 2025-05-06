/*
 Navicat Premium Dump SQL

 Source Server         : myboot
 Source Server Type    : MySQL
 Source Server Version : 80039 (8.0.39)
 Source Host           : localhost:3306
 Source Schema         : user

 Target Server Type    : MySQL
 Target Server Version : 80039 (8.0.39)
 File Encoding         : 65001

 Date: 06/05/2025 17:15:29
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for arcs
-- ----------------------------
DROP TABLE IF EXISTS `arcs`;
CREATE TABLE `arcs`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `number` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of arcs
-- ----------------------------
INSERT INTO `arcs` VALUES (1, 'yuchen', 6);
INSERT INTO `arcs` VALUES (2, 'samhan', 4);
INSERT INTO `arcs` VALUES (3, 'yuchen1', 2);
INSERT INTO `arcs` VALUES (4, 'smx', 1);
INSERT INTO `arcs` VALUES (5, 'gjy', 1);
INSERT INTO `arcs` VALUES (6, 'gjy2', 2);
INSERT INTO `arcs` VALUES (7, 'dhr', 3);
INSERT INTO `arcs` VALUES (8, 'dhr2', 0);

-- ----------------------------
-- Table structure for articles
-- ----------------------------
DROP TABLE IF EXISTS `articles`;
CREATE TABLE `articles`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` int NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`id`, `username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of articles
-- ----------------------------
INSERT INTO `articles` VALUES ('dhr', 1, 'nnsnsns', 'nnnnnnnnn');
INSERT INTO `articles` VALUES ('gjy', 1, '你好', '说大话谁都会是多少都会');
INSERT INTO `articles` VALUES ('gjy2', 1, 'sasasasa', 'sassassasasa');
INSERT INTO `articles` VALUES ('samhan', 1, '旅行记录', '行到水穷处，坐看云起时。');
INSERT INTO `articles` VALUES ('smx', 1, 'uhuhuhuhuu', 'sndauinduianduianduaidnaw');
INSERT INTO `articles` VALUES ('yuchen', 1, '第一篇诗', '白日依山尽，黄河入海流。欲穷千里目，更上一层楼。哈哈哈');
INSERT INTO `articles` VALUES ('yuchen1', 1, '观景记', '欲把西湖比西子，淡妆浓抹总相宜。');
INSERT INTO `articles` VALUES ('dhr', 2, 'dhnnsannsan', 'sndjaiudnawidnadnuiawd');
INSERT INTO `articles` VALUES ('gjy2', 2, 'sasasasas', 'asasasasasas');
INSERT INTO `articles` VALUES ('samhan', 2, '生活感悟', '不以物喜，不以己悲。');
INSERT INTO `articles` VALUES ('yuchen', 2, '第二篇诗', '锄禾日当午，汗滴禾下土。谁知盘中餐，粒粒皆辛苦。无限风光，一日看尽。望尽天涯路，云无绝尽时。');
INSERT INTO `articles` VALUES ('yuchen1', 2, '读书笔记', '读书百遍，其义自见。');
INSERT INTO `articles` VALUES ('dhr', 3, 'dnsjdnabduassbd', 'sandjanfuiansiufniaufn');
INSERT INTO `articles` VALUES ('samhan', 3, '学习心得', '业精于勤，荒于嬉。行成于思，毁于随。');
INSERT INTO `articles` VALUES ('yuchen', 3, '第三篇诗', '阳春布德泽，万物生光辉。常恐秋节至，焜黄华叶衰。百川东到海，何时复西归？少壮不努力，老大徒伤悲。');
INSERT INTO `articles` VALUES ('yuchen', 4, '第四篇', '还是双手合十水水水水');
INSERT INTO `articles` VALUES ('yuchen', 5, 'xxxx', 'xxxxxxx');
INSERT INTO `articles` VALUES ('yuchen', 6, 'xxxxxxxx', 'xxxxxxxxxx');

-- ----------------------------
-- Table structure for user_data
-- ----------------------------
DROP TABLE IF EXISTS `user_data`;
CREATE TABLE `user_data`  (
  `owner_username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `id` int NOT NULL,
  `date` datetime NULL DEFAULT NULL,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `city` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `area` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `zip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`owner_username`, `id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_data
-- ----------------------------
INSERT INTO `user_data` VALUES ('samhan', 1, '2016-05-03 08:00:00', '张小界', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('samhan', 2, '2016-05-03 08:00:00', '张小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('samhan', 3, '2016-05-03 08:00:00', '刘小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('samhan', 4, '2016-05-03 08:00:00', '李小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 1, '2016-05-03 08:00:00', '王小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 2, '2016-05-03 08:00:00', '张小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 3, '2016-05-03 08:00:00', '张勇常', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 4, '2016-05-03 08:00:00', '李小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 5, '2016-05-03 08:00:00', '赵小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 6, '2016-05-03 08:00:00', '杨小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 7, '2016-05-03 08:00:00', '周小虎', '上海', '普陀区', '上海市普陀区金沙江路 15', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 8, '2025-05-02 00:00:00', '王小三', '上海', '上海', '武汉佳和宾馆南湖店', '200333');
INSERT INTO `user_data` VALUES ('yuchen', 9, '2025-05-08 00:00:00', '太阳星辰2', '上海', '上海', 'dafafa', 'adad');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `confirm_password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `birthday` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `url` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('dhr', '123456', '123456', '15231712131@163.com', '2025-05-08', '202505061654144b8d61b68d6e4485b5b650f8e468e8d9.jpg');
INSERT INTO `users` VALUES ('dhr2', '123456', '123456', 'bneuj334422@hotmail.com', '2025-05-08', '20250506170659c9e3849907fe4e5898a96083a662c422.jpg');
INSERT INTO `users` VALUES ('gjy', '123456789', '123456', '15231712131@163.com', '2025-05-08', '202505061646584bf3faa226d6453d922391f9c2f11cc4.jpg');
INSERT INTO `users` VALUES ('gjy2', '123456', '123456', '15231712131@163.com', '2025-05-08', '20250506165251e7101c3db106447d9f6e4ca9c92187fd.jpg');
INSERT INTO `users` VALUES ('samhan', '123456789', '123456', '15231712131@163.com', '2004-09-18', '8710BDC452E4ECFC45F4364EAB93C3FD.jpg');
INSERT INTO `users` VALUES ('smx', '123456789', '123456', 'sybpd492561@outlook.com', '2005-09-20', '8710BDC452E4ECFC45F4364EAB93C3FD.jpg');
INSERT INTO `users` VALUES ('yuchen', '123456', '123456', '15231712131@163.com', '2005-09-20', 'Cache_348d251894231a24.jpg');
INSERT INTO `users` VALUES ('yuchen1', '123456', '123456', 'maysweb5245@hotmail.com', '2005-09-20', 'Cache_348d251894231a24.jpg');

SET FOREIGN_KEY_CHECKS = 1;

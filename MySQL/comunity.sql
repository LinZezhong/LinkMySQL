/*
Navicat MySQL Data Transfer

Source Server         : localhost_3308
Source Server Version : 50703
Source Host           : localhost:3308
Source Database       : comunity

Target Server Type    : MYSQL
Target Server Version : 50703
File Encoding         : 65001

Date: 2017-12-24 23:29:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `activity_org`
-- ----------------------------
DROP TABLE IF EXISTS `activity_org`;
CREATE TABLE `activity_org` (
  `Activity_Org_Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `User_Id` char(12) NOT NULL,
  `Activity_Name` varchar(40) NOT NULL,
  `Activity_Time` datetime NOT NULL,
  `Activity_Addr` varchar(20) NOT NULL,
  `Activity_Num` int(4) NOT NULL,
  `Activity_Intro` varchar(250) NOT NULL,
  `Org_Id` char(8) NOT NULL,
  `Org_Department_Id` int(4) unsigned zerofill NOT NULL,
  `Activity_State` enum('已结束','进行中','将进行') NOT NULL,
  PRIMARY KEY (`Activity_Org_Id`),
  KEY `FK_Act_Org_User` (`User_Id`),
  KEY `FK_Act_Org_Org` (`Org_Id`),
  KEY `FK_Act_Org_Department` (`Org_Department_Id`),
  CONSTRAINT `FK_Act_Org_Department` FOREIGN KEY (`Org_Department_Id`) REFERENCES `org_department` (`Org_Department_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Act_Org_Org` FOREIGN KEY (`Org_Id`) REFERENCES `organization` (`Org_Id`) ON UPDATE CASCADE,
  CONSTRAINT `FK_Act_Org_User` FOREIGN KEY (`User_Id`) REFERENCES `userinfo` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_org
-- ----------------------------

-- ----------------------------
-- Table structure for `activity_personl`
-- ----------------------------
DROP TABLE IF EXISTS `activity_personl`;
CREATE TABLE `activity_personl` (
  `Activity_Per_Id` int(8) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `User_Id` char(12) NOT NULL,
  `Activity_Name` varchar(40) NOT NULL,
  `Activity_Time` datetime NOT NULL,
  `Activity_Addr` varchar(20) NOT NULL,
  `Activity_Num` int(4) NOT NULL,
  `Activity_Intro` varchar(20) NOT NULL,
  `Activity_State` enum('已结束','进行中','将进行') NOT NULL DEFAULT '将进行',
  PRIMARY KEY (`Activity_Per_Id`),
  KEY `FK_Acti_Per_User` (`User_Id`),
  CONSTRAINT `FK_Acti_Per_User` FOREIGN KEY (`User_Id`) REFERENCES `userinfo` (`User_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_personl
-- ----------------------------

-- ----------------------------
-- Table structure for `college`
-- ----------------------------
DROP TABLE IF EXISTS `college`;
CREATE TABLE `college` (
  `Col_Id` char(8) NOT NULL,
  `Col_Name` varchar(20) NOT NULL,
  PRIMARY KEY (`Col_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of college
-- ----------------------------
INSERT INTO `college` VALUES ('10000000', '集美大学');
INSERT INTO `college` VALUES ('10000001', '计算机工程学院');
INSERT INTO `college` VALUES ('10000002', '信息工程学院');

-- ----------------------------
-- Table structure for `organization`
-- ----------------------------
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization` (
  `Org_Id` char(8) NOT NULL,
  `Org_Name` varchar(20) NOT NULL,
  `Org_Intro` varchar(250) DEFAULT NULL,
  `Org_Level` enum('校级','院级') NOT NULL DEFAULT '院级',
  `Col_Id` char(8) DEFAULT NULL,
  PRIMARY KEY (`Org_Id`),
  KEY `FK_Org_Col` (`Col_Id`),
  CONSTRAINT `FK_Org_Col` FOREIGN KEY (`Col_Id`) REFERENCES `college` (`Col_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of organization
-- ----------------------------
INSERT INTO `organization` VALUES ('10000001', '学生会', '校学生会', '校级', '10000000');
INSERT INTO `organization` VALUES ('10000002', '自律会', '计算机自律会', '院级', '10000001');
INSERT INTO `organization` VALUES ('10000003', '青年志愿者协会', '校青年志愿者协会', '校级', '10000000');
INSERT INTO `organization` VALUES ('10000004', '软件协会', '计算机软件协会', '院级', '10000001');
INSERT INTO `organization` VALUES ('10000005', '红十字会', '红十字会，血库建造协助', '校级', '10000000');

-- ----------------------------
-- Table structure for `org_apply`
-- ----------------------------
DROP TABLE IF EXISTS `org_apply`;
CREATE TABLE `org_apply` (
  `Org_Id` char(8) NOT NULL,
  `User_Id` char(12) NOT NULL,
  `Org_Apply_Reason` varchar(250) DEFAULT NULL,
  `Org_Department_Id` int(4) NOT NULL,
  `State` enum('未审核','已审核') NOT NULL DEFAULT '未审核',
  `Result` enum('未通过','通过','未知') DEFAULT '未知',
  `Message` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`Org_Id`,`User_Id`,`Org_Department_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_apply
-- ----------------------------
INSERT INTO `org_apply` VALUES ('10000001', '1234', '喜欢', '1', '已审核', '未知', null);
INSERT INTO `org_apply` VALUES ('10000001', '1234', '乐意', '2', '未审核', '未知', null);
INSERT INTO `org_apply` VALUES ('10000001', '201521121061', '喜欢行不', '1', '未审核', '未知', null);
INSERT INTO `org_apply` VALUES ('10000001', '201521121073', '擅长宣传', '1', '已审核', '未通过', '很抱歉');
INSERT INTO `org_apply` VALUES ('10000001', '201521121075', '乐意', '1', '已审核', '通过', '周五下午面试');
INSERT INTO `org_apply` VALUES ('10000001', '201521121075', '乐意', '2', '未审核', '未知', null);
INSERT INTO `org_apply` VALUES ('10000001', '201521121076', '喜欢', '1', '已审核', '通过', '面试');

-- ----------------------------
-- Table structure for `org_department`
-- ----------------------------
DROP TABLE IF EXISTS `org_department`;
CREATE TABLE `org_department` (
  `Org_Department_Id` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Org_Id` char(8) NOT NULL,
  `Org_Department_Name` varchar(40) NOT NULL,
  PRIMARY KEY (`Org_Department_Id`),
  KEY `FK_Department_Org` (`Org_Id`),
  CONSTRAINT `FK_Department_Org` FOREIGN KEY (`Org_Id`) REFERENCES `organization` (`Org_Id`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_department
-- ----------------------------
INSERT INTO `org_department` VALUES ('0001', '10000001', '宣传部');
INSERT INTO `org_department` VALUES ('0002', '10000001', '外交部');

-- ----------------------------
-- Table structure for `org_level`
-- ----------------------------
DROP TABLE IF EXISTS `org_level`;
CREATE TABLE `org_level` (
  `Org_Level_Id` int(4) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `Org_Level_Name` varchar(20) NOT NULL,
  PRIMARY KEY (`Org_Level_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_level
-- ----------------------------

-- ----------------------------
-- Table structure for `org_person`
-- ----------------------------
DROP TABLE IF EXISTS `org_person`;
CREATE TABLE `org_person` (
  `Org_Id` char(8) NOT NULL,
  `User_Id` char(12) NOT NULL,
  `Org_Level_Id` int(4) unsigned zerofill NOT NULL,
  `Org_Department_Id` int(4) unsigned zerofill NOT NULL,
  PRIMARY KEY (`Org_Id`,`User_Id`,`Org_Department_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of org_person
-- ----------------------------
INSERT INTO `org_person` VALUES ('10000001', '1234', '0004', '0001');

-- ----------------------------
-- Table structure for `userinfo`
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo` (
  `User_Sex` enum('女','男') DEFAULT NULL,
  `User_Id` char(12) NOT NULL,
  `User_Pwd` varchar(14) NOT NULL,
  `User_Name` varchar(8) DEFAULT NULL,
  `Col_Id` char(8) DEFAULT NULL,
  `User_Phone` char(11) DEFAULT NULL,
  `User_Like` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`User_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES ('男', '1234', '1234', '鸡蛋花', '10000002', '13244356475', '未设置');
INSERT INTO `userinfo` VALUES ('男', '201521121061', '12345678', 'abc', '10000001', '13256475643', '踢球');
INSERT INTO `userinfo` VALUES ('男', '201521121073', 'lzz19961015', '王浩', '10000001', '13255986731', '打篮球');
INSERT INTO `userinfo` VALUES ('男', '201521121075', 'lzz19961015', '林泽中', '10000001', '18359796309', '玩游戏');
INSERT INTO `userinfo` VALUES ('男', '201521121076', 'lzz19961015', '何温森', '10000001', '13224772236', '荒野行动');

-- ----------------------------
-- View structure for `org_apply_user`
-- ----------------------------
DROP VIEW IF EXISTS `org_apply_user`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `org_apply_user` AS select `org_apply`.`Org_Id` AS `Org_Id`,`org_apply`.`User_Id` AS `User_Id`,`org_apply`.`Org_Apply_Reason` AS `Org_Apply_Reason`,`org_apply`.`Org_Department_Id` AS `Org_Department_Id`,`org_apply`.`State` AS `State`,`userinfo`.`User_Sex` AS `User_Sex`,`userinfo`.`User_Name` AS `User_Name`,`userinfo`.`Col_Id` AS `Col_Id`,`userinfo`.`User_Phone` AS `User_Phone`,`userinfo`.`User_Like` AS `User_Like`,`org_apply`.`Result` AS `result`,`org_apply`.`Message` AS `message` from (`org_apply` left join `userinfo` on((`org_apply`.`User_Id` = `userinfo`.`User_Id`))) ;

-- ----------------------------
-- View structure for `org_person_org_depart_view`
-- ----------------------------
DROP VIEW IF EXISTS `org_person_org_depart_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `org_person_org_depart_view` AS select `org_department`.`Org_Id` AS `Org_Id`,`org_person_org_view`.`User_Id` AS `User_Id`,`org_person_org_view`.`Org_Level_Id` AS `Org_Level_Id`,`org_department`.`Org_Department_Id` AS `Org_Department_Id`,`org_person_org_view`.`Org_Intro` AS `Org_Intro`,`org_person_org_view`.`Org_Name` AS `Org_Name`,`org_person_org_view`.`Org_Level` AS `Org_Level`,`org_person_org_view`.`Col_Id` AS `Col_Id`,`org_department`.`Org_Department_Name` AS `Org_Department_Name` from (`org_person_org_view` left join `org_department` on((`org_person_org_view`.`Org_Department_Id` = `org_department`.`Org_Department_Id`))) ;

-- ----------------------------
-- View structure for `org_person_org_view`
-- ----------------------------
DROP VIEW IF EXISTS `org_person_org_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `org_person_org_view` AS select `org_person`.`Org_Id` AS `Org_Id`,`org_person`.`User_Id` AS `User_Id`,`org_person`.`Org_Level_Id` AS `Org_Level_Id`,`org_person`.`Org_Department_Id` AS `Org_Department_Id`,`organization`.`Org_Intro` AS `Org_Intro`,`organization`.`Org_Name` AS `Org_Name`,`organization`.`Org_Level` AS `Org_Level`,`organization`.`Col_Id` AS `Col_Id` from (`org_person` left join `organization` on((`org_person`.`Org_Id` = `organization`.`Org_Id`))) ;

-- ----------------------------
-- View structure for `user_apply_depart_view`
-- ----------------------------
DROP VIEW IF EXISTS `user_apply_depart_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_apply_depart_view` AS select `user_org_apply_info_view`.`Org_Id` AS `Org_Id`,`user_org_apply_info_view`.`User_Id` AS `User_Id`,`user_org_apply_info_view`.`org_apply_Reason` AS `org_apply_Reason`,`user_org_apply_info_view`.`Org_Department_Id` AS `Org_Department_Id`,`user_org_apply_info_view`.`State` AS `State`,`user_org_apply_info_view`.`User_Sex` AS `User_Sex`,`user_org_apply_info_view`.`User_Name` AS `User_Name`,`user_org_apply_info_view`.`Col_Id` AS `Col_Id`,`user_org_apply_info_view`.`User_Phone` AS `User_Phone`,`user_org_apply_info_view`.`User_Like` AS `User_Like`,`user_org_apply_info_view`.`result` AS `result`,`user_org_apply_info_view`.`message` AS `message`,`user_org_apply_info_view`.`Col_Name` AS `Col_Name`,`user_org_apply_info_view`.`Org_Name` AS `Org_Name`,`org_department`.`Org_Department_Name` AS `Org_Department_Name` from (`user_org_apply_info_view` left join `org_department` on((`user_org_apply_info_view`.`Org_Department_Id` = `org_department`.`Org_Department_Id`))) ;

-- ----------------------------
-- View structure for `user_info_apply_info_view`
-- ----------------------------
DROP VIEW IF EXISTS `user_info_apply_info_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_info_apply_info_view` AS select `org_apply_user`.`Org_Id` AS `Org_Id`,`org_apply_user`.`User_Id` AS `User_Id`,`org_apply_user`.`Org_Apply_Reason` AS `Org_Apply_Reason`,`org_apply_user`.`Org_Department_Id` AS `Org_Department_Id`,`org_apply_user`.`State` AS `State`,`org_apply_user`.`User_Sex` AS `User_Sex`,`org_apply_user`.`User_Name` AS `User_Name`,`college`.`Col_Id` AS `Col_Id`,`org_apply_user`.`User_Phone` AS `User_Phone`,`org_apply_user`.`User_Like` AS `User_Like`,`org_apply_user`.`result` AS `result`,`org_apply_user`.`message` AS `message`,`college`.`Col_Name` AS `Col_Name` from (`org_apply_user` left join `college` on((`org_apply_user`.`Col_Id` = `college`.`Col_Id`))) ;

-- ----------------------------
-- View structure for `user_org_apply_info_view`
-- ----------------------------
DROP VIEW IF EXISTS `user_org_apply_info_view`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `user_org_apply_info_view` AS select `user_info_apply_info_view`.`Org_Id` AS `Org_Id`,`user_info_apply_info_view`.`User_Id` AS `User_Id`,`user_info_apply_info_view`.`Org_Apply_Reason` AS `org_apply_Reason`,`user_info_apply_info_view`.`Org_Department_Id` AS `Org_Department_Id`,`user_info_apply_info_view`.`State` AS `State`,`user_info_apply_info_view`.`User_Sex` AS `User_Sex`,`user_info_apply_info_view`.`User_Name` AS `User_Name`,`user_info_apply_info_view`.`Col_Id` AS `Col_Id`,`user_info_apply_info_view`.`User_Phone` AS `User_Phone`,`user_info_apply_info_view`.`User_Like` AS `User_Like`,`user_info_apply_info_view`.`result` AS `result`,`user_info_apply_info_view`.`message` AS `message`,`user_info_apply_info_view`.`Col_Name` AS `Col_Name`,`organization`.`Org_Name` AS `Org_Name` from (`user_info_apply_info_view` left join `organization` on((`user_info_apply_info_view`.`Org_Id` = `organization`.`Org_Id`))) ;

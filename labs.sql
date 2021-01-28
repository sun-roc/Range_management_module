/*
Navicat MySQL Data Transfer

Source Server         : tests
Source Server Version : 50728
Source Host           : localhost:3306
Source Database       : labs

Target Server Type    : MYSQL
Target Server Version : 50728
File Encoding         : 65001

Date: 2020-04-28 19:54:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for competition_event
-- ----------------------------
DROP TABLE IF EXISTS `competition_event`;
CREATE TABLE `competition_event` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `competition_event_code` varchar(20) DEFAULT NULL COMMENT '比赛项目编码',
  `competition_event_name` varchar(20) DEFAULT NULL COMMENT '比赛项目名称',
  `suite_type` int(10) DEFAULT NULL COMMENT '组别',
  `range_code` varchar(20) DEFAULT NULL COMMENT '场地编码',
  `plan_start_at` date DEFAULT NULL COMMENT '开始日期',
  `plan_end_at` date DEFAULT NULL COMMENT '结束日期',
  `status` int(10) DEFAULT NULL COMMENT '1: 未开始 2：进行中 3：已结束',
  `created_at` datetime DEFAULT NULL,
  `updated_at` datetime DEFAULT NULL,
  `created_by` varchar(50) DEFAULT NULL,
  `updated_by` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of competition_event
-- ----------------------------
INSERT INTO `competition_event` VALUES ('1', null, '射击', null, 'RG2004280005', null, null, '2', null, null, null, null);
INSERT INTO `competition_event` VALUES ('2', null, '游泳', null, 'RG2004110002', null, null, '2', null, null, null, null);

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tenant_code` varchar(20) NOT NULL COMMENT '租户代码',
  `department_code` varchar(50) DEFAULT NULL COMMENT '部门代码',
  `department_name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `contact` varchar(50) DEFAULT NULL COMMENT '联系人',
  `contact_phone` varchar(100) DEFAULT NULL COMMENT '联系电话',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '联系电话',
  `deleted` tinyint(4) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('1', '001', 'DP1912030007', '通信所', '张三', null, '这是一条备注', '2019-12-03 17:31:28', '2020-04-11 15:56:49', 'TE00000001', '001', '0');
INSERT INTO `department` VALUES ('2', '001', 'DP1912030008', '通信所', '张三', '1532384234234', '这是一条备注', '2019-12-03 17:48:06', '2019-12-03 17:48:06', 'TE00000001', 'TE00000001', '0');
INSERT INTO `department` VALUES ('3', '001', 'DP2003280001', 'strsssssssing', 'string', 'string', 'string', '2020-03-28 21:04:41', '2020-04-07 17:08:06', 'TE000001', '001', '0');
INSERT INTO `department` VALUES ('4', '001', 'DP2004040001', 'string', 'string', 'string', 'string', '2020-04-04 11:47:27', '2020-04-08 09:24:12', 'TE000001', 'TE000001', '1');

-- ----------------------------
-- Table structure for key_max_value
-- ----------------------------
DROP TABLE IF EXISTS `key_max_value`;
CREATE TABLE `key_max_value` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenant_code` varchar(20) DEFAULT NULL COMMENT '租户代码',
  `key_prefix` char(2) DEFAULT NULL COMMENT '业务主键前缀',
  `date_part` char(6) DEFAULT NULL COMMENT '日期',
  `current_value` int(11) DEFAULT '1' COMMENT '业务后缀',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk` (`tenant_code`,`key_prefix`,`date_part`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of key_max_value
-- ----------------------------
INSERT INTO `key_max_value` VALUES ('32', '001', 'DP', '191203', '8', '2019-12-03 10:40:28');
INSERT INTO `key_max_value` VALUES ('33', '001', 'DP', '200328', '1', '2020-03-28 21:04:41');
INSERT INTO `key_max_value` VALUES ('34', '001', 'RG', '200404', '18', '2020-04-04 11:44:58');
INSERT INTO `key_max_value` VALUES ('35', '001', 'DP', '200404', '10', '2020-04-04 11:47:27');
INSERT INTO `key_max_value` VALUES ('36', '001', 'RG', '200405', '1', '2020-04-05 12:07:56');
INSERT INTO `key_max_value` VALUES ('37', '001', 'RG', '200406', '3', '2020-04-06 17:52:56');
INSERT INTO `key_max_value` VALUES ('38', '001', 'RG', '200407', '1', '2020-04-07 16:58:24');
INSERT INTO `key_max_value` VALUES ('39', '001', 'RG', '200408', '31', '2020-04-08 13:56:33');
INSERT INTO `key_max_value` VALUES ('40', '001', 'RG', '200411', '3', '2020-04-11 15:49:57');
INSERT INTO `key_max_value` VALUES ('41', '001', 'RG', '200418', '3', '2020-04-18 19:41:47');
INSERT INTO `key_max_value` VALUES ('42', '001', 'RG', '200421', '1', '2020-04-21 21:05:23');
INSERT INTO `key_max_value` VALUES ('43', '001', 'RG', '200425', '1', '2020-04-25 15:02:33');
INSERT INTO `key_max_value` VALUES ('44', '001', 'RG', '200428', '9', '2020-04-28 19:07:14');

-- ----------------------------
-- Table structure for range
-- ----------------------------
DROP TABLE IF EXISTS `range`;
CREATE TABLE `range` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `range_code` varchar(20) DEFAULT NULL COMMENT '场地编码',
  `range_name` varchar(100) DEFAULT NULL COMMENT '场地名称',
  `range_location` varchar(100) DEFAULT NULL COMMENT '场地位置',
  `status` int(11) DEFAULT NULL COMMENT '场地状态',
  `close_reason` varchar(100) DEFAULT NULL COMMENT '关闭原因',
  `description` varchar(255) DEFAULT NULL COMMENT '备注',
  `created_at` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_at` datetime DEFAULT NULL COMMENT '更新时间',
  `created_by` varchar(50) DEFAULT NULL COMMENT '创建人',
  `updated_by` varchar(50) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of range
-- ----------------------------
INSERT INTO `range` VALUES ('38', 'RG2004110002', '游泳馆(比赛进行中无法删除)', 'n12', '2', null, null, '2020-04-11 15:52:40', '2020-04-11 15:53:13', 'TE000001', '001');
INSERT INTO `range` VALUES ('45', 'RG2004280005', '射击馆', '123', '2', null, null, '2020-04-28 19:10:12', '2020-04-28 19:10:12', 'TE000001', 'TE000001');
INSERT INTO `range` VALUES ('46', 'RG2004280006', '摔跤馆', 'string', '1', null, null, '2020-04-28 19:10:47', '2020-04-28 19:10:47', 'TE000001', 'TE000001');
INSERT INTO `range` VALUES ('47', 'RG2004280007', '体育馆', 'string', '3', null, null, '2020-04-28 19:11:06', '2020-04-28 19:11:06', 'TE000001', 'TE000001');
INSERT INTO `range` VALUES ('48', 'RG2004280008', '田径场', 'string', '3', null, null, '2020-04-28 19:11:18', '2020-04-28 19:11:18', 'TE000001', 'TE000001');
INSERT INTO `range` VALUES ('49', 'RG2004280009', '羽毛球馆', 'string', '1', null, null, '2020-04-28 19:11:42', '2020-04-28 19:11:42', 'TE000001', 'TE000001');

-- ----------------------------
-- Table structure for supply
-- ----------------------------
DROP TABLE IF EXISTS `supply`;
CREATE TABLE `supply` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `title` varchar(50) DEFAULT NULL COMMENT '供应标题',
  `category_id` int(11) DEFAULT NULL COMMENT '对应品类',
  `province` varchar(6) DEFAULT NULL COMMENT '省级编号',
  `city` varchar(6) DEFAULT NULL COMMENT '市级编号',
  `district` varchar(6) DEFAULT NULL COMMENT '镇/区编号',
  `address` varchar(100) DEFAULT NULL COMMENT '详细地址',
  `property` varchar(100) DEFAULT NULL COMMENT '供应属性',
  `description` varchar(1000) DEFAULT NULL COMMENT '商品描述',
  `sell_date_type` int(11) DEFAULT NULL COMMENT '供货日期类型',
  `sell_date` datetime DEFAULT NULL COMMENT '供货日期',
  `specification_name` varchar(100) DEFAULT NULL COMMENT '最低价规格名称',
  `unit` int(11) DEFAULT NULL COMMENT '最低价单位',
  `price` decimal(20,2) DEFAULT NULL COMMENT '最低价价格',
  `starting_value` int(11) DEFAULT NULL COMMENT '最低价起批量',
  `transport_type` int(11) DEFAULT NULL COMMENT '最低价运费设置',
  `clicks` int(11) DEFAULT NULL COMMENT '点击次数',
  `amount` decimal(20,2) DEFAULT '0.00' COMMENT '成交额',
  `created_by` int(11) DEFAULT NULL COMMENT '创建人',
  `updated_by` int(11) DEFAULT NULL COMMENT '更新人',
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `deleted` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='供应';

-- ----------------------------
-- Records of supply
-- ----------------------------

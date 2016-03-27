/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50519
Source Host           : localhost:3306
Source Database       : kspt

Target Server Type    : MYSQL
Target Server Version : 50519
File Encoding         : 65001

Date: 2016-03-27 21:14:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bo_fcxx`
-- ----------------------------
DROP TABLE IF EXISTS `bo_fcxx`;
CREATE TABLE `bo_fcxx` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `bind_id` varchar(128) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(30) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_user` varchar(30) DEFAULT NULL,
  `yhm` varchar(100) DEFAULT NULL,
  `dh` varchar(100) DEFAULT NULL,
  `sfzh` varchar(100) DEFAULT NULL,
  `sfzzp` text,
  `fczzp` text,
  `qtfj` text,
  `fcdz` text,
  `yx` varchar(100) DEFAULT NULL,
  `xm` varchar(100) DEFAULT NULL,
  `spkgm` varchar(100) DEFAULT NULL,
  `fczbh` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_fcxx
-- ----------------------------
INSERT INTO `bo_fcxx` VALUES ('313', '8c85b8038ca8497d80822346d87e8d70', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('314', 'd1829bf5d2ff49ff8b040b880f562377', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('315', '55a0521304b5495da1d72442903d6c4b', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('316', 'cbd93798b6884a18b7868da1f85ccc4e', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('317', 'fbe0761693cd4e4e9683d411fc2ab5df', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('318', 'bf024d7c185b46ef900aad8a17457288', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('319', '8d8e15bc61064c99834d2227c40ddd8d', '2015-11-23 20:49:41', '11', '2015-11-23 20:49:41', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('320', '2bfc4cb5110c4615aee3b60da9eb58bb', '2015-11-23 20:49:42', '11', '2015-11-23 20:49:42', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);
INSERT INTO `bo_fcxx` VALUES ('321', '425b7ce345074359b078022e052f995c', '2015-11-23 20:49:42', '11', '2015-11-23 20:49:42', null, null, '1', null, null, null, null, '1', '1', '1', '1', null);

-- ----------------------------
-- Table structure for `bo_kdj`
-- ----------------------------
DROP TABLE IF EXISTS `bo_kdj`;
CREATE TABLE `bo_kdj` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `bind_id` varchar(128) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(30) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_user` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_kdj
-- ----------------------------

-- ----------------------------
-- Table structure for `bo_yhlb`
-- ----------------------------
DROP TABLE IF EXISTS `bo_yhlb`;
CREATE TABLE `bo_yhlb` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `bind_id` varchar(128) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(30) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_user` varchar(30) DEFAULT NULL,
  `yhm` varchar(100) DEFAULT NULL,
  `xm` varchar(100) DEFAULT NULL,
  `dh` varchar(100) DEFAULT NULL,
  `mm` varchar(100) DEFAULT NULL,
  `yx` varchar(100) DEFAULT NULL,
  `sfzh` text,
  `sfzzp` text,
  `fczzp` text,
  `qtfj` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_yhlb
-- ----------------------------
INSERT INTO `bo_yhlb` VALUES ('1', 'asdsa', '2015-05-17 08:52:30', null, '0000-00-00 00:00:00', null, 'manager', '管理员', '18210178959', 'E10ADC3949BA59ABBE56E057F20F883E', '', null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('2', null, '2015-05-18 09:54:24', null, '0000-00-00 00:00:00', null, '1', '1', '1', 'C4CA4238A0B923820DCC509A6F75849B', '1', null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('3', null, '2015-05-16 23:09:23', null, '0000-00-00 00:00:00', null, '11', '', null, '6512BD43D9CAA6E02C990B0A82652DCA', '', null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('4', null, '2015-05-16 23:09:23', null, '0000-00-00 00:00:00', null, 'c', '', null, '4A8A08F09D37B73795649038408B5F33', '', null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('5', null, '2015-05-16 23:09:23', null, '0000-00-00 00:00:00', null, 'c', '', null, '4A8A08F09D37B73795649038408B5F33', '', null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('6', null, '2015-05-19 14:16:21', null, '0000-00-00 00:00:00', null, 'qweq', 'eqweqw', 'eqe', 'C4CA4238A0B923820DCC509A6F75849B', null, null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('7', null, '2015-05-19 14:16:25', null, '0000-00-00 00:00:00', null, 'qweq', 'eqweqw', 'eqe', 'C4CA4238A0B923820DCC509A6F75849B', null, null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('8', null, '2015-05-19 14:19:45', null, '0000-00-00 00:00:00', null, '11111111', '1', '1', 'C4CA4238A0B923820DCC509A6F75849B', null, null, null, null, null);
INSERT INTO `bo_yhlb` VALUES ('9', null, '2015-05-19 14:20:47', null, '0000-00-00 00:00:00', null, '1212', '1', '1', 'C4CA4238A0B923820DCC509A6F75849B', null, null, null, null, null);

-- ----------------------------
-- Table structure for `bo_zczh`
-- ----------------------------
DROP TABLE IF EXISTS `bo_zczh`;
CREATE TABLE `bo_zczh` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `bind_id` varchar(128) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `create_user` varchar(30) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `update_user` varchar(30) DEFAULT NULL,
  `yhm` varchar(100) DEFAULT NULL,
  `xm` varchar(100) DEFAULT NULL,
  `dh` varchar(100) DEFAULT NULL,
  `yqm` varchar(100) DEFAULT NULL,
  `mm` varchar(100) DEFAULT NULL,
  `sptgsq` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bo_zczh
-- ----------------------------
INSERT INTO `bo_zczh` VALUES ('13', '5861eeb36acc4af297a65dcdb1712f01', '2015-05-15 16:07:18', null, '0000-00-00 00:00:00', null, '1', '1', null, null, 'C4CA4238A0B923820DCC509A6F75849B', '是');
INSERT INTO `bo_zczh` VALUES ('14', '83e12e90a1f444d68eb71bce908dc2f1', '2015-05-15 16:46:16', null, '0000-00-00 00:00:00', null, '11', '11', null, null, '6512BD43D9CAA6E02C990B0A82652DCA', '是');
INSERT INTO `bo_zczh` VALUES ('15', '124461bb7cfe4a6e992127a4a879861d', '2015-05-15 17:31:18', null, '0000-00-00 00:00:00', null, 'c', 'c', null, null, '4A8A08F09D37B73795649038408B5F33', '是');
INSERT INTO `bo_zczh` VALUES ('16', '9e57e629f0c942548c0562a550dde2c6', '2015-05-15 17:31:19', null, '0000-00-00 00:00:00', null, 'f', 'f', null, null, '8FA14CDD754F91CC6554C9E71929CCE7', '是');
INSERT INTO `bo_zczh` VALUES ('17', '1ea226d8fe4b408a8572da9280566f89', '2015-05-19 14:16:21', null, '0000-00-00 00:00:00', null, 'qweq', 'eqweqw', 'eqe', null, 'C4CA4238A0B923820DCC509A6F75849B', '是');
INSERT INTO `bo_zczh` VALUES ('18', 'b4bfe6c64d634090a4b8aee03c5a4e82', '2015-05-19 14:16:21', null, '0000-00-00 00:00:00', null, 'sa', 'aass', 'sssssssssssssss', null, '0CC175B9C0F1B6A831C399E269772661', '是');
INSERT INTO `bo_zczh` VALUES ('19', '6803598cd75a4defa4be5bb2878b892b', '2015-05-19 14:19:27', null, '0000-00-00 00:00:00', null, '11111111', '1', '1', null, 'C4CA4238A0B923820DCC509A6F75849B', '是');
INSERT INTO `bo_zczh` VALUES ('20', '6e69d3fea8b1409688015f112013eaaf', '2015-05-19 14:20:44', null, '0000-00-00 00:00:00', null, '1212', '1', '1', null, 'C4CA4238A0B923820DCC509A6F75849B', '是');
INSERT INTO `bo_zczh` VALUES ('21', 'c24d4fe3976a4ffeb4815e1dee274516', '2015-05-19 14:20:49', null, '0000-00-00 00:00:00', null, '333', '3', '3', null, 'ECCBC87E4B5CE2FE28308FD9F2A7BAF3', '是');
INSERT INTO `bo_zczh` VALUES ('22', '3d821da28c694c78a942bc59f7edde2f', '2015-05-19 14:28:35', null, '0000-00-00 00:00:00', null, '1212121', '1', '1', null, 'C4CA4238A0B923820DCC509A6F75849B', '是');
INSERT INTO `bo_zczh` VALUES ('23', '384a30d60f644a5ebce4ba81947b27f1', '2015-05-19 14:28:36', null, '0000-00-00 00:00:00', null, '33333', '33', '33', null, '182BE0C5CDCD5072BB1864CDEE4D3D6E', '是');
INSERT INTO `bo_zczh` VALUES ('24', '42defed3ad764c9fa50a6789f7a555f1', '2015-05-25 19:42:34', null, '0000-00-00 00:00:00', null, 'qqq', 'qqq', 'qqq', null, 'B2CA678B4C936F905FB82F2733F5297F', '是');
INSERT INTO `bo_zczh` VALUES ('25', 'fc495208eb1144a4b7a16191cb428c20', '2015-07-24 20:54:33', null, '0000-00-00 00:00:00', null, 'zhangman', '张曼', '11111111111', null, 'E10ADC3949BA59ABBE56E057F20F883E', '是');
INSERT INTO `bo_zczh` VALUES ('26', 'c2d1a9014db34f2e9c293e7b7d9bd759', '2015-09-06 21:55:58', null, '0000-00-00 00:00:00', null, '23', '23', '23', null, '6364D3F0F495B6AB9DCF8D3B5C6E0B01', '是');
INSERT INTO `bo_zczh` VALUES ('28', '8e67d67aaa934838948a61b7ed57f26d', '2015-09-07 22:08:37', null, '0000-00-00 00:00:00', null, '21', '12', '12', null, 'C20AD4D76FE97759AA27A0C99BFF6710', '否');
INSERT INTO `bo_zczh` VALUES ('29', null, '2015-10-11 01:32:31', null, '0000-00-00 00:00:00', null, '2222222222222222', '2222222222222222', '222222222222222222222222', '222222222', '22222222222', '2222222222');
INSERT INTO `bo_zczh` VALUES ('30', null, '2015-10-11 01:32:54', null, '0000-00-00 00:00:00', null, '111111111111111111111111111111111', '1111111111111111111111111111111111111111', '1111111111111111111111111111111111111111111111111', '1111111111111', '111111111111111111111111111111111111111', '11111111111111111111111111111111111111111111111111111');
INSERT INTO `bo_zczh` VALUES ('31', null, '2015-10-18 16:36:35', null, '0000-00-00 00:00:00', null, '', '', '', '', '', '');

-- ----------------------------
-- Table structure for `sys_sequence`
-- ----------------------------
DROP TABLE IF EXISTS `sys_sequence`;
CREATE TABLE `sys_sequence` (
  `sequence_name` char(100) NOT NULL,
  `sequence_value` decimal(16,0) DEFAULT NULL,
  `sequence_step` decimal(3,0) DEFAULT NULL,
  PRIMARY KEY (`sequence_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_sequence
-- ----------------------------
INSERT INTO `sys_sequence` VALUES ('1', '9', '1');
INSERT INTO `sys_sequence` VALUES ('12', '12', '12');
INSERT INTO `sys_sequence` VALUES ('work_list_button', '26', '1');
INSERT INTO `sys_sequence` VALUES ('zq_bo_metadata', '83', '1');
INSERT INTO `sys_sequence` VALUES ('zq_datalist', '250', '1');
INSERT INTO `sys_sequence` VALUES ('zq_datalist_column', '246', '1');
INSERT INTO `sys_sequence` VALUES ('zq_datalist_query', '68', '1');
INSERT INTO `sys_sequence` VALUES ('zq_form', '15', '1');
INSERT INTO `sys_sequence` VALUES ('zq_form_er', '28', '1');
INSERT INTO `sys_sequence` VALUES ('zq_form_ui', '164', '1');
INSERT INTO `sys_sequence` VALUES ('zq_metadatamapmap', '140', '1');
INSERT INTO `sys_sequence` VALUES ('zq_model_library', '22', '1');
INSERT INTO `sys_sequence` VALUES ('zq_nav_first', '20', '1');
INSERT INTO `sys_sequence` VALUES ('zq_nav_second', '34', '1');
INSERT INTO `sys_sequence` VALUES ('zq_nav_three', '34', '1');
INSERT INTO `sys_sequence` VALUES ('zq_org_company', '48', '1');
INSERT INTO `sys_sequence` VALUES ('zq_org_dept', '34', '1');
INSERT INTO `sys_sequence` VALUES ('zq_org_role', '11', '1');
INSERT INTO `sys_sequence` VALUES ('zq_org_user', '55', '1');

-- ----------------------------
-- Table structure for `zq_form`
-- ----------------------------
DROP TABLE IF EXISTS `zq_form`;
CREATE TABLE `zq_form` (
  `id` char(40) NOT NULL,
  `name` char(100) DEFAULT NULL,
  `model_lib_id` char(40) DEFAULT NULL,
  `master` char(100) DEFAULT NULL,
  `template_name` char(100) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `type` smallint(1) DEFAULT NULL COMMENT '表单类型0为平台表单1为sql表单',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_form
-- ----------------------------
INSERT INTO `zq_form` VALUES ('1c1cdf1f71564f1d9f3ca0aed48be2da', '测试', '289112d1c1a04708b71994aeb1a1ad53', '', '测试.ftl', '13', '1');
INSERT INTO `zq_form` VALUES ('5c5e6e746f604e87bd4c0b637985e4a2', 'asd', '289112d1c1a04708b71994aeb1a1ad53', '', 'asd.ftl', '15', '0');
INSERT INTO `zq_form` VALUES ('e860fce9a1b04647a0d2d4b0bff8e453', '快递', '289112d1c1a04708b71994aeb1a1ad53', '', '快递.ftl', '14', '0');

-- ----------------------------
-- Table structure for `zq_form_er`
-- ----------------------------
DROP TABLE IF EXISTS `zq_form_er`;
CREATE TABLE `zq_form_er` (
  `id` char(40) NOT NULL,
  `form_id` char(40) DEFAULT NULL,
  `metadata_id` char(40) DEFAULT NULL,
  `er_type` bigint(20) DEFAULT NULL,
  `page_size` bigint(20) DEFAULT NULL,
  `grid_order` char(200) DEFAULT NULL,
  `er_layer` bigint(20) DEFAULT NULL,
  `parent_er_id` char(40) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `grid_title` char(40) DEFAULT NULL,
  `grid_height` bigint(20) DEFAULT NULL,
  `table_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_form_er
-- ----------------------------
INSERT INTO `zq_form_er` VALUES ('0836eeccb82f4523ae39626f17605c7b', '65b58ef6c16c42618dfdae81bcb1dc0a', null, '0', '0', null, '1', null, '24', 'bo_fcxx', '300', 'bo_fcxx');
INSERT INTO `zq_form_er` VALUES ('12a87f0a3aae430d9786bbba7a4b6149', 'b0fbffe4b6744b0aad1ace67f689534d', '2407d199fddb404e8dcd025150999b8d', '1', '0', null, '2', '6454400a483f4db68b5669e7250d89f0', '11', 'sdas', '300', null);
INSERT INTO `zq_form_er` VALUES ('29474e584d564dc7a477dfbb7e04ce86', 'e860fce9a1b04647a0d2d4b0bff8e453', 'f69f07dcca0a47519e1e1defbff67a51', '0', '0', null, '1', null, '27', '快递柜', '300', null);
INSERT INTO `zq_form_er` VALUES ('5a17982c67024c85a54d775c4578f152', 'd2afe0051e864cf69f947835541cadf5', null, '0', '0', null, '1', null, '23', 'bo_fcxx', '300', 'bo_fcxx');
INSERT INTO `zq_form_er` VALUES ('6454400a483f4db68b5669e7250d89f0', 'b0fbffe4b6744b0aad1ace67f689534d', '6e25157dfd69406f90cafcd02fe811bc', '0', '0', null, '1', null, '10', '啊啊', '300', null);
INSERT INTO `zq_form_er` VALUES ('6a3ed3b9d70443fbbb1857dbf79bd6c9', 'f5408046042f474f80b20093f03f9109', '7b1d8951c4ab4881b08dd85224b7c7e3', '0', '0', null, '1', null, '21', '注册账户', '300', null);
INSERT INTO `zq_form_er` VALUES ('943ac278f61546b89dba02786d70ef1e', '5c5e6e746f604e87bd4c0b637985e4a2', '01691215376c40a5a473771f725f9c9e', '0', '0', null, '1', null, '28', '房产信息', '300', null);
INSERT INTO `zq_form_er` VALUES ('a3efd0f17d364415a1bddc5cd492713d', '6174fb4756ed4109ab0db5ab114e86e3', '01691215376c40a5a473771f725f9c9e', '0', '0', null, '1', null, '20', '房产信息', '300', null);
INSERT INTO `zq_form_er` VALUES ('b68b97b444cf427996c4cb39befb093b', 'c8631f961b0c48dd858d9e02020653bb', 'd9fca1dc40854378a86efdeab8eb504c', '0', '0', null, '1', null, '16', 'ceshi', '300', null);
INSERT INTO `zq_form_er` VALUES ('bb5a6e0c6ea24ce8937bfec7dc050dd7', '4cd1265ee8db420a831c84156e39bdb4', '39dd2d99233949cea65cba4f704bbdc3', '0', '0', null, '1', null, '22', '测试表单', '300', null);
INSERT INTO `zq_form_er` VALUES ('cc9f690c08e64e328cfa74ceea6a99be', '9960b3833f8148a8a55c1afd47546269', '11b3b28ec2e3443498b9ada308af4edd', '0', '0', null, '1', null, '12', 'ew', '300', null);
INSERT INTO `zq_form_er` VALUES ('e71582e7523241a9afceacf9fd76c158', '1c1cdf1f71564f1d9f3ca0aed48be2da', null, '0', '0', null, '1', null, '25', '房产税信息', '300', 'bo_fcxx');
INSERT INTO `zq_form_er` VALUES ('eed3f9db348f447ba57b67645e11eff0', '930aa52b933d4362b64ad85d6a18e8cc', '077ea9677331423295c75f722f1e53a5', '0', '0', null, '1', null, '15', '煞笔', '300', null);

-- ----------------------------
-- Table structure for `zq_form_ui`
-- ----------------------------
DROP TABLE IF EXISTS `zq_form_ui`;
CREATE TABLE `zq_form_ui` (
  `id` char(40) NOT NULL,
  `form_er_id` char(40) DEFAULT NULL,
  `metadata_map_id` char(40) DEFAULT NULL,
  `ui_title` char(100) DEFAULT NULL,
  `default_value` text,
  `is_null` bigint(20) DEFAULT NULL,
  `is_display` char(40) DEFAULT NULL,
  `ui_type` char(40) DEFAULT NULL,
  `ui_length` char(50) DEFAULT NULL,
  `ui_param` text,
  `ui_html` text,
  `order_index` bigint(20) DEFAULT NULL,
  `ui_name` char(60) DEFAULT NULL,
  `is_edit` bigint(20) DEFAULT NULL,
  `ui_length_type` char(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_form_ui
-- ----------------------------
INSERT INTO `zq_form_ui` VALUES ('03990a000fdf451b9faf4491f897f3d4', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '日期', '100', '', '', '121', 'create_date', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('088e3e10230546819d51296e611ca60b', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '131', 'fcdz', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('0b70b36018cf44dc990acc5deeb6b754', '943ac278f61546b89dba02786d70ef1e', '7147d056b6094a5f8b536042e26f048d', '邮箱', '', '1', '显示', '文本', '100', '', '', '161', 'yx', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('0bf5e54eaf384c75b48c44618abf058b', 'a3efd0f17d364415a1bddc5cd492713d', 'd5528c92503c46c48ead8207efea75b5', '身份证照片', '', '0', '显示', '文本', '100', '', '', '101', 'sfzzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('0ca17323aeab4c70a37e640835ccab6f', 'e71582e7523241a9afceacf9fd76c158', null, '姓名', '', '1', '显示', '文本', '100', '{\"type\":\"number\"}', '', '150', 'xm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('0d5b5f72c411449cb9f7a3a2f78bba3d', '943ac278f61546b89dba02786d70ef1e', '422e3c0128094fb180a328687f609b07', '其他附件', '', '1', '显示', '文本', '100', '', '', '159', 'qtfj', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('10ce201b24c34b9bb98f2f0297fabd40', 'e71582e7523241a9afceacf9fd76c158', null, '邮箱', '', '0', '显示', '文本', '100', '', '', '149', 'yx', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('1542d77883794b319ff6267e72fab98e', '943ac278f61546b89dba02786d70ef1e', '92dc8f4b34144631b38f426a3f8ecd8e', '电话', '', '1', '显示', '文本', '100', '', '', '155', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('18136ffd3b474da5a68c77cc1de61641', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '日期', '100', '', '', '123', 'update_date', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('18cbd9e9f0fa4c6987308593735fccd7', 'b68b97b444cf427996c4cb39befb093b', 'ee16a5ee843a4148994c7aa98cd8afb7', '电话', '', '1', '显示', '文本', '100', '', '', '76', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('22c8ba52635042c8b0e777503a6b1b76', 'eed3f9db348f447ba57b67645e11eff0', '51338aff27aa45d988de2351b4a89b46', 'a', '', '1', '显示', '数值', '100', '', '', '70', 'a', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('258e684abc3e4565b5eb9fcc8c9584b7', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '132', 'yx', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('295627952c9847dc9ed238f7c2c4faf0', '6a3ed3b9d70443fbbb1857dbf79bd6c9', '349f65f20f084102b78741efedfc1c63', '用户名', '', '1', '显示', '文本', '100', '', '', '109', 'yhm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('29d705052dee44559b35315bcb4f198f', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '0', '显示', '文本', '100', '', '', '148', 'fcdz', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('2aee27d35df843848864971a034f9c63', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '0', '显示', '文本', '100', '', '', '139', 'create_user', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('2c0ad68e9bca47ad8deebc51c0da56a0', 'b68b97b444cf427996c4cb39befb093b', '8bd0e4cb81a14500965c07478ef54a01', '年龄', '', '1', '显示', '文本', '100', '', '', '74', 'nl', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('2c3432ffa5944906a8fcf11deffc1f6d', '12a87f0a3aae430d9786bbba7a4b6149', '47ce349d432c4d0dafebdfd9b92479c6', 'sadasd6', '', '1', '显示', '文本', '100', '', '', '37', 'sadasd6', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('2d890337ce08497db6ed82003719803d', 'a3efd0f17d364415a1bddc5cd492713d', '7147d056b6094a5f8b536042e26f048d', '邮箱', '', '1', '显示', '附件', '100', '', '', '105', 'yx', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('2f23bf4bf48b454c99acc1de2a141965', 'e71582e7523241a9afceacf9fd76c158', null, '11', '', '0', '显示', '文本', '100', '', '', '136', 'id', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('2fc29a0f35d9485bb9d7c0566e7b2eba', 'b68b97b444cf427996c4cb39befb093b', '3e810d9858ef4dfe9556d9dd6b973412', '姓名', '', '0', '不显示', '日期', '100', '', '100', '73', null, '0', 'px');
INSERT INTO `zq_form_ui` VALUES ('30c255c3b8304e148c62e07e65fcc40d', '6454400a483f4db68b5669e7250d89f0', '329c03ced74d4786b1655f3d71cc4939', 'q12', '', '1', '显示', '文本', '100', '', '', '51', 'q12', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('30dd6ea83e3f4b57bc06994e2533ed5d', '12a87f0a3aae430d9786bbba7a4b6149', 'f153de8ff5c14b3b8073208a6e959ef0', 'sad1', '', '1', '显示', '文本', '100', '', '', '42', 'sad1', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('31d60cac037641a88a64a6a2da086d7d', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '130', 'qtfj', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('33478cf3e09245228f68d6476ae557f7', '6454400a483f4db68b5669e7250d89f0', '1612c9acf1544994a0dbf4e78808abc7', 'q3', '', '1', '显示', '文本', '100', '', '', '46', 'q3', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('33f6a809f3d44488bd0b893551a0eb5f', '6454400a483f4db68b5669e7250d89f0', '88e29d814c484dad8917e98723d4802c', 'q13', '', '1', '显示', '文本', '100', '', '', '52', 'q13', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('33ffccfda28f4ce29414cbd121528f22', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '120', 'bind_id', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('38f4a66b87e544d1bdf909d5b4c5afd4', '12a87f0a3aae430d9786bbba7a4b6149', '1794b4c2a346420d90b3795f212cafae', 'dasda8', '', '1', '显示', '文本', '100', '', '', '34', 'dasda8', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('3d5fca3f3cb94f3d8b8bd332fb83796f', '12a87f0a3aae430d9786bbba7a4b6149', '5f8188feccf94908b489c70cd027e3b2', 'asdas', '', '1', '显示', '文本', '100', '', '', '38', 'asdas', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('44adcbeed3aa441397dbffd93578ebdd', 'a3efd0f17d364415a1bddc5cd492713d', '5aafb7e6da0d44b4b802ba573c3f45c0', '身份证号', '', '1', '显示', '文本', '100', '', '', '100', 'sfzh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('45f3e08868244f5d8ed6ce515e20f4f0', '6454400a483f4db68b5669e7250d89f0', 'cdd78f69e42443a5b27b115ec33b2c0c', 'q5', '', '1', '显示', '文本', '100', '', '', '48', 'q5', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('47662d5ae1f64ef6ae3c57ef4f60adb9', 'bb5a6e0c6ea24ce8937bfec7dc050dd7', 'cdbacd1d70bc4593aacc7f8dafc4ad03', '年龄', '', '0', '显示', '文本', '200', '{\"type\":\"number\"}', '', '116', 'nl', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('4d69ab55812343d3b6776916518217a8', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '0', '显示', '文本', '100', '', '', '143', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('50766cdc19bc43199397daba3de8237e', 'a3efd0f17d364415a1bddc5cd492713d', '073c6e2fb9be4e02b33d7e9c32bd9b3c', '房产证编号', '', '1', '显示', '附件', '100', '', '', '108', 'fczbh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('512066c50db745438498d30b7228ed46', '6a3ed3b9d70443fbbb1857dbf79bd6c9', '7fc8b6f9d7bd43b39e573adb86922bcb', '邀请码', '', '1', '显示', '附件', '100', '', '', '112', 'yqm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('540d22dd9fb5454497b25843992db89b', 'a3efd0f17d364415a1bddc5cd492713d', 'dfce4148d25b4038929d75d5ffe9a947', '房产地址', '', '1', '显示', '文本', '100', '', '', '104', 'fcdz', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('5469aa4c7b8848dab84051336b25912d', '12a87f0a3aae430d9786bbba7a4b6149', 'f587e3411b6a45ddaa241c3c2a10baca', 'das44', '', '1', '显示', '文本', '100', '', '', '43', 'das44', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('56cf1f2a8d0d4642b9abe719f4558825', '6454400a483f4db68b5669e7250d89f0', 'e7ed82b078324e2ebd039ddd196c3c60', 'we', '', '1', '显示', '文本', '100', '', '', '54', 'we', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('586b4d9c45724543b3d9ee6978a5d8dd', '943ac278f61546b89dba02786d70ef1e', 'dfce4148d25b4038929d75d5ffe9a947', '房产地址', '', '1', '显示', '文本', '100', '', '', '160', 'fcdz', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('5cc017ca1f07438e8121e4bb1021dfbb', 'bb5a6e0c6ea24ce8937bfec7dc050dd7', 'cd953fe5eb0445108332fd0114f28be6', '电话', '', '1', '显示', '附件', '100', '', '', '117', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('5e1a5a72ba1047dcb9dd672a96efca43', '6a3ed3b9d70443fbbb1857dbf79bd6c9', 'ccd5f4bda1934fa79660bc14060dbaf7', '密码', '', '1', '显示', '文本', '100', '', '', '113', 'mm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('6433adaf715640c9ab5193c756a892df', '6454400a483f4db68b5669e7250d89f0', '54607524932a4d969a37b8282ee046c2', 'eeeeeeeee', '', '1', '显示', '文本', '100', '', '', '57', 'eeeeeeeee', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('66602ef938b84b418df47d1e4c2e3551', 'eed3f9db348f447ba57b67645e11eff0', '5fd6252e6aa8457891c3306f9af48cc1', 'b', '', '1', '显示', '文本', '100', '', '', '71', 'b', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('6adad6eb05844805938ad4a803a3dacd', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '122', 'create_user', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('6d7bb1722b4a4ddc882a772189141d2c', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '128', 'sfzzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('6e6d271ffdca4fe5bae0a81c08c767d8', '6454400a483f4db68b5669e7250d89f0', '75685dc8a3ea42a3996371618d5f5ce3', 'q1', '', '1', '显示', '文本', '100', '', '', '44', 'q1', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('70561f07fd544937a929c2c41c729eef', 'b68b97b444cf427996c4cb39befb093b', '644a3537933d49d3af3b793cb3d8afdf', '婚姻状况', '', '1', '显示', '文本', '100', '', '', '77', 'hyzk', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('719872f67e584add97a73a340e76e4f8', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '126', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('735872b7d28649d2ad645d6015d1d359', 'a3efd0f17d364415a1bddc5cd492713d', 'a9634c22b3fd4ebda0466c4d38c2b657', '用户名', '', '1', '显示', '文本', '100', '', '', '98', 'yhm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('736f2f8200a24c0792edbd63e9d5f6a6', '943ac278f61546b89dba02786d70ef1e', 'a9634c22b3fd4ebda0466c4d38c2b657', '用户名', '', '1', '显示', '文本', '100', '', '', '154', 'yhm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('74de3549addc46f69bff90bf773b9af6', '12a87f0a3aae430d9786bbba7a4b6149', '1cc10516c99d42a08b97345332421e1e', 'd5', '', '1', '显示', '文本', '100', '', '', '35', 'd5', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('834eddadcf3149baa67506b0f42bb735', 'b68b97b444cf427996c4cb39befb093b', '78d8668dd970452a83348e561f1eab7b', '生日', '', '1', '显示', '文本', '100', '', '', '75', 'sr', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('84a8fb0dbcec4205be484e159d1f9820', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '119', 'id', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('84de9811d0824685bd768296c7da1229', 'b68b97b444cf427996c4cb39befb093b', 'ea457fd8ded74589b74cf875b2b8ed65', '每月收入', '', '1', '显示', '文本', '100', '', '', '78', 'mysr', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('87f96072e9ba442cafb8a9a5f5cbfd16', '12a87f0a3aae430d9786bbba7a4b6149', '395fe41bbfb34716a8955425f9e3d888', 'asdas1', '', '1', '显示', '文本', '100', '', '', '36', 'asdas1', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('8b7b5660d86846459b87d7db093a0ed3', '6454400a483f4db68b5669e7250d89f0', '3ccf7ab43cc94ee38693424608f93e5c', '对对对', '1', '0', '显示', '文本', '100', '', '', '29', 'ddd', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('8c4633a8fdfe412b9f1e67b72c55776a', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '129', 'fczzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('8d1651a71b76402e8989264b6ff32192', '6a3ed3b9d70443fbbb1857dbf79bd6c9', '671cb1e2991440f29b1d692ea200e119', '姓名', '', '1', '显示', '文本', '100', '', '', '110', 'xm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('8dca49175ab34b28b5e877b59877a723', 'a3efd0f17d364415a1bddc5cd492713d', '630f21cb96b24c8b9286853ff0ceebb6', '房产证照片', '', '1', '显示', '附件', '100', '', '', '102', 'fczzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('90b804bd2e224ac9b6945fd27bb27c7c', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '124', 'update_user', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('9156c4e0c1974801ab9bfbc6fbb1d03b', '943ac278f61546b89dba02786d70ef1e', 'fc7cdaeeeb684bad93dea75ae3bea150', '是否可购买', '', '1', '显示', '文本', '100', '', '', '163', 'spkgm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('9177263ad93442d2b5c8b3783ed9dd38', '6454400a483f4db68b5669e7250d89f0', 'd759aae764a44c9c9f3e3763ff2b98ab', 'qwqeqwe', '1', '0', '显示', '文本', '100', '', '', '30', 'qwqeqwe', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('94ae87400ce7436394bdc33d76dd4db4', '943ac278f61546b89dba02786d70ef1e', 'f2b27e84c4e64472b43a7c8c18a91bca', '姓名', '', '1', '显示', '文本', '100', '', '', '162', 'xm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('960a7df7e33d4885aec0f0880d2692c1', 'a3efd0f17d364415a1bddc5cd492713d', '92dc8f4b34144631b38f426a3f8ecd8e', '电话', '', '1', '显示', '文本', '100', '', '', '99', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('9fd64c1ef2244ece963afa283db3066b', 'e71582e7523241a9afceacf9fd76c158', null, '更新日期', '', '1', '显示', '日期', '100', '{\"riqi\":\"yyyy-MM-dd\"}', '', '140', 'update_date', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('a28390662f6541ab8f691261b9ddfe57', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '125', 'yhm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('a42c47430e3247a98257d77576115871', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '133', 'xm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('a50a1609954e4fff8f4ca6d23b6d1ed5', '12a87f0a3aae430d9786bbba7a4b6149', '14b996c62a014936b523141332cba7ee', 'asdas7', 'qwwqe', '0', '显示', '文本', '100', '', '', '33', 'asdas7', '0', 'px');
INSERT INTO `zq_form_ui` VALUES ('a5e5e68a9376494c8725f85c5d9f301b', '6454400a483f4db68b5669e7250d89f0', '66b59f4e9581484896d0b6af2eb7e58a', 'q14', '', '1', '显示', '文本', '100', '', '', '53', 'q14', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('a8850d9881b24d8ba077fb2058098634', 'eed3f9db348f447ba57b67645e11eff0', '65c7cded554d4d02a0a9ea6f2b45a602', 'c', '', '1', '显示', '文本', '100', '', '', '72', 'c', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('b080178f96ac4abc95004979c145a663', 'bb5a6e0c6ea24ce8937bfec7dc050dd7', '3fc3b1ca53e84212b0a913dfaf9dc96b', '出生日期', '', '1', '显示', '日期', '100', '{\"riqi\":\"yyyy-MM-dd\"}', '', '118', 'csrq', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('b70872c6b03740f2a6ddfea349e91593', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '127', 'sfzh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('b70b0042ceda450b93e0fb6f59381e28', 'a3efd0f17d364415a1bddc5cd492713d', 'fc7cdaeeeb684bad93dea75ae3bea150', '是否可购买', '', '1', '显示', '文本', '100', '', '', '107', 'spkgm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('baff403550cc4ea59f89a30accb87820', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '1', '显示', '文本', '100', '', '', '152', 'fczbh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('bb8b8b6be10746e6b500f7c8510a0d4a', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '1', '显示', '文本', '100', '', '', '142', 'yhm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('bc57593105ab438691576675f950079a', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '1', '显示', '文本', '100', '', '', '141', 'update_user', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('bdc783349b054a70aec68f6172579d1f', '943ac278f61546b89dba02786d70ef1e', 'd5528c92503c46c48ead8207efea75b5', '身份证照片', '', '1', '显示', '文本', '100', '', '', '157', 'sfzzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('c17549cc2e0c49739cb728cbfec50279', 'a3efd0f17d364415a1bddc5cd492713d', '422e3c0128094fb180a328687f609b07', '其他附件', '', '1', '显示', '文本', '100', '', '', '103', 'qtfj', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('c429a3f5e43e4027b8f367c6454afe68', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '134', 'spkgm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('c875aa62a3824709bac66f3cf1d84ad0', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '1', '显示', '文本', '100', '', '', '144', 'sfzh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('c8cfea2cf1224d1a960897fc56c6ee20', 'e71582e7523241a9afceacf9fd76c158', null, '11', '', '1', '显示', '文本', '100', '', '', '146', 'fczzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('cadaf1d90405415da4837f47e7c67a07', '6454400a483f4db68b5669e7250d89f0', '6d5fc03febf24514b18f698c490323d2', 'q4', '', '1', '显示', '文本', '100', '', '', '47', 'q4', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('ceee725b5b584152820dacbab6db009c', '943ac278f61546b89dba02786d70ef1e', '073c6e2fb9be4e02b33d7e9c32bd9b3c', '房产证编号', '', '1', '显示', '文本', '100', '', '', '164', 'fczbh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('d02c3f76e63346d39b0fdfe3985edb45', 'e71582e7523241a9afceacf9fd76c158', null, '创建日期', '', '1', '显示', '日期', '100', '{\"riqi\":\"yyyy-MM-dd\"}', '', '138', 'create_date', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('d0b6875f88b64ba78387171c0c1f6c4b', '12a87f0a3aae430d9786bbba7a4b6149', '02b7dd83f8ee4bfb9480d1f88b127d9c', 'ad2', '', '1', '显示', '文本', '100', '', '', '32', 'ad2', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('d295fd3f363b43408351aae59ee56835', '6a3ed3b9d70443fbbb1857dbf79bd6c9', 'a661e48606c64f3cbd9b69f8f41bfb1b', '电话', '', '1', '显示', '文本', '100', '', '', '111', 'dh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('d5fec8c726e84610a2cd37c5db1fb267', '6454400a483f4db68b5669e7250d89f0', '3f8325e7e79f4c8b9d1c2bb76b7b7be7', 'q2', '', '1', '显示', '文本', '100', '', '', '45', 'q2', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('dc3b49744ad2494aa57bb6b5c170e4cd', '29474e584d564dc7a477dfbb7e04ce86', '6adbebbf16da4fdc8799547a1a29041a', 'name', '', '1', '显示', '文本', '100', '', '', '153', 'name', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('dd5e0af9e279460d829b2511086d75e2', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '1', '显示', '文本', '100', '', '', '145', 'sfzzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e086c9ecba2d4470bd015d8c7889541d', '12a87f0a3aae430d9786bbba7a4b6149', 'f0c2e7a8c074470bbfe1a5eb7235bd3a', 'sd3', '', '1', '显示', '文本', '100', '', '', '41', 'sd3', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e0be01d060fa4c17856b486f7d41de6e', '5a17982c67024c85a54d775c4578f152', null, '', null, '1', '显示', '文本', '100', '', '', '135', 'fczbh', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e27900315daf4e2f84bdcaade655ad67', 'bb5a6e0c6ea24ce8937bfec7dc050dd7', '19d9d8900ab34cc58d33b4af8c009552', '姓名', '', '1', '显示', '文本', '100', '', '', '115', 'xm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e429a4c90e1241968f444075a38b4b35', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '1', '显示', '文本', '100', '', '', '147', 'qtfj', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e49f18e4e6944d8ca35fc87589fd303f', '12a87f0a3aae430d9786bbba7a4b6149', '6d6fafd461a1451584511f197fe78a50', 'sadsa4', '', '1', '显示', '文本', '100', '', '', '39', 'sadsa4', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e594153ca21744c2a34a4f259d2db9fb', '6454400a483f4db68b5669e7250d89f0', '97889cbc6ca34d849afee15be8f0cf98', 'q55', '', '1', '显示', '文本', '100', '', '', '49', 'q55', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e5a1fa1671bf4e96a064e2964642d9b3', '943ac278f61546b89dba02786d70ef1e', '630f21cb96b24c8b9286853ff0ceebb6', '房产证照片', '', '1', '显示', '文本', '100', '', '', '158', 'fczzp', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e86f1184ab6041d0bbb547b2e11c102a', '6454400a483f4db68b5669e7250d89f0', '9c5024f3ae124df09947224630e56dee', 'edasd', '', '1', '显示', '文本', '100', '', '', '56', 'edasd', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e9b3249aac2148b4bf249890841fa84b', '6a3ed3b9d70443fbbb1857dbf79bd6c9', 'd49e1e320d4d4447811df3ca7b431603', '是否通过申请', '', '1', '显示', '文本', '100', '', '', '114', 'sptgsq', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('e9d1c89d9e2447e8bfb0c9efad9f22eb', 'e71582e7523241a9afceacf9fd76c158', null, '1', '', '0', '显示', '文本', '100', '', '', '151', 'spkgm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('edcf7201599d45acb7ef9f8e02774e82', 'a3efd0f17d364415a1bddc5cd492713d', 'f2b27e84c4e64472b43a7c8c18a91bca', '姓名', '', '1', '显示', '文本', '100', '', '', '106', 'xm', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('f003abf5203d48dc96d4b57a0590a3c3', 'cc9f690c08e64e328cfa74ceea6a99be', 'b82429fce0ea4ca8bfd0d41281ec387e', 'wewe', '', '1', '显示', '文本', '100', '', '', '55', 'wewe', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('f0b9736543d44567b69f855f071ef15a', '6454400a483f4db68b5669e7250d89f0', '0a9be2ecb5424154b3c00b652c623ac3', 'q11', '', '1', '显示', '文本', '100', '', '', '50', 'q11', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('f79c427cb52f40a8b51d324d4c078dd8', '6454400a483f4db68b5669e7250d89f0', '8a6b5a61bba449788774ed5075ff9d5b', 'adadasd', '', '1', '显示', '文本', '100', '', '', '31', 'adadasd', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('f85b404d906c4c9c9c466b6780ec0dea', '12a87f0a3aae430d9786bbba7a4b6149', '9f5126a1bfb5406cba52b87ad60ca916', 'd22', 'wq', '1', '显示', '文本', '100', '', '', '40', 'd22', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('fb05bb88142d42779d886daa86dfe67d', 'e71582e7523241a9afceacf9fd76c158', null, '22221', '', '1', '显示', '文本', '100', '{\"type\":\"char\"}', '', '137', 'bind_id', '1', 'px');
INSERT INTO `zq_form_ui` VALUES ('fd64c19c8bb3471c949a0af8058d9144', '943ac278f61546b89dba02786d70ef1e', '5aafb7e6da0d44b4b802ba573c3f45c0', '身份证号', '', '1', '显示', '文本', '100', '', '', '156', 'sfzh', '1', 'px');

-- ----------------------------
-- Table structure for `zq_metadata`
-- ----------------------------
DROP TABLE IF EXISTS `zq_metadata`;
CREATE TABLE `zq_metadata` (
  `id` char(40) NOT NULL,
  `table_name` char(56) DEFAULT NULL,
  `table_title` char(100) DEFAULT NULL,
  `table_type` char(10) DEFAULT NULL,
  `model_lib_id` char(40) DEFAULT NULL,
  `master` char(100) DEFAULT NULL,
  `order_index` decimal(12,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_metadata
-- ----------------------------
INSERT INTO `zq_metadata` VALUES ('01691215376c40a5a473771f725f9c9e', 'bo_fcxx', '房产信息', null, '7feaf3c8c8f8494493fedd23f16549cc', null, '77');
INSERT INTO `zq_metadata` VALUES ('7b1d8951c4ab4881b08dd85224b7c7e3', 'bo_zczh', '注册账户', null, '7feaf3c8c8f8494493fedd23f16549cc', null, '73');
INSERT INTO `zq_metadata` VALUES ('9a3fb2cd116a4e26abe88e00feb8f885', 'bo_yhlb', '用户列表', null, '7feaf3c8c8f8494493fedd23f16549cc', null, '74');
INSERT INTO `zq_metadata` VALUES ('f69f07dcca0a47519e1e1defbff67a51', 'bo_kdj', '快递柜', null, '289112d1c1a04708b71994aeb1a1ad53', null, '83');

-- ----------------------------
-- Table structure for `zq_metadata_map`
-- ----------------------------
DROP TABLE IF EXISTS `zq_metadata_map`;
CREATE TABLE `zq_metadata_map` (
  `id` char(40) NOT NULL,
  `metadata_id` char(40) DEFAULT NULL,
  `column_name` char(60) DEFAULT NULL,
  `column_title` char(100) DEFAULT NULL,
  `column_type` char(10) DEFAULT NULL,
  `column_length` char(10) DEFAULT NULL,
  `order_index` decimal(12,0) DEFAULT NULL,
  `default_value` char(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_metadata_map
-- ----------------------------
INSERT INTO `zq_metadata_map` VALUES ('01ed5f9b550a418096a9770a90076112', '9a3fb2cd116a4e26abe88e00feb8f885', 'dh', '电话', '文本', '100', '99', null);
INSERT INTO `zq_metadata_map` VALUES ('02b7dd83f8ee4bfb9480d1f88b127d9c', '2407d199fddb404e8dcd025150999b8d', 'ad2', 'ad2', '文本', '100', '63', null);
INSERT INTO `zq_metadata_map` VALUES ('073c6e2fb9be4e02b33d7e9c32bd9b3c', '01691215376c40a5a473771f725f9c9e', 'fczbh', '房产证编号', '文本', '100', '123', null);
INSERT INTO `zq_metadata_map` VALUES ('0a5e46578af348e88ea46a6e22e4cba2', 'a849715e14b54e889cb6cbd4d41c3ec4', 'dffds', '的方法都是', '文本', '100', '139', null);
INSERT INTO `zq_metadata_map` VALUES ('0a9be2ecb5424154b3c00b652c623ac3', '6e25157dfd69406f90cafcd02fe811bc', 'q11', 'q11', '文本', '100', '79', null);
INSERT INTO `zq_metadata_map` VALUES ('0aad2ac432e64f3db864c0cd733bd4a5', '41a2f10c0af84dc08c5fea80fdeb7a38', 'aaa', 'aaa', '文本', '100', '56', null);
INSERT INTO `zq_metadata_map` VALUES ('0abfde7ea1c344569eadd0031f8a2240', '41a2f10c0af84dc08c5fea80fdeb7a38', 'asdas', 'asdas', '文本', '100', '55', null);
INSERT INTO `zq_metadata_map` VALUES ('14b996c62a014936b523141332cba7ee', '2407d199fddb404e8dcd025150999b8d', 'asdas7', 'asdas7', '文本', '100', '67', null);
INSERT INTO `zq_metadata_map` VALUES ('1612c9acf1544994a0dbf4e78808abc7', '6e25157dfd69406f90cafcd02fe811bc', 'q3', 'q3', '文本', '100', '75', null);
INSERT INTO `zq_metadata_map` VALUES ('1794b4c2a346420d90b3795f212cafae', '2407d199fddb404e8dcd025150999b8d', 'dasda8', 'dasda8', '文本', '100', '69', null);
INSERT INTO `zq_metadata_map` VALUES ('1920941ad97d48c580f190bd29a5eb3c', '41a2f10c0af84dc08c5fea80fdeb7a38', 'fff', 'fff', '文本', '100', '57', null);
INSERT INTO `zq_metadata_map` VALUES ('19d9d8900ab34cc58d33b4af8c009552', '39dd2d99233949cea65cba4f704bbdc3', 'xm', '姓名', '文本', '100', '135', null);
INSERT INTO `zq_metadata_map` VALUES ('1cc10516c99d42a08b97345332421e1e', '2407d199fddb404e8dcd025150999b8d', 'd5', 'd5', '文本', '100', '66', null);
INSERT INTO `zq_metadata_map` VALUES ('329c03ced74d4786b1655f3d71cc4939', '6e25157dfd69406f90cafcd02fe811bc', 'q12', 'q12', '文本', '100', '80', null);
INSERT INTO `zq_metadata_map` VALUES ('349f65f20f084102b78741efedfc1c63', '7b1d8951c4ab4881b08dd85224b7c7e3', 'yhm', '用户名', '文本', '100', '87', null);
INSERT INTO `zq_metadata_map` VALUES ('395fe41bbfb34716a8955425f9e3d888', '2407d199fddb404e8dcd025150999b8d', 'asdas1', 'asdas1', '文本', '100', '62', null);
INSERT INTO `zq_metadata_map` VALUES ('3ccf7ab43cc94ee38693424608f93e5c', '6e25157dfd69406f90cafcd02fe811bc', 'ddd', '对对对', '文本', '100', '58', null);
INSERT INTO `zq_metadata_map` VALUES ('3e810d9858ef4dfe9556d9dd6b973412', 'd9fca1dc40854378a86efdeab8eb504c', 'xm', '姓名', '文本', '100', '124', null);
INSERT INTO `zq_metadata_map` VALUES ('3f8325e7e79f4c8b9d1c2bb76b7b7be7', '6e25157dfd69406f90cafcd02fe811bc', 'q2', 'q2', '文本', '100', '74', null);
INSERT INTO `zq_metadata_map` VALUES ('3fc3b1ca53e84212b0a913dfaf9dc96b', '39dd2d99233949cea65cba4f704bbdc3', 'csrq', '出生日期', '日期', '0', '138', null);
INSERT INTO `zq_metadata_map` VALUES ('422e3c0128094fb180a328687f609b07', '01691215376c40a5a473771f725f9c9e', 'qtfj', '其他附件', '文本', '2000', '118', null);
INSERT INTO `zq_metadata_map` VALUES ('43b07c46366a4908a6766d4468c06e11', '9a3fb2cd116a4e26abe88e00feb8f885', 'xm', '姓名', '文本', '100', '98', null);
INSERT INTO `zq_metadata_map` VALUES ('441194bbce554c6fb9e01fe882419b9e', '9a3fb2cd116a4e26abe88e00feb8f885', 'fczzp', '房产证照片', '文本', '2000', '110', null);
INSERT INTO `zq_metadata_map` VALUES ('453eb3d7bf7146e089cf63489f029174', '9a3fb2cd116a4e26abe88e00feb8f885', 'yx', '邮箱', '文本', '100', '102', null);
INSERT INTO `zq_metadata_map` VALUES ('47ce349d432c4d0dafebdfd9b92479c6', '2407d199fddb404e8dcd025150999b8d', 'sadasd6', 'sadasd6', '文本', '100', '68', null);
INSERT INTO `zq_metadata_map` VALUES ('51338aff27aa45d988de2351b4a89b46', '077ea9677331423295c75f722f1e53a5', 'a', 'a', '数值', '11,1', '130', null);
INSERT INTO `zq_metadata_map` VALUES ('51800aed512b419499c38ee15921c312', '9a3fb2cd116a4e26abe88e00feb8f885', 'mm', '密码', '文本', '100', '100', null);
INSERT INTO `zq_metadata_map` VALUES ('54607524932a4d969a37b8282ee046c2', '6e25157dfd69406f90cafcd02fe811bc', 'eeeeeeeee', 'eeeeeeeee', '文本', '100', '86', null);
INSERT INTO `zq_metadata_map` VALUES ('5aafb7e6da0d44b4b802ba573c3f45c0', '01691215376c40a5a473771f725f9c9e', 'sfzh', '身份证号', '文本', '100', '115', null);
INSERT INTO `zq_metadata_map` VALUES ('5b9f6f79fb524d3a82a44b860d6187c1', '924a241c771c4d8cace27585b979391b', 'sfzzm', '身份证正面', '文本', '2000', '104', null);
INSERT INTO `zq_metadata_map` VALUES ('5f8188feccf94908b489c70cd027e3b2', '2407d199fddb404e8dcd025150999b8d', 'asdas', 'asdas', '文本', '100', '72', null);
INSERT INTO `zq_metadata_map` VALUES ('5fd6252e6aa8457891c3306f9af48cc1', '077ea9677331423295c75f722f1e53a5', 'b', 'b', '文本', '100', '131', null);
INSERT INTO `zq_metadata_map` VALUES ('630f21cb96b24c8b9286853ff0ceebb6', '01691215376c40a5a473771f725f9c9e', 'fczzp', '房产证照片', '文本', '2000', '117', null);
INSERT INTO `zq_metadata_map` VALUES ('644a3537933d49d3af3b793cb3d8afdf', 'd9fca1dc40854378a86efdeab8eb504c', 'hyzk', '婚姻状况', '文本', '100', '128', null);
INSERT INTO `zq_metadata_map` VALUES ('65c7cded554d4d02a0a9ea6f2b45a602', '077ea9677331423295c75f722f1e53a5', 'c', 'c', '文本', '100', '132', null);
INSERT INTO `zq_metadata_map` VALUES ('66b59f4e9581484896d0b6af2eb7e58a', '6e25157dfd69406f90cafcd02fe811bc', 'q14', 'q14', '文本', '100', '82', null);
INSERT INTO `zq_metadata_map` VALUES ('671cb1e2991440f29b1d692ea200e119', '7b1d8951c4ab4881b08dd85224b7c7e3', 'xm', '姓名', '文本', '100', '88', null);
INSERT INTO `zq_metadata_map` VALUES ('6adbebbf16da4fdc8799547a1a29041a', 'f69f07dcca0a47519e1e1defbff67a51', 'name', 'name', '文本', '100', '140', null);
INSERT INTO `zq_metadata_map` VALUES ('6d5fc03febf24514b18f698c490323d2', '6e25157dfd69406f90cafcd02fe811bc', 'q4', 'q4', '文本', '100', '76', null);
INSERT INTO `zq_metadata_map` VALUES ('6d6fafd461a1451584511f197fe78a50', '2407d199fddb404e8dcd025150999b8d', 'sadsa4', 'sadsa4', '文本', '100', '65', null);
INSERT INTO `zq_metadata_map` VALUES ('7147d056b6094a5f8b536042e26f048d', '01691215376c40a5a473771f725f9c9e', 'yx', '邮箱', '文本', '100', '120', null);
INSERT INTO `zq_metadata_map` VALUES ('75685dc8a3ea42a3996371618d5f5ce3', '6e25157dfd69406f90cafcd02fe811bc', 'q1', 'q1', '文本', '100', '73', null);
INSERT INTO `zq_metadata_map` VALUES ('77aa595efa604dae8bc69be17474fdd8', '924a241c771c4d8cace27585b979391b', 'qtzl', '其他资料', '文本', '2000', '107', null);
INSERT INTO `zq_metadata_map` VALUES ('78d8668dd970452a83348e561f1eab7b', 'd9fca1dc40854378a86efdeab8eb504c', 'sr', '生日', '文本', '100', '126', null);
INSERT INTO `zq_metadata_map` VALUES ('7fc8b6f9d7bd43b39e573adb86922bcb', '7b1d8951c4ab4881b08dd85224b7c7e3', 'yqm', '邀请码', '文本', '100', '90', null);
INSERT INTO `zq_metadata_map` VALUES ('80040b28032f49c6ad3985b789f95fa0', '9a3fb2cd116a4e26abe88e00feb8f885', 'sfzzp', '身份证照片', '文本', '2000', '109', null);
INSERT INTO `zq_metadata_map` VALUES ('8329fb0b52d749ab961b63cfd99bcb9a', '9a3fb2cd116a4e26abe88e00feb8f885', 'sfzh', '身份证号', '文本', '2000', '108', null);
INSERT INTO `zq_metadata_map` VALUES ('88e29d814c484dad8917e98723d4802c', '6e25157dfd69406f90cafcd02fe811bc', 'q13', 'q13', '文本', '100', '81', null);
INSERT INTO `zq_metadata_map` VALUES ('8a6b5a61bba449788774ed5075ff9d5b', '6e25157dfd69406f90cafcd02fe811bc', 'adadasd', 'adadasd', '文本', '100', '60', null);
INSERT INTO `zq_metadata_map` VALUES ('8bd0e4cb81a14500965c07478ef54a01', 'd9fca1dc40854378a86efdeab8eb504c', 'nl', '年龄', '文本', '100', '125', null);
INSERT INTO `zq_metadata_map` VALUES ('92dc8f4b34144631b38f426a3f8ecd8e', '01691215376c40a5a473771f725f9c9e', 'dh', '电话', '文本', '100', '114', null);
INSERT INTO `zq_metadata_map` VALUES ('97889cbc6ca34d849afee15be8f0cf98', '6e25157dfd69406f90cafcd02fe811bc', 'q55', 'q55', '文本', '100', '78', null);
INSERT INTO `zq_metadata_map` VALUES ('97ffbfea79c5465e8f76f306800f61c2', '9a3fb2cd116a4e26abe88e00feb8f885', 'yhm', '用户名', '文本', '100', '97', null);
INSERT INTO `zq_metadata_map` VALUES ('9c5024f3ae124df09947224630e56dee', '6e25157dfd69406f90cafcd02fe811bc', 'edasd', 'edasd', '文本', '100', '85', null);
INSERT INTO `zq_metadata_map` VALUES ('9f5126a1bfb5406cba52b87ad60ca916', '2407d199fddb404e8dcd025150999b8d', 'd22', 'd22', '文本', '100', '70', null);
INSERT INTO `zq_metadata_map` VALUES ('a661e48606c64f3cbd9b69f8f41bfb1b', '7b1d8951c4ab4881b08dd85224b7c7e3', 'dh', '电话', '文本', '100', '89', null);
INSERT INTO `zq_metadata_map` VALUES ('a9634c22b3fd4ebda0466c4d38c2b657', '01691215376c40a5a473771f725f9c9e', 'yhm', '用户名', '文本', '100', '113', null);
INSERT INTO `zq_metadata_map` VALUES ('b82429fce0ea4ca8bfd0d41281ec387e', '11b3b28ec2e3443498b9ada308af4edd', 'wewe', 'wewe', '文本', '100', '84', null);
INSERT INTO `zq_metadata_map` VALUES ('bbf11af2af1e4485ad47a5c13624d547', '924a241c771c4d8cace27585b979391b', 'sfzh', '身份证号', '文本', '100', '103', null);
INSERT INTO `zq_metadata_map` VALUES ('ccd5f4bda1934fa79660bc14060dbaf7', '7b1d8951c4ab4881b08dd85224b7c7e3', 'mm', '密码', '文本', '100', '96', null);
INSERT INTO `zq_metadata_map` VALUES ('cd953fe5eb0445108332fd0114f28be6', '39dd2d99233949cea65cba4f704bbdc3', 'dh', '电话', '文本', '100', '137', null);
INSERT INTO `zq_metadata_map` VALUES ('cdbacd1d70bc4593aacc7f8dafc4ad03', '39dd2d99233949cea65cba4f704bbdc3', 'nl', '年龄', '数值', '11,2', '136', null);
INSERT INTO `zq_metadata_map` VALUES ('cdd78f69e42443a5b27b115ec33b2c0c', '6e25157dfd69406f90cafcd02fe811bc', 'q5', 'q5', '文本', '100', '77', null);
INSERT INTO `zq_metadata_map` VALUES ('d216a8b9ab1842f9a1e867652cd65fde', '924a241c771c4d8cace27585b979391b', 'sfzfm', '身份证反面', '文本', '2000', '105', null);
INSERT INTO `zq_metadata_map` VALUES ('d49e1e320d4d4447811df3ca7b431603', '7b1d8951c4ab4881b08dd85224b7c7e3', 'sptgsq', '是否通过申请', '文本', '100', '112', null);
INSERT INTO `zq_metadata_map` VALUES ('d5528c92503c46c48ead8207efea75b5', '01691215376c40a5a473771f725f9c9e', 'sfzzp', '身份证照片', '文本', '2000', '116', null);
INSERT INTO `zq_metadata_map` VALUES ('d759aae764a44c9c9f3e3763ff2b98ab', '6e25157dfd69406f90cafcd02fe811bc', 'qwqeqwe', 'qwqeqwe', '文本', '100', '59', null);
INSERT INTO `zq_metadata_map` VALUES ('dfce4148d25b4038929d75d5ffe9a947', '01691215376c40a5a473771f725f9c9e', 'fcdz', '房产地址', '文本', '1000', '119', null);
INSERT INTO `zq_metadata_map` VALUES ('e7ed82b078324e2ebd039ddd196c3c60', '6e25157dfd69406f90cafcd02fe811bc', 'we', 'we', '文本', '100', '83', null);
INSERT INTO `zq_metadata_map` VALUES ('ea457fd8ded74589b74cf875b2b8ed65', 'd9fca1dc40854378a86efdeab8eb504c', 'mysr', '每月收入sdfsdf', '文本', '100', '129', null);
INSERT INTO `zq_metadata_map` VALUES ('ee16a5ee843a4148994c7aa98cd8afb7', 'd9fca1dc40854378a86efdeab8eb504c', 'dh', '电话dfds', '文本', '100', '127', null);
INSERT INTO `zq_metadata_map` VALUES ('f0c2e7a8c074470bbfe1a5eb7235bd3a', '2407d199fddb404e8dcd025150999b8d', 'sd3', 'sd3', '文本', '100', '64', null);
INSERT INTO `zq_metadata_map` VALUES ('f153de8ff5c14b3b8073208a6e959ef0', '2407d199fddb404e8dcd025150999b8d', 'sad1', 'sad1', '文本', '100', '61', null);
INSERT INTO `zq_metadata_map` VALUES ('f2b27e84c4e64472b43a7c8c18a91bca', '01691215376c40a5a473771f725f9c9e', 'xm', '姓名', '文本', '100', '121', null);
INSERT INTO `zq_metadata_map` VALUES ('f3c7b5c3ef9d49679a3a019b2edc266d', 'c83bce0c5cbf4b65918d432f8aed6fb6', 'ssd', '撒是的', '文本', '100', '133', null);
INSERT INTO `zq_metadata_map` VALUES ('f54cb3dfe6e64b06953ae59aa61fd5a8', '924a241c771c4d8cace27585b979391b', 'fcz', '房产证', '文本', '2000', '106', null);
INSERT INTO `zq_metadata_map` VALUES ('f587e3411b6a45ddaa241c3c2a10baca', '2407d199fddb404e8dcd025150999b8d', 'das44', 'das44', '文本', '100', '71', null);
INSERT INTO `zq_metadata_map` VALUES ('fac05f4758a54b0489cafa15c5e800fb', 'c83bce0c5cbf4b65918d432f8aed6fb6', 'qwqw', '请问千千万万企鹅请问', '文本', '100', '134', null);
INSERT INTO `zq_metadata_map` VALUES ('fc7cdaeeeb684bad93dea75ae3bea150', '01691215376c40a5a473771f725f9c9e', 'spkgm', '是否可购买', '文本', '100', '122', null);
INSERT INTO `zq_metadata_map` VALUES ('fcbfef6a5c1b44e4b9d4992ab0d4c5b7', '9a3fb2cd116a4e26abe88e00feb8f885', 'qtfj', '其他附件', '文本', '2000', '111', null);

-- ----------------------------
-- Table structure for `zq_model_library`
-- ----------------------------
DROP TABLE IF EXISTS `zq_model_library`;
CREATE TABLE `zq_model_library` (
  `id` char(40) NOT NULL DEFAULT '',
  `model_name` char(40) DEFAULT NULL,
  `order_index` decimal(12,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_model_library
-- ----------------------------
INSERT INTO `zq_model_library` VALUES ('289112d1c1a04708b71994aeb1a1ad53', '测试', '22');
INSERT INTO `zq_model_library` VALUES ('7feaf3c8c8f8494493fedd23f16549cc', '中介', '15');

-- ----------------------------
-- Table structure for `zq_nav`
-- ----------------------------
DROP TABLE IF EXISTS `zq_nav`;
CREATE TABLE `zq_nav` (
  `id` char(40) NOT NULL,
  `name` char(100) DEFAULT NULL,
  `url` varchar(2000) DEFAULT NULL,
  `url_target` char(50) DEFAULT NULL,
  `icon_url` char(50) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `status` char(20) DEFAULT NULL,
  `p_id` char(40) DEFAULT NULL,
  `level` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_nav
-- ----------------------------
INSERT INTO `zq_nav` VALUES ('6e1fc3728b3c4dbd8e4f96799274f84b', '1', '1', '新窗口', '1', '20', '是', null, null);

-- ----------------------------
-- Table structure for `zq_nav_first`
-- ----------------------------
DROP TABLE IF EXISTS `zq_nav_first`;
CREATE TABLE `zq_nav_first` (
  `id` char(40) NOT NULL,
  `first_name` char(100) DEFAULT NULL,
  `first_url` varchar(2000) DEFAULT NULL,
  `url_target` char(50) DEFAULT NULL,
  `icon_url` char(50) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `status` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_nav_first
-- ----------------------------
INSERT INTO `zq_nav_first` VALUES ('6e1fc3728b3c4dbd8e4f96799274f84b', '1', '1', '新窗口', '1', '20', '是');

-- ----------------------------
-- Table structure for `zq_nav_second`
-- ----------------------------
DROP TABLE IF EXISTS `zq_nav_second`;
CREATE TABLE `zq_nav_second` (
  `id` char(40) NOT NULL,
  `second_name` char(100) DEFAULT NULL,
  `second_url` varchar(2000) DEFAULT NULL,
  `url_target` char(50) DEFAULT NULL,
  `icon_url` char(50) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `first_id` char(50) DEFAULT NULL,
  `status` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_nav_second
-- ----------------------------

-- ----------------------------
-- Table structure for `zq_nav_three`
-- ----------------------------
DROP TABLE IF EXISTS `zq_nav_three`;
CREATE TABLE `zq_nav_three` (
  `id` char(40) NOT NULL,
  `three_name` char(100) DEFAULT NULL,
  `second_id` char(50) DEFAULT NULL,
  `three_url` varchar(2000) DEFAULT NULL,
  `url_target` char(50) DEFAULT NULL,
  `icon_url` char(50) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `first_id` char(50) DEFAULT NULL,
  `status` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_nav_three
-- ----------------------------

-- ----------------------------
-- Table structure for `zq_org_company`
-- ----------------------------
DROP TABLE IF EXISTS `zq_org_company`;
CREATE TABLE `zq_org_company` (
  `id` char(40) NOT NULL,
  `co_code` char(100) DEFAULT NULL,
  `co_name` char(200) DEFAULT NULL,
  `co_memo` text,
  `order_index` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_org_company
-- ----------------------------
INSERT INTO `zq_org_company` VALUES ('b2e29b50db4841f9b14888c1fa81adc1', '1', '张钦', '1', '43');

-- ----------------------------
-- Table structure for `zq_org_department`
-- ----------------------------
DROP TABLE IF EXISTS `zq_org_department`;
CREATE TABLE `zq_org_department` (
  `id` char(40) NOT NULL,
  `co_id` char(40) DEFAULT NULL,
  `dept_name` char(80) DEFAULT NULL,
  `dept_code` char(80) DEFAULT NULL,
  `dept_parentid` char(40) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_org_department
-- ----------------------------
INSERT INTO `zq_org_department` VALUES ('a0d256d2cb23469785f98a3d3a39db1f', 'b2e29b50db4841f9b14888c1fa81adc1', 'ce', 'ce', null, '33');

-- ----------------------------
-- Table structure for `zq_org_membership`
-- ----------------------------
DROP TABLE IF EXISTS `zq_org_membership`;
CREATE TABLE `zq_org_membership` (
  `id` char(40) NOT NULL,
  `user_account` char(20) DEFAULT NULL,
  `dept_id` char(40) DEFAULT NULL,
  `role_id` char(40) DEFAULT NULL,
  `dept_manager` decimal(1,0) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_org_membership
-- ----------------------------

-- ----------------------------
-- Table structure for `zq_org_role`
-- ----------------------------
DROP TABLE IF EXISTS `zq_org_role`;
CREATE TABLE `zq_org_role` (
  `id` char(40) NOT NULL,
  `role_group` char(20) DEFAULT NULL,
  `role_name` char(20) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_org_role
-- ----------------------------
INSERT INTO `zq_org_role` VALUES ('0ee8a7ed3af34e9b9df585927398cb28', '的萨大声', '444444', '11');
INSERT INTO `zq_org_role` VALUES ('376af98f413948ab81f09319e5cc016b', '的萨大声', 'ww', '10');
INSERT INTO `zq_org_role` VALUES ('3c85c069519c4c4394d8efc946dedc55', '的萨大声', '情悄悄前期', '5');

-- ----------------------------
-- Table structure for `zq_org_user`
-- ----------------------------
DROP TABLE IF EXISTS `zq_org_user`;
CREATE TABLE `zq_org_user` (
  `id` char(40) NOT NULL,
  `dept_id` char(40) DEFAULT NULL,
  `role_id` char(40) DEFAULT NULL,
  `user_account` char(20) DEFAULT NULL,
  `user_password` char(128) DEFAULT NULL,
  `user_name` char(20) DEFAULT NULL,
  `dept_manager` char(10) DEFAULT NULL,
  `user_tel` char(20) DEFAULT NULL,
  `user_fax` char(20) DEFAULT NULL,
  `user_mobile` char(20) DEFAULT NULL,
  `user_mail` char(40) DEFAULT NULL,
  `order_index` bigint(20) DEFAULT NULL,
  `user_code` char(50) DEFAULT NULL,
  `user_sex` char(10) DEFAULT NULL,
  `comp_id` char(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of zq_org_user
-- ----------------------------
INSERT INTO `zq_org_user` VALUES ('35077f450d994623965b73a45d04c679', 'a0d256d2cb23469785f98a3d3a39db1f', '376af98f413948ab81f09319e5cc016b', 'ee', 'E10ADC3949BA59ABBE56E057F20F883E', 'eee', '是', '', '', '', '', '55', null, '男', null);
INSERT INTO `zq_org_user` VALUES ('ca16984a784f4be39b31fc3a5eb81a13', 'a0d256d2cb23469785f98a3d3a39db1f', '376af98f413948ab81f09319e5cc016b', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', 'admin', '是', 'r', 'e', 'e', '', '54', null, '男', null);

-- ----------------------------
-- Procedure structure for `insets`
-- ----------------------------
DROP PROCEDURE IF EXISTS `insets`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insets`()
BEGIN
    DECLARE i int default 1;
    WHILE(i < 200000) DO
        insert into bo_fcxx (yhm, dh,fcdz) values ('manager', '14141025877','大师傅时代发生地方当时发生的发大水发的顺丰撒旦法的事发生大发生的发顺丰萨芬撒地方');
        SET i = i+1;
    END WHILE;
    END
;;
DELIMITER ;

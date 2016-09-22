/*
Navicat MySQL Data Transfer

Source Server         : 115
Source Server Version : 50614
Source Host           : 10.2.45.115:3306
Source Database       : countdata

Target Server Type    : MYSQL
Target Server Version : 50614
File Encoding         : 65001

Date: 2015-10-14 17:13:31
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `countdata`
-- ----------------------------
DROP TABLE IF EXISTS `countdata`;
CREATE TABLE `countdata` (
  `time` varchar(20) NOT NULL DEFAULT '',
  `HBase` varchar(15) NOT NULL COMMENT '单位：条',
  `HBases` varchar(15) NOT NULL COMMENT '单位：条',
  `ElasticSearch` varchar(15) NOT NULL COMMENT '单位：条',
  `ElasticSearchs` varchar(15) NOT NULL COMMENT '单位：条',
  `ElasticDisk` varchar(15) NOT NULL COMMENT '单位：GB',
  `ElasticDisks` varchar(15) NOT NULL COMMENT '单位：gb',
  PRIMARY KEY (`time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of countdata
-- ----------------------------
INSERT INTO `countdata` VALUES ('2015/10/10', '22368312', '0', '135868', '0', '', '');
INSERT INTO `countdata` VALUES ('2015/10/11', '22368312', '0', '135868', '0', '', '');
INSERT INTO `countdata` VALUES ('2015/10/12', '22368312', '0', '135868', '0', '', '');
INSERT INTO `countdata` VALUES ('2015/10/13', '23260824', '892512', '160667', '24799', '10', '');
INSERT INTO `countdata` VALUES ('2015/10/14', '23355612', '94788', '163300', '2633', '11.4', '1.4');

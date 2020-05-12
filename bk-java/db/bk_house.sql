CREATE DATABASE /*!32312 IF NOT EXISTS*/`bk_house` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `bk_house`;

DROP TABLE IF EXISTS `house_info`;

CREATE TABLE `house_info` (
  `maidian` varchar(32) NOT NULL COMMENT '卖点值(默认按该值降序)',
  `title` varchar(256) DEFAULT NULL COMMENT '标题',
  `href` varchar(256) DEFAULT NULL COMMENT '链接',
  `neighbourhood` varchar(64) DEFAULT NULL COMMENT '小区',
  `floor` varchar(64) DEFAULT NULL COMMENT '楼层',
  `build_year` varchar(64) DEFAULT NULL COMMENT '建于哪年',
  `type` varchar(64) DEFAULT NULL COMMENT '户型',
  `size` decimal(10,2) DEFAULT NULL COMMENT '面积(平方)',
  `face_toward` varchar(64) DEFAULT NULL COMMENT '朝向',
  `focus` int(11) DEFAULT '0' COMMENT '关注人数',
  `publish_time` varchar(64) DEFAULT NULL COMMENT '发布时间',
  `total_price` decimal(10,2) DEFAULT NULL COMMENT '总价(万元)',
  `unit_price` decimal(10,2) DEFAULT NULL COMMENT '单价(元)',
  `good_house` varchar(64) DEFAULT NULL COMMENT '必看好房',
  `new_up` varchar(64) DEFAULT NULL COMMENT '新上房源',
  PRIMARY KEY (`maidian`),
  UNIQUE KEY `href` (`href`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='在售房源信息';

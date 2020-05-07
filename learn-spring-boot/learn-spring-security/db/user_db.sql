/*
SQLyog Ultimate
MySQL - 5.7.17-log : Database - user_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`user_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `user_db`;

/*Table structure for table `t_permission` */

DROP TABLE IF EXISTS `t_permission`;

CREATE TABLE `t_permission` (
  `id` varchar(32) NOT NULL,
  `code` varchar(32) NOT NULL COMMENT '权限标识符',
  `description` varchar(64) DEFAULT NULL COMMENT '描述',
  `url` varchar(128) DEFAULT NULL COMMENT '请求地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';

/*Data for the table `t_permission` */

insert  into `t_permission`(`id`,`code`,`description`,`url`) values ('1','p1','测试资源1','/r/r1');
insert  into `t_permission`(`id`,`code`,`description`,`url`) values ('2','p2','测试资源2','/r/r2');

/*Table structure for table `t_role` */

DROP TABLE IF EXISTS `t_role`;

CREATE TABLE `t_role` (
  `id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `role_name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` char(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_role_name` (`role_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

/*Data for the table `t_role` */

insert  into `t_role`(`id`,`role_name`,`description`,`create_time`,`update_time`,`status`) values ('1','管理员',NULL,NULL,NULL,'1');
insert  into `t_role`(`id`,`role_name`,`description`,`create_time`,`update_time`,`status`) values ('2','会员',NULL,NULL,NULL,'1');

/*Table structure for table `t_role_permission` */

DROP TABLE IF EXISTS `t_role_permission`;

CREATE TABLE `t_role_permission` (
  `role_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `permission_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色权限关系表';

/*Data for the table `t_role_permission` */

insert  into `t_role_permission`(`role_id`,`permission_id`) values ('1','1');
insert  into `t_role_permission`(`role_id`,`permission_id`) values ('1','2');
insert  into `t_role_permission`(`role_id`,`permission_id`) values ('2','1');

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(64) CHARACTER SET utf8 NOT NULL,
  `password` varchar(64) CHARACTER SET utf8 NOT NULL,
  `fullname` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '用户姓名',
  `mobile` varchar(11) CHARACTER SET utf8 DEFAULT NULL COMMENT '手机号',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`fullname`,`mobile`) values (1,'su','$2a$10$5vnRl0L6aVnh9XjCkL9H8.1Plf2rmCwNQqT8v9nqEWYvDyONXXNUe','苏',NULL);
insert  into `t_user`(`id`,`username`,`password`,`fullname`,`mobile`) values (2,'huan','$2a$10$9Ggjsr.1tklwI0xVTeq64uX.opEWyVGO9GblCHcisoJbfy03nQeEe','欢欢',NULL);

/*Table structure for table `t_user_role` */

DROP TABLE IF EXISTS `t_user_role`;

CREATE TABLE `t_user_role` (
  `user_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `role_id` varchar(32) CHARACTER SET utf8 NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `creator` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户角色关系表';

/*Data for the table `t_user_role` */

insert  into `t_user_role`(`user_id`,`role_id`,`create_time`,`creator`) values ('1','1',NULL,NULL);
insert  into `t_user_role`(`user_id`,`role_id`,`create_time`,`creator`) values ('2','2',NULL,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

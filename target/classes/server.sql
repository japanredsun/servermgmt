/*
SQLyog Community v12.5.0 (32 bit)
MySQL - 5.7.20-log : Database - server
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`server` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `server`;

/*Table structure for table `servers` */

DROP TABLE IF EXISTS `servers`;

CREATE TABLE `servers` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `server` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `platform` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `ssh_userpassword` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `root_password` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `isAvailable` tinyint(1) DEFAULT '1',
  `owner` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `start_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `end_date` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `ams_version` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  `note` varchar(255) COLLATE utf8_unicode_ci DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `servers` */

insert  into `servers`(`id`,`server`,`platform`,`ssh_userpassword`,`root_password`,`created_date`,`isAvailable`,`owner`,`start_date`,`end_date`,`ams_version`,`note`) values 
(1,'192.168.92.201','RedHat/Linux','root/123456','123456\r\n','2018-01-04 14:06:20',0,'jr','4/1/2018','5/1/2018','9.5.1.2','Patch,EF\r\n'),
(2,'192.168.92.202','RedHat/Linux','root/123456','123456','2018-01-04 16:23:07',1,'','','','131313','PCC Server'),
(3,'192.168.92.203','RedHat/Linux','root/123456','123456','2018-01-05 11:35:02',1,NULL,NULL,NULL,'9.6.03',NULL),
(4,'192.168.92.204','RedHat/Linux','root/123456','123456','2018-01-05 11:46:05',1,NULL,NULL,NULL,'9604','Patch,EF'),
(5,'192.168.92.205','RedHat/Linux','root/123456','123456','2018-01-05 11:48:04',1,NULL,NULL,NULL,'9.6.05','no'),
(6,'192.168.92.70','RedHat/Linux','root/123456','123456','2018-01-05 11:54:56',1,NULL,NULL,NULL,'9503',''),
(7,'192.168.92.19','RedHat 6.7','root/123456','123456','2018-01-05 12:00:39',1,NULL,NULL,NULL,'9.6.02','Demo'),
(8,'192.168.92.28','RedHat 6.7','root/123456','123456','2018-01-05 12:09:01',1,NULL,NULL,NULL,'9.6.04','PCC Server'),
(9,'192.168.92.57','RedHat 6.7','root/123456','123456','2018-01-05 12:13:08',1,NULL,NULL,NULL,'9.6.05','APC'),
(10,'192.168.92.135','RedHat 6.7','root/123456','root123','2018-01-05 13:27:31',1,NULL,NULL,NULL,'9.6.05',''),
(11,'192.168.92.302','RedHat/Linux','root/123456','123456','2018-01-05 15:50:21',1,NULL,NULL,NULL,'131313','PCC Server'),
(12,'192.168.95.158','Redhat 6.8','root/123456','123456','2018-01-05 16:50:25',1,NULL,NULL,NULL,'9.6.05','Bug');

/*Table structure for table `user_roles` */

DROP TABLE IF EXISTS `user_roles`;

CREATE TABLE `user_roles` (
  `role_id` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `user_role` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_roles` */

insert  into `user_roles`(`role_id`,`username`,`user_role`,`created_date`) values 
(1,'nhmnhat@tma.com.vn','ADMIN','2018-01-03 10:49:58'),
(2,'nhmnhat@tma.com.vn','USER','2018-01-03 10:50:07'),
(3,'user1@tma.com.vn','USER','2018-01-03 10:50:27'),
(4,'japanredsun','USER','2018-01-03 16:05:42'),
(5,'japanredsun','ADMIN','2018-01-04 17:38:57'),
(6,'renjirou','USER','2018-01-05 10:05:00');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `username` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(255) COLLATE utf8_unicode_ci NOT NULL COMMENT 'md5',
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `enabled` tinyint(1) DEFAULT '1' COMMENT '1: active 0: inactive',
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `users` */

insert  into `users`(`username`,`password`,`created_date`,`enabled`) values 
('japanredsun','minhnhat102','2018-01-03 16:05:42',1),
('nhmnhat@tma.com.vn','12345','2018-01-03 10:49:16',1),
('renjirou','12345','2018-01-05 10:05:00',1),
('user1@tma.com.vn','12345','2018-01-03 10:49:37',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

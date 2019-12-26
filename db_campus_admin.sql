/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.27 : Database - yigang
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `yigang`;

/*Table structure for table `admin` */

CREATE TABLE `admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` char(15) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `nickname` char(10) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `sex` char(2) DEFAULT NULL,
  `phone` char(15) DEFAULT NULL,
  `email` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `admin_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

/*Data for the table `admin` */

LOCK TABLES `admin` WRITE;

insert  into `admin`(`id`,`username`,`password`,`nickname`,`role_id`,`sex`,`phone`,`email`) values (1,'201602801385','96e79218965eb72c92a549dd5a330112','一钢',1,'1','15503679159','yiigang@126.com'),(2,'201602801295','96e79218965eb72c92a549dd5a330112','张三',11,'0','15536307580','3503002112@qq.com'),(5,'201602800001','96e79218965eb72c92a549dd5a330112','李四',18,'2','11111111111','145268188@126.com'),(6,'201602800002','96e79218965eb72c92a549dd5a330112','王五',19,'1','18845261479','whn112@gmail.com'),(9,'Admin','29cbedc6ba516960661b9c436e57da20','体验管理员',20,'1','15698548526','tiyan@gmail.com');

UNLOCK TABLES;

/*Table structure for table `admin_log` */

CREATE TABLE `admin_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_username` char(15) DEFAULT NULL,
  `login_ip` char(20) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `logout_time` datetime DEFAULT NULL,
  `is_safe_exit` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;


/*Table structure for table `con_test` */

CREATE TABLE `con_test` (
  `a` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `con_test` */

LOCK TABLES `con_test` WRITE;

UNLOCK TABLES;

/*Table structure for table `course` */

/*Table structure for table `menu` */

CREATE TABLE `menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `icon` char(20) DEFAULT NULL,
  `href` char(100) DEFAULT NULL,
  `perms` char(100) DEFAULT NULL,
  `spread` char(10) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `sorting` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `menu` */

LOCK TABLES `menu` WRITE;

insert  into `menu`(`id`,`name`,`icon`,`href`,`perms`,`spread`,`parent_id`,`sorting`) values (1,'后台首页','&#xe68e;','',NULL,'false',0,9999),(2,'我的面板','&#xe770;',NULL,NULL,'false',0,999),(3,'个人信息','&#xe66f;','../admin/personalDate',NULL,'false',2,99),(4,'修改密码','&#xe673;','../admin/changePassword',NULL,'false',2,98),(5,'登录日志','&#xe60e;','../admin/adminLoginLog',NULL,'false',2,97),(6,'用户管理','&#xe612;',NULL,NULL,'false',0,998),(7,'用户列表','','../user/userList','','false',6,NULL),(8,'课程管理','&#xe656;',NULL,NULL,'false',0,997),(9,'课程列表','&#xe6b1;',NULL,NULL,'false',8,NULL),(11,'管理员管理','&#xe612;',NULL,NULL,'false',0,995),(12,'管理员列表','&#xe613','../admin/adminList',NULL,'false',11,96),(13,'角色管理','&#xe656;','../admin/roleList',NULL,'false',11,95),(14,'菜单管理','&#xe663;','../admin/menuList',NULL,'false',0,994);

UNLOCK TABLES;

/*Table structure for table `role_menu` */

CREATE TABLE `role_menu` (
  `role_id` int(11) DEFAULT NULL,
  `menu_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `role_menu` */

LOCK TABLES `role_menu` WRITE;

insert  into `role_menu`(`role_id`,`menu_id`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(1,8),(1,9),(1,11),(1,12),(1,13),(1,14),(11,1),(11,2),(11,3),(11,4),(11,5),(11,6),(11,7),(19,1),(19,2),(19,3),(19,4),(19,5),(19,6),(19,7),(19,8),(19,9),(19,11),(19,12),(19,13),(18,1),(18,2),(18,3),(18,4),(18,5),(18,8),(18,9),(20,1),(20,2),(20,3),(20,4),(20,5),(20,6),(20,7),(20,8),(20,9),(20,11),(20,12),(20,13),(20,14);

UNLOCK TABLES;

/*Table structure for table `roles` */

CREATE TABLE `roles` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` char(20) DEFAULT NULL,
  `role_remark` char(50) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Data for the table `roles` */

LOCK TABLES `roles` WRITE;

insert  into `roles`(`role_id`,`role_name`,`role_remark`) values (1,'超级管理员','超级管理员'),(11,'用户管理员','用户管理员'),(18,'课程管理员','课程管理员'),(19,'数据库管理员','数据库管理员');

UNLOCK TABLES;

/*Table structure for table `sc` */

CREATE TABLE `sc` (
  `id` char(10) DEFAULT NULL,
  `name` char(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `sc` */

LOCK TABLES `sc` WRITE;

UNLOCK TABLES;

/*Table structure for table `user` */

CREATE TABLE `user` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `username` char(15) DEFAULT NULL,
  `password` char(32) DEFAULT NULL,
  `sex` int(2) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `phone` char(11) DEFAULT NULL,
  `email` char(20) DEFAULT NULL,
  `address` char(100) DEFAULT NULL,
  `status` int(3) DEFAULT NULL,
  `role_id` int(3) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `note` text,
  PRIMARY KEY (`id`),
  KEY `user_roles` (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

LOCK TABLES `user` WRITE;

insert  into `user`(`id`,`username`,`password`,`sex`,`birthday`,`phone`,`email`,`address`,`status`,`role_id`,`create_time`,`note`) values (1,'yigang','9db06bcff9248837f86d1a6bcf41c9e7',1,'1997-03-15','15503679159','1004260908@qq.com','山西省太原市',1,24,'2019-05-13 17:34:34','心向远方，就要不顾风雨兼程！'),(3,'zhangsan','9db06bcff9248837f86d1a6bcf41c9e7',1,'2019-05-15','13854786954','144875757@qq.com','广东省广州市',0,23,'2019-05-14 22:39:12',''),(4,'xixige','9db06bcff9248837f86d1a6bcf41c9e7',0,'2019-04-30','18653458652','378377377@qq.com','山西省晋中市',0,21,'2019-05-14 22:43:34',''),(7,'xiaoji','9db06bcff9248837f86d1a6bcf41c9e7',1,'2019-05-07','15534685674','57545343@126.com','北京市昌平区',1,23,'2019-05-14 23:27:59',''),(10,'123a','9db06bcff9248837f86d1a6bcf41c9e7',0,'2019-04-30','13845454227','hhwt@163.com','陕西省西安市',0,21,'2019-05-15 08:08:55','天天学习，好好向上！'),(11,'wang163','9db06bcff9248837f86d1a6bcf41c9e7',2,'2019-05-08','15075272755','125uuo@gmail.com','山东省淄博市',1,22,'2019-05-15 09:11:49',''),(12,'xi666','9db06bcff9248837f86d1a6bcf41c9e7',0,'2019-05-07','13622772728','58272424@qq.com','北京市海淀区',0,21,'2019-05-15 09:14:21','');

UNLOCK TABLES;

/*Table structure for table `user_log` */


/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

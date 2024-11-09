/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.24-MariaDB : Database - electric_bike
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`electric_bike` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `electric_bike`;

/*Table structure for table `bunk` */

DROP TABLE IF EXISTS `bunk`;

CREATE TABLE `bunk` (
  `bunk_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`bunk_id`)
) ENGINE=MyISAM AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `bunk` */

insert  into `bunk`(`bunk_id`,`login_id`,`name`,`place`,`latitude`,`longitude`) values 
(15,15,'bunk2','ekm','9.976580665317236','76.27147827617837'),
(3,20,'electric bunk','kottayam','9.9763272','76.2862488'),
(4,22,'qwer','asdv','9.971944559554876','76.29550735496207'),
(16,23,'dsfa','dsf','9.978186784793973','76.28684196941568');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `sender_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `receiver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`sender_id`,`complaint`,`reply`,`date`,`receiver_id`) values 
(1,2,'dcvvb','good','2022-03-12',NULL),
(2,3,'dffg','pending','2022-03-13',NULL),
(3,4,'sdfhjkm','pending','2022-03-14',NULL),
(4,3,'sdghjjk','pending','2022-03-14',NULL),
(5,3,'sghjjk','pending','2022-03-14',NULL),
(6,3,'sfgvbnn','pending','2022-03-14',15),
(7,3,'fgyy','pending','2022-03-14',15),
(8,4,'xcv','pending','2022-03-14',15),
(12,1,'dsfhf gdfvzfddvdfvc vgdfhmnbv','pending','2023-01-17',0),
(11,1,'sdghdjmghn','pending','2023-01-17',0),
(13,2,'gjkgd','pending','2023-01-17',0),
(14,2,'jkfgkk','pending','2023-02-02',0),
(16,4,'jjff','pending','2023-02-03',0),
(17,4,'hello','pending','2023-02-03',11),
(18,4,'yyyyyyyyy','pending','2023-02-03',12),
(19,4,'uiggu','pending','2023-02-03',8),
(20,4,'ssghd','pending','2023-02-03',11),
(21,4,'hello','pending','2023-02-03',15),
(22,4,'gjkbbh','pending','2023-02-03',15);

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values 
(1,'admin','admin','admin'),
(2,'mmm1','mmm1','mechanic'),
(3,'anu','anu','mechanic'),
(4,'user1','user1','user'),
(5,'m3','m3','mechanic'),
(9,'ddd','ddd','spare'),
(17,'sss','sss','user'),
(15,'b2','b2','bunk'),
(20,'eee','eee','bunk'),
(21,'mchnc','mchcdfdgfdh','mechanic'),
(22,'bunk1','bunk1123','bunk'),
(23,'fddsgfhsg','fgfghgjh','bunk'),
(27,'zajonimed','Pa$$w0rd!','service_center'),
(25,'m1','m1','pending'),
(26,'ihhj','jhhj','pending');

/*Table structure for table `mechanic` */

DROP TABLE IF EXISTS `mechanic`;

CREATE TABLE `mechanic` (
  `mechanic_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`mechanic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `mechanic` */

insert  into `mechanic`(`mechanic_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`,`status`) values 
(2,2,'anu','ssssss','ekm','2563201478','a@gmail.com','10.5155119','76.2180251','accepted'),
(3,5,'mechanic3','g','chalakudi','5632014523','as@gmail.com','9.9762963','76.2863048',NULL),
(6,26,'sa','aa','ss','144','add@ss.ss','10.5155117','76.2180253',NULL);

/*Table structure for table `mechanicrequest` */

DROP TABLE IF EXISTS `mechanicrequest`;

CREATE TABLE `mechanicrequest` (
  `mrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `mechanic_id` int(11) DEFAULT NULL,
  `serviceamount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`mrequest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `mechanicrequest` */

insert  into `mechanicrequest`(`mrequest_id`,`user_id`,`mechanic_id`,`serviceamount`,`date`,`status`,`vehicle_id`) values 
(12,3,2,'500','2022-03-14','reject',NULL),
(13,1,2,'500','2023-02-17','pending',1),
(14,1,2,'pending','2023-02-17','pending',1);

/*Table structure for table `my_type` */

DROP TABLE IF EXISTS `my_type`;

CREATE TABLE `my_type` (
  `my_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `bunk_id` int(11) DEFAULT NULL,
  `type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`my_type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

/*Data for the table `my_type` */

insert  into `my_type`(`my_type_id`,`bunk_id`,`type_id`) values 
(1,1,1),
(2,15,5),
(6,15,2),
(7,15,2),
(8,15,6),
(9,15,6);

/*Table structure for table `order` */

DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `order_id` int(11) NOT NULL AUTO_INCREMENT,
  `sparepart_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`order_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

/*Data for the table `order` */

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `detail_id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`detail_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`detail_id`,`order_id`,`product_id`,`amount`,`date`) values 
(13,4,1,'1000','2022-03-14'),
(12,4,2,'250','2022-03-14'),
(11,4,1,'1000','2022-03-14');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `requested_id` int(11) DEFAULT NULL,
  `requestedfor` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`requested_id`,`requestedfor`,`amount`,`date`) values 
(1,12,'mechanicrequest','1000','2022-03-12'),
(2,2,'rechargerequest','250','2022-03-13'),
(3,1,'servicerequest','2000','2022-03-14'),
(4,2,'sparepartbuy','700','2022-03-14'),
(5,2,'sparepartbuy','700','2022-03-14'),
(6,2,'sparepartbuy','700','2022-03-14'),
(7,4,'rechargerequest','1000','2022-03-14'),
(8,4,'sparepartbuy','500','2022-03-14'),
(9,11,'sparepartbuy','None','2023-02-02'),
(10,13,'mechanicrequest','500','2023-02-03');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `sparepart_id` int(11) DEFAULT NULL,
  `product_name` varchar(100) DEFAULT NULL,
  `quantity` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `image` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `product` */

insert  into `product`(`product_id`,`sparepart_id`,`product_name`,`quantity`,`amount`,`image`) values 
(1,4,'spare 1','10','1000','static/uploads/2fc0e41d-d0af-45e2-99a7-34decfd02a80abc.jpg'),
(2,4,'addf','50','250','static/uploads/afb2c9a7-1b00-4229-8335-58508c4325d4abc.jpg'),
(3,4,'fhhh','15','2000','static/uploads/96c2f20e-82c3-4d78-ace8-a14dd772d91dabc.jpg'),
(4,4,'cgshdj','20','500','static/uploads/e0b1f3cc-194b-4ca8-8e27-3535a4a83033abc.jpg'),
(5,4,'cgshdj','20','500','static/uploads/17825231-ea58-4edc-bb53-2296ba4e6297abc.jpg'),
(6,4,'dffgg','10','800','static/uploads/06a85a4d-f9ba-4759-b0e1-7f1b243dae02abc.jpg'),
(7,4,'sdf','8','200','static/uploads/f9c4fb18-66bf-4b9c-8b2f-b22f963e4017abc.jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `requested_id` int(11) DEFAULT NULL,
  `rating` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`requested_id`,`rating`,`date`) values 
(1,3,6,'2.0','curdate()'),
(2,3,4,'5.0','curdate()'),
(3,1,2,'4.5','2023-02-03'),
(4,1,3,'3.0','curdate()'),
(5,3,5,'2.0','curdate()'),
(6,3,12,'3.0','curdate()'),
(7,1,11,'3.0','2023-02-02');

/*Table structure for table `rechargerequest` */

DROP TABLE IF EXISTS `rechargerequest`;

CREATE TABLE `rechargerequest` (
  `rrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `my_type_id` int(11) DEFAULT NULL,
  `amount` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `date` varchar(100) CHARACTER SET latin1 DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  `status` varchar(25) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`rrequest_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4;

/*Data for the table `rechargerequest` */

insert  into `rechargerequest`(`rrequest_id`,`user_id`,`my_type_id`,`amount`,`date`,`vehicle_id`,`status`) values 
(4,3,2,'300','2022-03-14',NULL,NULL),
(5,3,2,'90','2022-03-14',NULL,NULL),
(6,3,3,'how much','2022-03-14',NULL,NULL),
(7,3,3,'how much','2022-03-14',NULL,NULL),
(8,1,6,NULL,'2023-01-20',2,'pending'),
(11,1,6,'500','9/2/2023',1,'pending'),
(12,1,6,NULL,'2023/02/09',2,'pending'),
(13,1,2,NULL,'2023-02-17',1,'pending'),
(14,1,2,NULL,'2023-02-17',1,'pending'),
(15,1,2,'50000','1',1,'accepted');

/*Table structure for table `serive_center_request` */

DROP TABLE IF EXISTS `serive_center_request`;

CREATE TABLE `serive_center_request` (
  `ser_request_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `vehicle_id` int(11) DEFAULT NULL,
  `center_id` int(11) DEFAULT NULL,
  `servicceamount` int(11) DEFAULT NULL,
  `date` varchar(25) DEFAULT NULL,
  `status` varchar(25) DEFAULT NULL,
  PRIMARY KEY (`ser_request_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `serive_center_request` */

insert  into `serive_center_request`(`ser_request_id`,`user_id`,`vehicle_id`,`center_id`,`servicceamount`,`date`,`status`) values 
(1,1,1,1,500,'5152652','pending');

/*Table structure for table `service_center` */

DROP TABLE IF EXISTS `service_center`;

CREATE TABLE `service_center` (
  `center_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(50) DEFAULT NULL,
  `lastname` varchar(50) DEFAULT NULL,
  `place` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`center_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `service_center` */

insert  into `service_center`(`center_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`) values 
(1,24,'qwer','last','dsf','9234567890','sdfd@fghg.fgh','9.97108598617003','76.28641281597329'),
(3,27,'Colton','Perez','Rerum praesentium ip','+1 (845) 594-8039','pidahexe@mailinator.com','Mollit iste doloremq','Possimus autem plac');

/*Table structure for table `sparepart` */

DROP TABLE IF EXISTS `sparepart`;

CREATE TABLE `sparepart` (
  `sparepart_id` float NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`sparepart_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `sparepart` */

insert  into `sparepart`(`sparepart_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`) values ;

/*Table structure for table `station_type` */

DROP TABLE IF EXISTS `station_type`;

CREATE TABLE `station_type` (
  `type_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_name` varchar(50) DEFAULT NULL,
  `voltage` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `station_type` */

insert  into `station_type`(`type_id`,`type_name`,`voltage`) values 
(2,'Two wheeler Fast Charging','200'),
(6,'Fast Charging Four Wheeler','550');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login_id` int(11) DEFAULT NULL,
  `firstname` varchar(100) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `place` varchar(100) DEFAULT NULL,
  `phone` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `latitude` varchar(100) DEFAULT NULL,
  `longitude` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`user_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`) values 
(1,4,'anu','dddd','ekm','12345678','anu@gmail.com','',''),
(3,17,'sneha','s','dddfghh','7036589645','s@gmail.com','9.9763195','76.2862961');

/*Table structure for table `vehicle` */

DROP TABLE IF EXISTS `vehicle`;

CREATE TABLE `vehicle` (
  `vehicle_id` int(11) NOT NULL AUTO_INCREMENT,
  `vehicle_name` varchar(75) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `type` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`vehicle_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `vehicle` */

insert  into `vehicle`(`vehicle_id`,`vehicle_name`,`user_id`,`type`) values 
(1,'fdhgf',1,'Two Wheeler');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.10-MariaDB : Database - electric_bike
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
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `bunk` */

insert  into `bunk`(`bunk_id`,`login_id`,`name`,`place`,`latitude`,`longitude`) values (2,15,'bunk2','ekm','9.9763214','76.2863198'),(3,20,'electric bunk','kottayam','9.9763272','76.2862488');

/*Table structure for table `complaint` */

DROP TABLE IF EXISTS `complaint`;

CREATE TABLE `complaint` (
  `complaint_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `complaint` varchar(100) DEFAULT NULL,
  `reply` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`complaint_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `complaint` */

insert  into `complaint`(`complaint_id`,`user_id`,`complaint`,`reply`,`date`) values (1,2,'dcvvb','pending','2022-03-12'),(2,3,'dffg','pending','2022-03-13'),(3,4,'sdfhjkm','pending','2022-03-14'),(4,3,'sdghjjk','pending','2022-03-14'),(5,3,'sghjjk','pending','2022-03-14'),(6,3,'sfgvbnn','pending','2022-03-14'),(7,3,'fgyy','pending','2022-03-14'),(8,4,'xcv','pending','2022-03-14');

/*Table structure for table `login` */

DROP TABLE IF EXISTS `login`;

CREATE TABLE `login` (
  `login_id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `usertype` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`login_id`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;

/*Data for the table `login` */

insert  into `login`(`login_id`,`username`,`password`,`usertype`) values (1,'admin','admin','admin'),(2,'mmm1','mmm1','mechanic'),(3,'anu','anu','mechanic'),(4,'user1','user1','user'),(5,'m3','m3','mechanic'),(9,'ddd','ddd','spare'),(19,'ggg','ggg','delivery_boy'),(18,'alby','alby','delivery_boy'),(17,'sss','sss','user'),(15,'b2','b2','bunk'),(20,'eee','eee','bunk');

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
  PRIMARY KEY (`mechanic_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `mechanic` */

insert  into `mechanic`(`mechanic_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`) values (2,3,'anu','ss','ekm','2563201478','a@gmail.com','9.9763719','76.2862977'),(3,5,'mechanic3','g','chalakudi','5632014523','as@gmail.com','9.9762963','76.2863048');

/*Table structure for table `mechanicrequest` */

DROP TABLE IF EXISTS `mechanicrequest`;

CREATE TABLE `mechanicrequest` (
  `mrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `mechanic_id` int(11) DEFAULT NULL,
  `serviceamount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  `status` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`mrequest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;

/*Data for the table `mechanicrequest` */

insert  into `mechanicrequest`(`mrequest_id`,`user_id`,`mechanic_id`,`serviceamount`,`date`,`status`) values (12,3,3,'70','2022-03-14','cancelled');

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
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `order` */

insert  into `order`(`order_id`,`sparepart_id`,`user_id`,`amount`,`date`,`status`) values (4,4,3,'2250','2022-03-14','accept');

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

insert  into `orderdetail`(`detail_id`,`order_id`,`product_id`,`amount`,`date`) values (13,4,1,'1000','2022-03-14'),(12,4,2,'250','2022-03-14'),(11,4,1,'1000','2022-03-14');

/*Table structure for table `payment` */

DROP TABLE IF EXISTS `payment`;

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL AUTO_INCREMENT,
  `requested_id` int(11) DEFAULT NULL,
  `requestedfor` varchar(100) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`payment_id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

/*Data for the table `payment` */

insert  into `payment`(`payment_id`,`requested_id`,`requestedfor`,`amount`,`date`) values (1,4,'sparepartbuy','1000','2022-03-12'),(2,2,'sparepartbuy','250','2022-03-13'),(3,4,'sparepartbuy','2000','2022-03-14'),(4,2,'sparepartbuy','700','2022-03-14'),(5,2,'sparepartbuy','700','2022-03-14'),(6,2,'sparepartbuy','700','2022-03-14'),(7,4,'sparepartbuy','1000','2022-03-14'),(8,4,'sparepartbuy','500','2022-03-14');

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

insert  into `product`(`product_id`,`sparepart_id`,`product_name`,`quantity`,`amount`,`image`) values (1,4,'spare 1','10','1000','static/uploads/2fc0e41d-d0af-45e2-99a7-34decfd02a80abc.jpg'),(2,4,'addf','50','250','static/uploads/afb2c9a7-1b00-4229-8335-58508c4325d4abc.jpg'),(3,4,'fhhh','15','2000','static/uploads/96c2f20e-82c3-4d78-ace8-a14dd772d91dabc.jpg'),(4,4,'cgshdj','20','500','static/uploads/e0b1f3cc-194b-4ca8-8e27-3535a4a83033abc.jpg'),(5,4,'cgshdj','20','500','static/uploads/17825231-ea58-4edc-bb53-2296ba4e6297abc.jpg'),(6,4,'dffgg','10','800','static/uploads/06a85a4d-f9ba-4759-b0e1-7f1b243dae02abc.jpg'),(7,4,'sdf','8','200','static/uploads/f9c4fb18-66bf-4b9c-8b2f-b22f963e4017abc.jpg');

/*Table structure for table `rating` */

DROP TABLE IF EXISTS `rating`;

CREATE TABLE `rating` (
  `rating_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `requested_id` int(11) DEFAULT NULL,
  `rating` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rating_id`)
) ENGINE=MyISAM AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `rating` */

insert  into `rating`(`rating_id`,`user_id`,`requested_id`,`rating`,`date`) values (1,3,6,'2.0','curdate()'),(2,3,4,'5.0','curdate()'),(3,3,2,'3.0','curdate()'),(4,3,3,'3.0','curdate()'),(5,3,5,'2.0','curdate()'),(6,3,12,'3.0','curdate()');

/*Table structure for table `rechargerequest` */

DROP TABLE IF EXISTS `rechargerequest`;

CREATE TABLE `rechargerequest` (
  `rrequest_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `bunk_id` int(11) DEFAULT NULL,
  `amount` varchar(100) DEFAULT NULL,
  `date` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`rrequest_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

/*Data for the table `rechargerequest` */

insert  into `rechargerequest`(`rrequest_id`,`user_id`,`bunk_id`,`amount`,`date`) values (1,3,2,'how much','2022-03-14'),(2,3,2,'700','2022-03-14'),(3,3,2,'how much','2022-03-14'),(4,3,2,'500','2022-03-14'),(5,3,2,'90','2022-03-14'),(6,3,3,'how much','2022-03-14'),(7,3,3,'how much','2022-03-14');

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

insert  into `sparepart`(`sparepart_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`) values (4,9,'sonu','dfffghh','gg','8945236874','d@gmail.com','9.9763174','76.2862741'),(5,18,'alby','a','Chalakudi','8945698087','a@gmail.com','9.9763249','76.2863348'),(6,19,'Georgekutty ','biju','kottayam','8969458087','g@gmail.com','9.9762934','76.2863085');

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

insert  into `user`(`user_id`,`login_id`,`firstname`,`lastname`,`place`,`phone`,`email`,`latitude`,`longitude`) values (1,8,'anu','d','ekm','12345678','a@gmail.com','9.9763304','76.2862256'),(3,17,'sneha','s','dddfghh','7036589645','s@gmail.com','9.9763195','76.2862961');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

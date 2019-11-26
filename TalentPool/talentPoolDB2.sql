/*
SQLyog Community v12.12 (64 bit)
MySQL - 10.1.37-MariaDB : Database - talent_pool
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`talent_pool` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `talent_pool`;

/*Table structure for table `talent_candidate_experience_details` */

DROP TABLE IF EXISTS `talent_candidate_experience_details`;

CREATE TABLE `talent_candidate_experience_details` (
  `id` int(11) NOT NULL,
  `technology_used` varchar(255) DEFAULT NULL,
  `candidate_unique_id` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `end_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `project_name` varchar(255) DEFAULT NULL,
  `start_date` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `talent_candidate_experience_details` */

/*Table structure for table `talent_candidate_personal_details` */

DROP TABLE IF EXISTS `talent_candidate_personal_details`;

  `alternate_email_id` varchar(255) DEFAULT NULL,
  `candidate_uniqe_id` varchar(255) DEFAULT NULL,
  `contact_number` bigint(20) DEFAULT NULL,
  `email_id` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `grade` varchar(255) DEFAULT NULL,
  `institute_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `middle_name` varchar(255) DEFAULT NULL,
  `passing_year` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`candidate_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `talent_candidate_personal_details` */

/*Table structure for table `talent_location_questions` */

DROP TABLE IF EXISTS `talent_location_questions`;

CREATE TABLE `talent_location_questions` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `question_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `location_id_idx` (`location_id`),
  KEY `question_id_idx` (`question_id`),
  CONSTRAINT `location_id` FOREIGN KEY (`location_id`) REFERENCES `talent_locations` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `question_id` FOREIGN KEY (`question_id`) REFERENCES `talent_questions` (`question_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `talent_location_questions` */

/*Table structure for table `talent_locations` */

DROP TABLE IF EXISTS `talent_locations`;

CREATE TABLE `talent_locations` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'Unique ID',
  `country` varchar(50) NOT NULL COMMENT 'Country Name',
  `state` varchar(50) DEFAULT NULL COMMENT 'state',
  `city` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

/*Data for the table `talent_locations` */

insert  into `talent_locations`(`location_id`,`country`,`state`,`city`) values (1,'USA','VA','MCLEan'),(2,'USA','VA','FairFax'),(3,'USA','VA','Vienna'),(4,'USA','IL','Chicago'),(5,'USA','IL',NULL);

/*Table structure for table `talent_question_answers` */

DROP TABLE IF EXISTS `talent_question_answers`;

CREATE TABLE `talent_question_answers` (
  `id` int(11) NOT NULL,
  `answer_description` varchar(255) DEFAULT NULL,
  `answer_option` varchar(255) DEFAULT NULL,
  `question_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `talent_question_answers` */

/*Table structure for table `talent_questions` */

DROP TABLE IF EXISTS `talent_questions`;

CREATE TABLE `talent_questions` (
  `question_id` int(11) NOT NULL COMMENT 'Unique autogenerated questionID',
  `question_name` varchar(250) NOT NULL,
  `is_active` varchar(1) NOT NULL DEFAULT 'Y',
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `talent_questions` */

insert  into `talent_questions`(`question_id`,`question_name`,`is_active`) values (1,'What kind of Job you are looking for','Y');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

CREATE DATABASE  IF NOT EXISTS `blog2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `blog2`;
-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: localhost    Database: blog2
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `categories`
--

DROP TABLE IF EXISTS `categories`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `categories` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ivqt9e06jfskg7wb1jfpf6836` (`category_id`),
  CONSTRAINT `FK_ivqt9e06jfskg7wb1jfpf6836` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categories`
--

LOCK TABLES `categories` WRITE;
/*!40000 ALTER TABLE `categories` DISABLE KEYS */;
INSERT INTO `categories` VALUES (3,'Uncategoried',NULL),(19,'News',NULL),(20,'Politics',21),(21,'Belarus',19),(23,'Economics',21),(24,'Finance',21),(25,'Sport',21),(40,'Russia',19);
/*!40000 ALTER TABLE `categories` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `record_has_tag`
--

DROP TABLE IF EXISTS `record_has_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `record_has_tag` (
  `record_id` bigint(20) NOT NULL,
  `tag_id` bigint(20) NOT NULL,
  PRIMARY KEY (`record_id`,`tag_id`),
  KEY `FK_96i0o2jwfgip37kh5w458c40` (`tag_id`),
  CONSTRAINT `FK_96i0o2jwfgip37kh5w458c40` FOREIGN KEY (`tag_id`) REFERENCES `tags` (`id`),
  CONSTRAINT `FK_me6270utoexe5crehp2105pm9` FOREIGN KEY (`record_id`) REFERENCES `records` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `record_has_tag`
--

LOCK TABLES `record_has_tag` WRITE;
/*!40000 ALTER TABLE `record_has_tag` DISABLE KEYS */;
INSERT INTO `record_has_tag` VALUES (1,1),(1,2),(3,3),(9,3),(10,3),(11,3),(12,3),(13,3),(14,3),(15,3),(28,3),(29,3),(34,3),(37,3),(38,3),(2,4),(29,9),(29,10),(34,14),(37,15),(38,16);
/*!40000 ALTER TABLE `record_has_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `records` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `body` varchar(10000) DEFAULT NULL,
  `created_at` datetime DEFAULT NULL,
  `header` varchar(255) DEFAULT NULL,
  `modified_at` datetime DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_3g7aoa5j1vktuy88x5ffaueok` (`author_id`),
  KEY `FK_qalbtushf6xr9s51e1w5io6uh` (`category_id`),
  CONSTRAINT `FK_3g7aoa5j1vktuy88x5ffaueok` FOREIGN KEY (`author_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FK_qalbtushf6xr9s51e1w5io6uh` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (1,'Просто текст','2017-03-31 14:39:56','Заголовок',NULL,1,3),(2,'asdfasdfxdf asdf sad fsd ','2017-03-31 14:43:18','zdfsadf!!!','2017-03-31 14:47:51',1,3),(3,'asd','2017-03-31 14:50:41','sad',NULL,1,3),(9,'adfadsfasdf','2017-03-31 15:45:20','dsafasdf',NULL,1,3),(10,'asdfasdferfwefe','2017-04-03 13:18:00','dsfasdfas',NULL,1,3),(11,'awef awefasef awerf awefaw3qerf zsdc\\a s hsdfssdf qerw','2017-04-03 13:18:09','dsfsad sdfasdf asefwerf',NULL,1,3),(12,'asedf awefawetr asedfad rgraedwe rergered','2017-04-03 13:18:24','sadfref aefr aewf aerf ',NULL,1,3),(13,'asdf ertgasf ergter er','2017-04-03 13:18:31','dsf asrdrfergaedsrf erfge',NULL,1,3),(14,'asdf ergewrgf segaeafe','2017-04-03 13:18:41','asdfg asdf ergarea',NULL,1,3),(15,'asfr ergferrew greadre ','2017-04-03 13:18:49','adfghasrder dsrfaeer rf ','2017-04-09 18:35:20',1,23),(28,'Record body!','2017-04-09 23:05:40','New Record!','2017-04-09 23:05:54',1,23),(29,'Всё плохо...','2017-04-09 23:07:30','Тестовая запись','2017-04-28 01:39:55',1,25),(34,'<p><strong>News</strong> from <em>Russia</em></p>','2017-04-28 19:44:55','Test','2017-05-01 22:50:18',1,40),(37,'<p style=\"margin: 0px 0px 15px; padding: 0px; text-align: justify; font-family: \'Open Sans\', Arial, sans-serif;\">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eu ultrices felis. Fusce lacus enim, interdum in lacinia nec, placerat eu lectus. Mauris euismod nunc quis velit gravida rhoncus. Phasellus tincidunt nulla elit, et maximus ex ornare at. Cras consectetur sapien ut ligula scelerisque feugiat. Donec id orci ut ligula vulputate porttitor a eu urna. Maecenas vel cursus odio. Vestibulum eget tortor at neque dictum tincidunt eget id mauris. Cras volutpat a ex et vestibulum.</p>\r\n<blockquote>\r\n<p style=\"margin: 0px 0px 15px; padding: 0px; text-align: justify; font-family: \'Open Sans\', Arial, sans-serif;\">Quisque purus purus, ultricies a sapien eu, cursus mattis enim. Maecenas eu arcu urna. Praesent tincidunt facilisis aliquam. Fusce aliquet ac augue eget elementum. Nulla facilisi. Donec ac tincidunt ante, vitae semper risus. Donec velit diam, posuere eu eros sit amet, facilisis euismod metus. Sed vitae aliquet nisi. Quisque facilisis rhoncus nibh ut ultrices.</p>\r\n</blockquote>\r\n<p style=\"margin: 0px 0px 15px; padding: 0px; text-align: justify; font-family: \'Open Sans\', Arial, sans-serif;\">Duis facilisis est vitae hendrerit mattis. Duis vel gravida sapien. Maecenas congue, urna sit amet tincidunt ullamcorper, quam dui consequat ex, ac dictum massa nulla id est. Proin ac congue velit, id convallis massa. Duis imperdiet pretium lorem id accumsan. Maecenas sollicitudin venenatis ligula et elementum. Curabitur tristique nunc non efficitur pretium. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Donec at mollis enim. Fusce convallis, neque et egestas fermentum, lectus nisi malesuada lacus, ac semper ex ipsum vel est. Maecenas quis suscipit magna, eget porttitor metus. Quisque et dignissim diam. Sed porta arcu eu porta efficitur. Etiam ligula massa, tempus et est sit amet, rhoncus porttitor nunc. Vestibulum dolor ante, porttitor vitae ex sit amet, cursus tincidunt enim.</p>\r\n<p style=\"margin: 0px 0px 15px; padding: 0px; text-align: justify; font-family: \'Open Sans\', Arial, sans-serif;\">Sed aliquam ante at erat eleifend pellentesque. Vivamus viverra lobortis ultrices. Ut id purus at nisl mattis faucibus a id leo. Donec gravida suscipit erat, sit amet scelerisque ligula congue sit amet. Cras bibendum, quam ac ultricies elementum, libero ligula egestas sem, eu cursus enim arcu sed eros. Pellentesque mi ipsum, efficitur sit amet nunc non, sodales tempus massa. Curabitur at auctor purus, ut eleifend quam.</p>\r\n<p style=\"margin: 0px 0px 15px; padding: 0px; text-align: justify; font-family: \'Open Sans\', Arial, sans-serif;\">Nulla ultrices posuere malesuada. Donec id nunc ut eros ornare accumsan. Praesent pretium est a ligula convallis, nec vulputate felis consectetur. In ligula velit, aliquet ut porta varius, faucibus a augue. Suspendisse et imperdiet odio. Quisque faucibus, lacus a sodales vehicula, nunc orci euismod est, nec tincidunt turpis justo quis orci. Proin nec dolor facilisis, scelerisque nisi ut, lobortis lacus.</p>','2017-05-01 15:26:11','fdasdfasdf','2017-05-01 22:55:17',37,25),(38,'<h2>Привет всем!</h2>\r\n<p>Это попытка <span style=\"text-decoration: line-through;\">изнасило</span> прикрутить редактор <strong>TinyMCE</strong> к моему проекту.</p>','2017-05-01 22:33:30','Test','2017-05-01 22:56:57',1,19);
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `roles` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMINISTRATOR'),(2,'EDITOR'),(3,'MODERATOR'),(4,'USER'),(5,'ANONIMOUS');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tags`
--

DROP TABLE IF EXISTS `tags`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tags` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tags`
--

LOCK TABLES `tags` WRITE;
/*!40000 ALTER TABLE `tags` DISABLE KEYS */;
INSERT INTO `tags` VALUES (1,'начало'),(2,'тест'),(3,''),(4,'проверка'),(5,'Test'),(6,'Just test'),(9,'Футбол'),(10,'Дрова'),(12,'Вовка'),(13,'Пора спать'),(14,'Russia'),(15,'Вау'),(16,'Tinymce');
/*!40000 ALTER TABLE `tags` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `users_email_uindex` (`email`),
  UNIQUE KEY `users_nick_name_uindex` (`nick_name`),
  KEY `FK_krvotbtiqhudlkamvlpaqus0t` (`role_id`),
  CONSTRAINT `FK_krvotbtiqhudlkamvlpaqus0t` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin@test.com','Test','Admin','CriticalGnome','202cb962ac59075b964b07152d234b70',1),(23,'editor@test.com','Test','Editor','Skiminok','202cb962ac59075b964b07152d234b70',2),(37,'moder@test.com','Test','Moder','Moderator','202cb962ac59075b964b07152d234b70',3),(38,'user@test.com','Test','User','User','202cb962ac59075b964b07152d234b70',4);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-05 22:37:28

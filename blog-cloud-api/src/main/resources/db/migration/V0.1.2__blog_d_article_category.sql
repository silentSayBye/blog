CREATE DATABASE  IF NOT EXISTS `blog` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `blog`;
-- MySQL dump 10.13  Distrib 8.0.15, for Win64 (x86_64)
--
-- Host: localhost    Database: blog
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `d_article_category`
--

DROP TABLE IF EXISTS `d_article_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `d_article_category` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `category` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `type` char(1) COLLATE utf8_bin NOT NULL COMMENT '1- 系统分类 \\n2- 个人分类\\n3- 文章类型\\n4- 发布形式',
  `description` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `status` char(1) COLLATE utf8_bin DEFAULT '0',
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_article_category`
--

LOCK TABLES `d_article_category` WRITE;
/*!40000 ALTER TABLE `d_article_category` DISABLE KEYS */;
INSERT INTO `d_article_category` VALUES (1,'java','1','java','0',NULL,NULL,NULL,NULL),(2,'linux','1','linux','1','2020-02-18 05:48:29.635',NULL,'2020-02-18 05:48:29.635',NULL),(3,'jvm','1','jvm','1','2020-02-18 05:49:28.282',NULL,'2020-02-18 05:49:28.282',NULL),(4,'C','1','C','1','2020-02-18 05:57:48.484',NULL,'2020-02-18 05:57:48.484',NULL),(5,'C','1','C','1','2020-02-18 06:07:25.486',NULL,'2020-02-18 06:07:25.486',NULL),(6,'C','1','C','1','2020-02-18 06:08:02.561',NULL,'2020-02-18 06:08:02.561',NULL),(7,'C','1','C','1','2020-02-18 06:09:52.795',NULL,'2020-02-18 06:09:52.795',NULL),(8,'C','1','C','1','2020-02-18 06:10:35.249',NULL,'2020-02-18 06:10:35.249',NULL),(9,'C','1','C','1','2020-02-18 06:27:36.793',NULL,'2020-02-18 06:27:36.793',NULL),(10,'C','1','C','1','2020-02-18 06:27:53.688',NULL,'2020-02-18 06:27:53.688',NULL),(11,'C','1','C','1','2020-02-18 06:33:30.357',NULL,'2020-02-18 06:33:30.357',NULL),(12,'C','1','C','1','2020-02-18 06:34:18.261',NULL,'2020-02-18 06:34:18.261',NULL),(13,'C','1','C','1','2020-02-18 13:54:35.965','ces11234','2020-02-18 13:54:35.965','ces11234'),(14,'C','1','C','1','2020-02-18 13:55:50.782','ces11234','2020-02-18 13:55:50.782','ces11234'),(15,'C','1','C','1','2020-03-14 16:21:50.206',NULL,'2020-03-14 16:21:50.206',NULL);
/*!40000 ALTER TABLE `d_article_category` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-31 21:16:11

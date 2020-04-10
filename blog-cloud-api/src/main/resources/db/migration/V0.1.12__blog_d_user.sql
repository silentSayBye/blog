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
-- Table structure for table `d_user`
--

DROP TABLE IF EXISTS `d_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `d_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户主键',
  `user_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(1000) COLLATE utf8_bin DEFAULT NULL COMMENT '用户密码',
  `email` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '邮箱',
  `state` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '状态 默认为 1 可用     0 禁用',
  `img` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '用户头像url',
  `code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '激活码',
  `create_date` datetime DEFAULT NULL,
  `creator` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `updater` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_user`
--

LOCK TABLES `d_user` WRITE;
/*!40000 ALTER TABLE `d_user` DISABLE KEYS */;
INSERT INTO `d_user` VALUES (1,'zhangsan','aa','77680312@qq.com','1',NULL,'111',NULL,NULL,NULL,NULL),(4,'maliu',NULL,'776805161','0',NULL,'11',NULL,NULL,NULL,NULL),(5,'fdsffs','sfdfadfa',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(6,'fdswqwqffs','sfdfadfa',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(7,'fd1fahsdjkfffs','sfdfadfa',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(8,'ceshi','sfdfadfa',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(9,'ceshi1','sfdfadfa',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(10,'ceshi12','sfdfadfa',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(11,'ceshi12123','sfdfadfa',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(13,NULL,'sfdfadfa1',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(14,'ces1123','sfdfadfa1',NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL),(15,'ces11234','$2a$10$G7KutihTWX0MHMcyzyXPH.Zr4r69dqqo9HpZ1AbPXg9M091QUj1WG',NULL,'1',NULL,NULL,'2020-02-11 12:49:58','anonymousUser','2020-02-11 12:49:58','anonymousUser'),(16,'admin','$2a$10$60NWPNQV6VGYWFgfPNVTHukUUZSz20ehXVs.67B2/BiPdftgyE8AS',NULL,'1',NULL,NULL,'2020-02-14 20:21:25','anonymousUser','2020-02-14 20:21:25','anonymousUser');
/*!40000 ALTER TABLE `d_user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-31 21:16:13

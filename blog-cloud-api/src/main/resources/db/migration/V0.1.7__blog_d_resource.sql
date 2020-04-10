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
-- Table structure for table `d_resource`
--

DROP TABLE IF EXISTS `d_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `d_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `resource_name` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '资源名',
  `resource_code` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '资源code',
  `description` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '资源描述',
  `url` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '资源url',
  `parent_id` int(11) DEFAULT NULL COMMENT '父资源id',
  `order` int(11) DEFAULT NULL COMMENT '模块子功能序号',
  `level` int(11) DEFAULT NULL COMMENT '所处层级（父资源层级）',
  `type` int(11) DEFAULT NULL COMMENT '资源类型（function menu）',
  `http_method` varchar(45) COLLATE utf8_bin DEFAULT NULL COMMENT '方法类型',
  `state` char(1) COLLATE utf8_bin DEFAULT '1' COMMENT '状态 1 可用       0 禁用',
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `creator` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_resource`
--

LOCK TABLES `d_resource` WRITE;
/*!40000 ALTER TABLE `d_resource` DISABLE KEYS */;
INSERT INTO `d_resource` VALUES (1,'博客管理','BLOG_MANAGE','博客管理','/article/**',NULL,1,1,NULL,NULL,'1',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `d_resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-03-31 21:16:14

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
-- Table structure for table `d_resource_authority`
--

DROP TABLE IF EXISTS `d_resource_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `d_resource_authority` (
  `id` int(11) NOT NULL,
  `resource_id` int(11) NOT NULL,
  `authority_id` int(11) NOT NULL,
  `state` char(1) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `creator` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `create_date` timestamp(3) NULL DEFAULT NULL,
  `updater` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `update_date` timestamp(3) NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKr10j2oj9te8bf5m3v5l0gd2db` (`authority_id`),
  KEY `FKb620v2r7se7nf379ca3c30bcc` (`resource_id`),
  CONSTRAINT `FKb620v2r7se7nf379ca3c30bcc` FOREIGN KEY (`resource_id`) REFERENCES `d_resource` (`id`),
  CONSTRAINT `FKr10j2oj9te8bf5m3v5l0gd2db` FOREIGN KEY (`authority_id`) REFERENCES `d_authority` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='资源权限表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `d_resource_authority`
--

LOCK TABLES `d_resource_authority` WRITE;
/*!40000 ALTER TABLE `d_resource_authority` DISABLE KEYS */;
INSERT INTO `d_resource_authority` VALUES (1,1,1,'1',NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `d_resource_authority` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-26 20:55:45

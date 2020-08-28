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
-- Table structure for table `flyway_schema_history`
--

DROP TABLE IF EXISTS `flyway_schema_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `flyway_schema_history` (
  `installed_rank` int(11) NOT NULL,
  `version` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `description` varchar(200) COLLATE utf8_bin NOT NULL,
  `type` varchar(20) COLLATE utf8_bin NOT NULL,
  `script` varchar(1000) COLLATE utf8_bin NOT NULL,
  `checksum` int(11) DEFAULT NULL,
  `installed_by` varchar(100) COLLATE utf8_bin NOT NULL,
  `installed_on` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `execution_time` int(11) NOT NULL,
  `success` tinyint(1) NOT NULL,
  PRIMARY KEY (`installed_rank`),
  KEY `flyway_schema_history_s_idx` (`success`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `flyway_schema_history`
--

LOCK TABLES `flyway_schema_history` WRITE;
/*!40000 ALTER TABLE `flyway_schema_history` DISABLE KEYS */;
INSERT INTO `flyway_schema_history` VALUES (1,'0.1.0','<< Flyway Baseline >>','BASELINE','<< Flyway Baseline >>',NULL,'root','2020-03-31 14:39:16',0,1),(2,'0.1.1','blog d article','SQL','V0.1.1__blog_d_article.sql',-277536892,'root','2020-03-31 14:39:16',73,1),(3,'0.1.2','blog d article category','SQL','V0.1.2__blog_d_article_category.sql',-1641062393,'root','2020-03-31 14:39:16',58,1),(4,'0.1.3','blog d article content','SQL','V0.1.3__blog_d_article_content.sql',419608393,'root','2020-03-31 14:39:16',47,1),(5,'0.1.4','blog d authority','SQL','V0.1.4__blog_d_authority.sql',-1746632353,'root','2020-03-31 14:39:16',55,1),(6,'0.1.5','blog d comment','SQL','V0.1.5__blog_d_comment.sql',-520176340,'root','2020-03-31 14:39:16',53,1),(7,'0.1.6','blog d comment detail','SQL','V0.1.6__blog_d_comment_detail.sql',11561477,'root','2020-03-31 14:39:16',55,1),(8,'0.1.7','blog d resource','SQL','V0.1.7__blog_d_resource.sql',-1904162707,'root','2020-03-31 14:39:16',55,1),(9,'0.1.8','blog d resource authority','SQL','V0.1.8__blog_d_resource_authority.sql',261965898,'root','2020-03-31 14:39:16',61,1),(10,'0.1.9','blog d role','SQL','V0.1.9__blog_d_role.sql',-1375139537,'root','2020-03-31 14:39:17',73,1),(11,'0.1.10','blog d role authority','SQL','V0.1.10__blog_d_role_authority.sql',-1796012319,'root','2020-03-31 14:39:17',63,1),(12,'0.1.11','blog d role resource','SQL','V0.1.11__blog_d_role_resource.sql',-220877752,'root','2020-03-31 14:39:17',55,1),(13,'0.1.12','blog d user','SQL','V0.1.12__blog_d_user.sql',-480401473,'root','2020-03-31 14:39:17',64,1),(14,'0.1.13','blog d user role','SQL','V0.1.13__blog_d_user_role.sql',-1175012926,'root','2020-03-31 14:39:17',57,1),(15,'0.2.1','AlterArticleContent','SQL','V0.2.1__AlterArticleContent.sql',468373503,'root','2020-04-03 14:22:09',23,1);
/*!40000 ALTER TABLE `flyway_schema_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-08-26 20:55:43

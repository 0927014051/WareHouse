-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: warehouse
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `inventory_report`
--

DROP TABLE IF EXISTS `inventory_report`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `inventory_report` (
  `report_id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `type` varchar(10) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `g_order_id` bigint DEFAULT NULL,
  `request_id` bigint DEFAULT NULL,
  `staff_id_accept` bigint DEFAULT NULL,
  PRIMARY KEY (`report_id`),
  KEY `FK_g_order_id_idx` (`g_order_id`),
  KEY `FK_request_id_idx` (`request_id`),
  KEY `FKI_staff_id_idx` (`staff_id`),
  KEY `abccsadad` (`staff_id_accept`),
  CONSTRAINT `abccsadad` FOREIGN KEY (`staff_id_accept`) REFERENCES `staffs` (`staff_id`),
  CONSTRAINT `FK_g_order_id` FOREIGN KEY (`g_order_id`) REFERENCES `goods_order` (`g_order_id`),
  CONSTRAINT `FK_request_id` FOREIGN KEY (`request_id`) REFERENCES `request` (`request_id`),
  CONSTRAINT `FKI_staff_id` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `inventory_report`
--

LOCK TABLES `inventory_report` WRITE;
/*!40000 ALTER TABLE `inventory_report` DISABLE KEYS */;
INSERT INTO `inventory_report` VALUES (6,'2023-11-06','EXPORT',3,9,NULL,5,7),(7,'2023-11-06','EXPORT',3,9,NULL,6,7);
/*!40000 ALTER TABLE `inventory_report` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13  0:24:16

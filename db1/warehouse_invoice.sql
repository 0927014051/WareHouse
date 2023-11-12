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
-- Table structure for table `invoice`
--

DROP TABLE IF EXISTS `invoice`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `invoice` (
  `invoice_id` bigint NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `total_price` int DEFAULT NULL,
  `total_quantity` int DEFAULT NULL,
  `status` int DEFAULT NULL,
  `staff_id` bigint DEFAULT NULL,
  `g_issue_id` bigint DEFAULT NULL,
  `g_receipt_id` bigint DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `staff_id_accept` bigint DEFAULT NULL,
  PRIMARY KEY (`invoice_id`),
  KEY `FK_invoice_staff_idx` (`staff_id`),
  KEY `FK_g_receipt_id__idx` (`g_receipt_id`),
  KEY `FK_goodsIssue__idx` (`g_issue_id`),
  KEY `FK4vdfpdyokir0wore2do85nmjx` (`staff_id_accept`),
  CONSTRAINT `FK4vdfpdyokir0wore2do85nmjx` FOREIGN KEY (`staff_id_accept`) REFERENCES `staffs` (`staff_id`),
  CONSTRAINT `FK_g_receipt_id_` FOREIGN KEY (`g_receipt_id`) REFERENCES `goods_receipt` (`g_receipt_id`),
  CONSTRAINT `FK_goodsIssue_` FOREIGN KEY (`g_issue_id`) REFERENCES `goods_issue` (`g_issue_id`),
  CONSTRAINT `FK_invoice_staff` FOREIGN KEY (`staff_id`) REFERENCES `staffs` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invoice`
--

LOCK TABLES `invoice` WRITE;
/*!40000 ALTER TABLE `invoice` DISABLE KEYS */;
INSERT INTO `invoice` VALUES (4,'2023-11-06',34200,360,2,7,4,NULL,'EXPORT',10),(5,'2023-11-06',0,420,2,7,5,NULL,'EXPORT',10);
/*!40000 ALTER TABLE `invoice` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-11-13  0:24:14

-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: file
-- ------------------------------------------------------
-- Server version	8.1.0

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
-- Table structure for table `applicant`
--

DROP TABLE IF EXISTS `applicant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `applicant` (
  `email` varchar(255) NOT NULL,
  `salutations` varchar(25) NOT NULL,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `app_role` varchar(100) NOT NULL,
  `mobile_number` varchar(20) NOT NULL,
  `resume_filepath` varchar(255) DEFAULT NULL,
  `archive` tinyint NOT NULL,
  PRIMARY KEY (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `applicant`
--

LOCK TABLES `applicant` WRITE;
/*!40000 ALTER TABLE `applicant` DISABLE KEYS */;
INSERT INTO `applicant` VALUES ('guest1@gmail.com','Mr.','Nathaniel Denny','Guevarra','HR and Admin Manager (Full-time)','1234567890','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',0),('guest10@gmail.com','Mr.','Alex','Reyes','Sales and Marketing Manager (Full-Time)','09567890123','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest11@gmail.com','Mrs.','Eleni','Moraitis','Instructors (Part-time/Full-time)','09678901234','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest12@gmail.com','Dr.','Michael','Smith','IT Support (Full-Time)','09789012345','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',0),('guest13@gmail.com','Ms.','Sofia','Cruz','Admin Officer (Full-time)','09890123456','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',0),('guest14@gmail.com','Mr.','Yannis','Georgiou','Sales Officers (Full-time)','09901234567','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest15@gmail.com','Mrs.','Lisa','Wong','Graphic Designer (Part-Time)','09112345678','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest16@gmail.com','Ms.','Ava','Miller','Sales Officers (Full-time)','09123456791','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest17@gmail.com','Mr.','Mason','Lee','Graphic Designer (Part-time)','09123456792','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',0),('guest18@gmail.com','Ms.','Olivia','Taylor','Marketing and Communication Officer (Full-Time)','09123456793','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',0),('guest19@gmail.com','Mr.','Richard','Sanchez','IT Support (Full-Time)','09123456794','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest2@gmail.com','Mr.','Ramon','Tuazon','HR and Admin Manager (Full-time)','1987654321','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest20@gmail.com','Ms.','Sophia','Thomas','Admin Officer (Full-time)','09123456795','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest21@gmail.com','Mr.','Ethan','Jackson','HR and Admin Officer (Full-time)','09123456796','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest22@gmail.com','Ms.','Isabella','White','HR and Admin Manager (Full-time)','09123456797','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest23@gmail.com','Mr.','Daniel','Harris','Sales and Marketing Supervisor (Full-Time)','09123456798','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest24@gmail.com','Ms.','Mia','Martin','Sales and Marketing Manager (Full-Time)','09123456799','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest25@gmail.com','Mr.','Anthony','Garcia','Graphic Designer (Part-Time)','09123456800','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest26@gmail.com','Mr.','Alexander','Lee','Marketing and Communication Officer (Full-Time)','09123456806','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest27@gmail.com','Mrs.','Amelia','Walker','Sales and Marketing Supervisor (Full-Time)','09123456807','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest28@gmail.com','Mr.','Harper','Allen','Sales and Marketing Manager (Full-Time)','09123456808','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest29@gmail.com','Ms.','Abigail','Young','Graphic Designer (Part-Time)','09123456809','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest3@gmail.com','Mr.','Leonne Matthew','Dayao','IT Support (Full-Time)','0123456789','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest30@gmail.com','Mr.','Samuel','Hernandez','Instructors (Part-time/Full-time)','09123456810','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',0),('guest33@gmail.com','Mr.','Johnathan','Doughnut','HR and Admin Manager (Full-time)','09958439585','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\2CSC_DayaoDy_CS2617_MP5.pdf',0),('guest35@gmail.com','Ms.','Darcy','Samplename','HR and Admin Manager (Full-time)','09438594043','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\darcy-resume-12-21-2023.pdf',0),('guest37@gmail.com','Ms.','Darcy','Samplename2','Graphic Designer (Part-time)','09994739594','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\darcy-resume-12-21-2023.pdf',0),('guest4@gmail.com','Ms.','Eli Rayna','Gulifardo','HR and Admin Manager (Full-time)','1987654321','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',1),('guest5@gmail.com','Ms.','Edrine','Frances','HR and Admin Manager (Full-time)','1987654321','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',1),('guest6@gmail.com','Mr.','John','Doe','HR and Admin Manager (Full-time)','09123456789','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0),('guest7@gmail.com','Ms.','Maria','Santos','HR and Admin Officer (Full-time)','09234567890','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\Latest Resume.pdf',0),('guest8@gmail.com','Mr.','Nikos','Papadopoulos','Graphic Designer (Part-time)','09345678901','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\RICHARD_SANCHEZ_RESUME_2024 (1).pdf',1),('guest9@gmail.com','Ms.','Chloe','Kim','Marketing and Communication Officer (Full-Time)','09456789012','C:\\Users\\NEOPOT\\GlassFish_Server\\glassfish\\domains\\domain1\\config\\null\\admin5@gmail.com_20240510023754.pdf',0);
/*!40000 ALTER TABLE `applicant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-13  1:34:22

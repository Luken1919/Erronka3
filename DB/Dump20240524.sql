CREATE DATABASE  IF NOT EXISTS `erronka_pakag` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `erronka_pakag`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: erronka_pakag
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `arazoak`
--

DROP TABLE IF EXISTS `arazoak`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `arazoak` (
  `idArazoak` int NOT NULL AUTO_INCREMENT,
  `Describapena` varchar(80) NOT NULL,
  `Tituloa` varchar(45) NOT NULL,
  PRIMARY KEY (`idArazoak`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `arazoak`
--

LOCK TABLES `arazoak` WRITE;
/*!40000 ALTER TABLE `arazoak` DISABLE KEYS */;
INSERT INTO `arazoak` VALUES (1,'Helbidea ez da zuzena edo eguneratua','Helbide Errorea'),(2,'Kaltetutako bidalketa-etiketa','Etiketa Gaizki'),(3,'Pakete kaltetua','Pakete Kaltetuta'),(4,'Entregatzeko saiakerak huts egin du','Saiakera errorea'),(5,'Pakete galdua','Paketea Galduta'),(6,'Berandu entregatzea','Entrega Berandu'),(7,'Aduana-arazoak','Aduana Arazoa'),(8,'Erroreak fakturazioan','Fakturazio errorea'),(9,'Okerreko pertsonari entregatzea','Pertsona okerra');
/*!40000 ALTER TABLE `arazoak` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `entregatuta`
--

DROP TABLE IF EXISTS `entregatuta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `entregatuta` (
  `idEntregatuta` int NOT NULL AUTO_INCREMENT,
  `Bezero_zenbaki` varchar(9) NOT NULL,
  `Entrega_data` date NOT NULL,
  `Entrega_Ordua` varchar(45) NOT NULL,
  `Helbidea` varchar(100) NOT NULL,
  `Pakete_Tamaina` varchar(45) NOT NULL,
  `idPaketea` int NOT NULL,
  `erabiltzailea_idErabiltzailea` int NOT NULL,
  PRIMARY KEY (`idEntregatuta`),
  KEY `fk_Entregatuta_Paketea1_idx` (`idPaketea`),
  KEY `fk_entregatuta_erabiltzailea1_idx` (`erabiltzailea_idErabiltzailea`),
  CONSTRAINT `fk_entregatuta_erabiltzailea1` FOREIGN KEY (`erabiltzailea_idErabiltzailea`) REFERENCES `erabiltzailea` (`idErabiltzailea`),
  CONSTRAINT `fk_Entregatuta_Paketea1` FOREIGN KEY (`idPaketea`) REFERENCES `paketea` (`idPaketea`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `entregatuta`
--

LOCK TABLES `entregatuta` WRITE;
/*!40000 ALTER TABLE `entregatuta` DISABLE KEYS */;
INSERT INTO `entregatuta` VALUES (1,'12345678','2002-04-05','09:00','Tolsoa','Txikia',1,2);
/*!40000 ALTER TABLE `entregatuta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `erabiltzailea`
--

DROP TABLE IF EXISTS `erabiltzailea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `erabiltzailea` (
  `idErabiltzailea` int NOT NULL AUTO_INCREMENT,
  `Izena` varchar(45) NOT NULL,
  `Abizena` varchar(45) NOT NULL,
  `Pasahitza` varchar(45) NOT NULL,
  `Erab_Izena` varchar(45) NOT NULL,
  `Mota` varchar(45) NOT NULL,
  PRIMARY KEY (`idErabiltzailea`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `erabiltzailea`
--

LOCK TABLES `erabiltzailea` WRITE;
/*!40000 ALTER TABLE `erabiltzailea` DISABLE KEYS */;
INSERT INTO `erabiltzailea` VALUES (1,'Ioritz','Lopetegi','1234','iorilope','Kudeatzailea'),(2,'Julen','Merino','12345','julenmeri','Banatzailea'),(3,'Luken','Franko','1234','lukenfran','Banatzailea');
/*!40000 ALTER TABLE `erabiltzailea` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `erabiltzailea_BEFORE_INSERT` BEFORE INSERT ON `erabiltzailea` FOR EACH ROW BEGIN
DECLARE ErabiltzaileIzena VARCHAR(255);
    DECLARE ErabiltzaileaBadago VARCHAR(255);
    DECLARE Kontagailua INT DEFAULT 1;
    SET ErabiltzaileIzena = CONCAT(NEW.Izena, '.', NEW.Abizena);
    SET ErabiltzaileaBadago = ErabiltzaileIzena;
    WHILE EXISTS (SELECT 1 FROM erabiltzailea WHERE Erab_Izena = ErabiltzaileaBadago) DO
        SET ErabiltzaileIzena = CONCAT(ErabiltzaileIzena, Kontagailua);
        SET Kontagailua = Kontagailua + 1;
    END WHILE;
    SET NEW.Erab_Izena = ErabiltzaileIzena;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `gertatu`
--

DROP TABLE IF EXISTS `gertatu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gertatu` (
  `idArazoak` int NOT NULL,
  `idEntregatuta` int NOT NULL,
  PRIMARY KEY (`idArazoak`,`idEntregatuta`),
  KEY `fk_Arazoak_has_Entregatuta_Entregatuta1_idx` (`idEntregatuta`),
  KEY `fk_Arazoak_has_Entregatuta_Arazoak1_idx` (`idArazoak`),
  CONSTRAINT `fk_Arazoak_has_Entregatuta_Arazoak1` FOREIGN KEY (`idArazoak`) REFERENCES `arazoak` (`idArazoak`),
  CONSTRAINT `fk_Arazoak_has_Entregatuta_Entregatuta1` FOREIGN KEY (`idEntregatuta`) REFERENCES `entregatuta` (`idEntregatuta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gertatu`
--

LOCK TABLES `gertatu` WRITE;
/*!40000 ALTER TABLE `gertatu` DISABLE KEYS */;
/*!40000 ALTER TABLE `gertatu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `izan`
--

DROP TABLE IF EXISTS `izan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `izan` (
  `idPaketea` int NOT NULL,
  `idArazoak` int NOT NULL,
  `Arazo_Ordua` varchar(45) NOT NULL,
  PRIMARY KEY (`idPaketea`,`idArazoak`),
  KEY `fk_Paketea_has_Arazoak_Arazoak1_idx` (`idArazoak`),
  KEY `fk_Paketea_has_Arazoak_Paketea1_idx` (`idPaketea`),
  CONSTRAINT `fk_Paketea_has_Arazoak_Arazoak1` FOREIGN KEY (`idArazoak`) REFERENCES `arazoak` (`idArazoak`),
  CONSTRAINT `fk_Paketea_has_Arazoak_Paketea1` FOREIGN KEY (`idPaketea`) REFERENCES `paketea` (`idPaketea`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `izan`
--

LOCK TABLES `izan` WRITE;
/*!40000 ALTER TABLE `izan` DISABLE KEYS */;
/*!40000 ALTER TABLE `izan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paketea`
--

DROP TABLE IF EXISTS `paketea`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paketea` (
  `idPaketea` int NOT NULL AUTO_INCREMENT,
  `Bezero_zenbakia` varchar(9) NOT NULL,
  `Helbidea` varchar(100) NOT NULL,
  `Pakete_Tamaina` varchar(45) NOT NULL,
  `Mota` varchar(45) NOT NULL,
  `Erabiltzailea_idErabiltzailea` int NOT NULL,
  PRIMARY KEY (`idPaketea`),
  KEY `fk_Paketea_Erabiltzailea_idx` (`Erabiltzailea_idErabiltzailea`),
  CONSTRAINT `fk_Paketea_Erabiltzailea` FOREIGN KEY (`Erabiltzailea_idErabiltzailea`) REFERENCES `erabiltzailea` (`idErabiltzailea`) ON UPDATE RESTRICT
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paketea`
--

LOCK TABLES `paketea` WRITE;
/*!40000 ALTER TABLE `paketea` DISABLE KEYS */;
INSERT INTO `paketea` VALUES (1,'1234455','Ibarra','Ertaina','Entregatu Gabe',1),(2,'234234','Ibarra','Txikia','Entregatu Gabe',1),(5,'245354','Hernani','Ertaina','Entregatu Gabe',1);
/*!40000 ALTER TABLE `paketea` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-24 12:59:31

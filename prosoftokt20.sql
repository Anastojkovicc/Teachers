/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 10.4.8-MariaDB : Database - prosoftokt20
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`prosoftokt20` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `prosoftokt20`;

/*Table structure for table `korisnik` */

DROP TABLE IF EXISTS `korisnik`;

CREATE TABLE `korisnik` (
  `KorisnikID` int(11) NOT NULL,
  `Ime` varchar(255) DEFAULT NULL,
  `Prezime` varchar(255) DEFAULT NULL,
  `KorisnickoIme` varchar(255) DEFAULT NULL,
  `Lozinka` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`KorisnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `korisnik` */

insert  into `korisnik`(`KorisnikID`,`Ime`,`Prezime`,`KorisnickoIme`,`Lozinka`) values 
(1,'Ana','Anic','ana','ana'),
(2,'Ivan','Ivic','ivan','ivan');

/*Table structure for table `nastavnik` */

DROP TABLE IF EXISTS `nastavnik`;

CREATE TABLE `nastavnik` (
  `NastavnikID` int(11) NOT NULL,
  `Ime` varchar(255) DEFAULT NULL,
  `Prezime` varchar(255) DEFAULT NULL,
  `Zvanje` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`NastavnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `nastavnik` */

insert  into `nastavnik`(`NastavnikID`,`Ime`,`Prezime`,`Zvanje`) values 
(1,'Ivana','Ivanovic','Redovni profesor'),
(2,'Dragan','Draganic','Docent'),
(3,'Marija','Marjanovic','Asistent'),
(4,'Petar','Petkovic','Saradnik u nastavi'),
(5,'Marko','Markovic','Vanredni profesor'),
(6,'Mirko','Mirkovic','Asistent');

/*Table structure for table `predajepredmet` */

DROP TABLE IF EXISTS `predajepredmet`;

CREATE TABLE `predajepredmet` (
  `NastavnikID` int(11) NOT NULL,
  `PredmetID` int(11) NOT NULL,
  `Semestar` int(11) DEFAULT NULL,
  `StudijskiProgram` varchar(50) DEFAULT NULL,
  `DatumAngazovanja` date DEFAULT NULL,
  `KorisnikID` int(11) DEFAULT NULL,
  PRIMARY KEY (`NastavnikID`,`PredmetID`),
  KEY `PredmetID` (`PredmetID`),
  KEY `KorisnikID` (`KorisnikID`),
  CONSTRAINT `predajepredmet_ibfk_1` FOREIGN KEY (`NastavnikID`) REFERENCES `nastavnik` (`NastavnikID`),
  CONSTRAINT `predajepredmet_ibfk_2` FOREIGN KEY (`PredmetID`) REFERENCES `predmet` (`PredmetID`),
  CONSTRAINT `predajepredmet_ibfk_3` FOREIGN KEY (`KorisnikID`) REFERENCES `korisnik` (`KorisnikID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `predajepredmet` */

insert  into `predajepredmet`(`NastavnikID`,`PredmetID`,`Semestar`,`StudijskiProgram`,`DatumAngazovanja`,`KorisnikID`) values 
(1,1,5,'OM','2020-09-09',1),
(1,2,8,'ISIT','2020-09-11',2);

/*Table structure for table `predmet` */

DROP TABLE IF EXISTS `predmet`;

CREATE TABLE `predmet` (
  `PredmetID` int(11) NOT NULL,
  `Naziv` varchar(255) DEFAULT NULL,
  `ESPB` int(11) DEFAULT NULL,
  PRIMARY KEY (`PredmetID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `predmet` */

insert  into `predmet`(`PredmetID`,`Naziv`,`ESPB`) values 
(1,'Osnove programiranja',6),
(2,'Projektovanje softvera',6),
(3,'Programiranje 1',5),
(4,'Engleski jezik struke 1',6),
(5,'Matematika 1',6),
(6,'Matematika 2',6),
(7,'Teorija verovatnoce',6),
(8,'Statistika',6),
(9,'Operaciona istrazivanja 1',6),
(10,'Operaciona istrazivanja 2',6);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

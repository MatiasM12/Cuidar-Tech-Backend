-- CREATE USER 'vdgpps'@'%' IDENTIFIED BY 'vdgpps2019';

GRANT ALL PRIVILEGES ON *.* TO 'vdgpps'@'%';

FLUSH PRIVILEGES;


-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--	
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 06-04-2021 a las 19:23:39
-- Versión del servidor: 8.0.23
-- Versión de PHP: 7.4.3

SET FOREIGN_KEY_CHECKS=0;
SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `vdg`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `BotonAntipanico`
--

drop database vdg;

create database vdg;

use vdg;	

CREATE TABLE `Grupo` (
  `idGrupo` int AUTO_INCREMENT NOT NULL,
  `nombreGrupo` varchar(50) DEFAULT NULL,
  `turnoGrupo` varchar(50) DEFAULT NULL,
  PRIMARY KEY (idGrupo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


insert into Grupo (idGrupo, nombreGrupo, turnoGrupo) values (0, 'PERSONAS', 'MAÑANA');
insert into Grupo (idGrupo, nombreGrupo, turnoGrupo) values (1, 'Mañana 1', 'MAÑANA');
insert into Grupo (idGrupo, nombreGrupo, turnoGrupo) values (2, 'Tarde 1','TARDE');
insert into Grupo (idGrupo, nombreGrupo, turnoGrupo) values (3, 'Noche 1','NOCHE');



CREATE TABLE `BotonAntipanico` (
  `idBotonAntipanico` int NOT NULL,
  `latitud` decimal(9,6) DEFAULT NULL,
  `longitud` decimal(9,6) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  `idDamnificada` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `BotonAntipanico`
--

INSERT INTO `BotonAntipanico` (`idBotonAntipanico`, `latitud`, `longitud`, `fecha`, `idDamnificada`) VALUES
(1, '-34.586305', '-58.644667', '2019-11-07 02:48:44', 2),
(2, '-34.586305', '-58.644667', '2019-11-07 02:51:21', 2),
(3, '-34.627680', '-58.761318', '2019-11-07 03:24:18', 2),
(4, '-34.606088', '-58.725930', '2019-11-11 16:16:30', 2),
(5, '-34.606088', '-58.725930', '2019-11-11 16:17:36', 2),
(6, '-34.606088', '-58.725930', '2019-11-11 16:23:13', 2),
(7, '-34.606088', '-58.725930', '2019-11-11 16:24:12', 2),
(8, '-34.606088', '-58.725930', '2019-11-11 16:32:13', 2),
(9, '-34.606088', '-58.725930', '2019-11-11 16:33:11', 2),
(10, '-34.606088', '-58.725930', '2019-11-11 16:43:48', 2),
(11, '-34.521891', '-58.700381', '2019-11-11 21:16:53', 2),
(12, '-34.521853', '-58.700385', '2019-11-11 21:47:11', 2),
(13, NULL, NULL, '2019-11-25 21:46:54', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Contacto`
--

CREATE TABLE `Contacto` (
  `idContacto` int NOT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `relacion` varchar(30) DEFAULT NULL,
  `idDamnificada` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `Contacto`
-- tipo varchar(255)

INSERT INTO `Contacto` (`idContacto`, `apellido`, `nombre`, `email`, `telefono`, `relacion`, `idDamnificada`) VALUES
(20, 'Cruz', 'Gustavo', 'gustycruz85@gmail.com', '1122334422', 'Amigo/a', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Direccion`
--

CREATE TABLE `Direccion` (
  `idDireccion` int NOT NULL,
  `calle` varchar(50) DEFAULT NULL,
  `altura` varchar(50) DEFAULT NULL,
  `piso` varchar(50) DEFAULT NULL,
  `departamento` varchar(50) DEFAULT NULL,
  `idLocalidad` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Direccion`
--

INSERT INTO `Direccion` (`idDireccion`, `calle`, `altura`, `piso`, `departamento`, `idLocalidad`) VALUES
(1, 'Calle1', '1767', NULL, NULL, 2156),
(2, 'Calle', '4310', '7', 'C', 2156),
(3, 'Calle2', '7898', '7', NULL, 2156),
(4, 'Calle2', '9876', '', '', 2156),
(6, 'Calle1', '2031', '', '', 16431),
(9, '9 de Julio', '654', '1', 'z', 5001),
(11, 'Alem', '654', '6', 'c', 11732);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstadoNotificacion`
--

CREATE TABLE `EstadoNotificacion` (
  `idEstadoNotificacion` int NOT NULL,
  `estadoNotificacion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `EstadoNotificacion`
--

INSERT INTO `EstadoNotificacion` (`idEstadoNotificacion`, `estadoNotificacion`) VALUES
(1, 'NoVista'),
(2, 'Vista'),
(3, 'Archivada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `EstadoPruebaDeVida`
--

CREATE TABLE `EstadoPruebaDeVida` (
  `idEstadoPruebaDeVida` int NOT NULL,
  `estadoPruebaDeVida` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `EstadoPruebaDeVida`
--

INSERT INTO `EstadoPruebaDeVida` (`idEstadoPruebaDeVida`, `estadoPruebaDeVida`) VALUES
(1, 'Pendiente'),
(2, 'Aceptada'),
(3, 'Rechazada');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FotoIdentificacion`
--

CREATE TABLE `FotoIdentificacion` (
  `idFoto` int NOT NULL,
  `foto` mediumblob,
  `idPersona` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `FotoIdentificacion`
--

-- Script de fotoIdentifiacion1,2,3

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `FotoPruebaDeVida`
--

CREATE TABLE `FotoPruebaDeVida` (
  `idFoto` int NOT NULL,
  `foto` mediumblob,
  `idPruebaDeVida` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `FotoPruebaDeVida`
--

-- Script de fotoPruebaDeVida

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Incidencia`
--

CREATE TABLE `Incidencia` (
  `idIncidencia` int NOT NULL,
  `fecha` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` varchar(500) DEFAULT NULL,
  `topico` varchar(50) DEFAULT NULL,
  `idRestriccion` int DEFAULT NULL,
  `peligrosidad` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Incidencia`
--

-- Script incidencia.sql

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Infraccion`
--

CREATE TABLE `Infraccion` (
  `idInfraccion` int NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `distancia` int DEFAULT NULL,
  `estadoInfraccion` varchar(50) DEFAULT NULL,
  `idRestriccion` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Infraccion`
--

INSERT INTO `Infraccion` (`idInfraccion`, `fecha`, `distancia`, `estadoInfraccion`, `idRestriccion`) VALUES
(10, '2019-10-01 00:44:51', 68, 'Abierta', 1),
(11, '2019-10-25 05:02:54', 12, 'Abierta', 1),
(12, '2019-10-26 01:56:22', 12, 'Abierta', 1),
(13, '2019-10-27 02:21:56', 12, 'Abierta', 1),
(14, '2019-10-28 22:19:35', 12, 'Abierta', 1),
(15, '2019-10-28 23:20:01', 12, 'Abierta', 1),
(16, '2019-10-31 21:05:44', 12, 'Abierta', 1),
(17, '2019-10-31 23:52:06', 12, 'Abierta', 1),
(18, '2019-11-01 00:48:15', 12, 'Abierta', 1),
(19, '2019-11-01 02:07:57', 12, 'Abierta', 1),
(20, '2019-11-01 07:28:43', 12, 'Abierta', 1),
(21, '2019-11-01 15:09:03', 12, 'Abierta', 1),
(22, '2019-11-01 16:26:58', 12, 'Abierta', 1),
(23, '2019-11-01 18:03:11', 12, 'Abierta', 1),
(24, '2019-11-02 05:58:57', 12, 'Abierta', 1),
(25, '2019-11-02 20:30:46', 12, 'Abierta', 1),
(26, '2019-11-02 21:09:50', 12, 'Abierta', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Localidad`
--

CREATE TABLE `Localidad` (
  `idLocalidad` int NOT NULL,
  `nombre` varchar(60) NOT NULL,
  `codigoPostal` int NOT NULL,
  `idProvincia` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Localidad`
--

-- Script localidad.sql

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `idUsuario` int NOT NULL,
  `email` varchar(64) NOT NULL,
  `contrasena` varchar(64) NOT NULL,
  `rolDeUsuario` varchar(25) DEFAULT NULL,
  `estadoUsuario` varchar(25) DEFAULT NULL,
  `idGrupo` int,
  FOREIGN KEY (idGrupo) REFERENCES Grupo(idGrupo)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`idUsuario`, `email`, `contrasena`, `rolDeUsuario`,`estadoUsuario`, `idGrupo`) VALUES
(1, "admin@admin.com", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "SUPERVISOR",'AUSENTE',1),
(2, 'agresor1@agresor1.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'VICTIMARIO','AUSENTE',0),
(4, 'victima1@victima1.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'DAMNIFICADA','AUSENTE',0),
(5, 'gfgrillo3@gmail.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ADMINISTRATIVO','AUSENTE', 2),
(7, 'agresor2@agresor2.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'VICTIMARIO','AUSENTE',0),
(18, 'gustycruz85@gmail.com', '983adc986531868a9ef48446fd07d5751982f6336ee073b10512d6568ad149e1', 'ADMINISTRATIVO','AUSENTE',3),
(20, 'usuario@prueba.com.ar', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'ADMINISTRATIVO','AUSENTE',1),
(21, 'agresor3@agresor.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'VICTIMARIO','AUSENTE',0),
(23, 'damnificada2@damnificada.com', 'a665a45920422f9d417e4867efdc4fb8a04a1f3fff1fa07e998e86f7f7a27ae3', 'DAMNIFICADA','AUSENTE',0),
(24, 'supervisor@general.com', '8698df0ec492e5026b61ae25e429f82dea81eb962c5fbfa8ed3fd2ac72a968b2', 'SUPERVISOR_GENERAL','NO_CORRESPONDE',1)
;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Notificacion`
--

CREATE TABLE `Notificacion` (
  `idNotificacion` int NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `asunto` varchar(150) DEFAULT NULL,
  `descripcion` varchar(400) DEFAULT NULL,
  `estadoNotificacion` varchar(50) DEFAULT NULL,
  `idUsuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Notificacion`
--

-- Script notificacion.sql

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Persona`
--

CREATE TABLE `Persona` (
  `idPersona` int NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellido` varchar(50) DEFAULT NULL,
  `DNI` varchar(20) DEFAULT NULL,
  `telefono` varchar(50) DEFAULT NULL,
  `fechaNacimiento` date DEFAULT NULL,
  `idDireccion` int DEFAULT NULL,
  `idUsuario` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Persona`
--

INSERT INTO `Persona` (`idPersona`, `nombre`, `apellido`, `DNI`, `telefono`, `fechaNacimiento`, `idDireccion`, `idUsuario`) VALUES
(1, 'Agresor1', 'Agresor1', '10101010', '10', '1986-03-05', 2, 2),
(2, 'Victima1', 'Victima1', '987654', '654987', '2010-09-10', 4, 4),
(4, 'Agresor2', 'Agresor2', '23232323', '11232323', '1974-04-10', 6, 7),
(7, 'Agresor3', 'Agresor3', '321321', '1150689558', '1970-10-19', 9, 21),
(9, 'Damnificada2', 'Damnificada2', '123321', '1154877895', '1983-07-16', 11, 23);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Provincia`
--

CREATE TABLE `Provincia` (
  idProvincia INT NOT NULL,
  nombre VARCHAR(50) NOT NULL,
  PRIMARY KEY (idProvincia)
)ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Provincia`
--

INSERT INTO `Provincia` VALUES ('1', 'BUENOS AIRES');
INSERT INTO `Provincia` VALUES ('2', 'CAPITAL FEDERAL');
INSERT INTO `Provincia` VALUES ('3', 'CATAMARCA');
INSERT INTO `Provincia` VALUES ('4', 'CHACO');
INSERT INTO `Provincia` VALUES ('5', 'CHUBUT');
INSERT INTO `Provincia` VALUES ('6', 'CORDOBA');
INSERT INTO `Provincia` VALUES ('7', 'CORRIENTES');
INSERT INTO `Provincia` VALUES ('8', 'ENTRE RIOS');
INSERT INTO `Provincia` VALUES ('9', 'FORMOSA');
INSERT INTO `Provincia` VALUES ('10', 'JUJUY');
INSERT INTO `Provincia` VALUES ('11', 'LA PAMPA');
INSERT INTO `Provincia` VALUES ('12', 'LA RIOJA');
INSERT INTO `Provincia` VALUES ('13', 'MENDOZA');
INSERT INTO `Provincia` VALUES ('14', 'MISIONES');
INSERT INTO `Provincia` VALUES ('15', 'NEUQUEN');
INSERT INTO `Provincia` VALUES ('16', 'RIO NEGRO');
INSERT INTO `Provincia` VALUES ('17', 'SALTA');
INSERT INTO `Provincia` VALUES ('18', 'SAN JUAN');
INSERT INTO `Provincia` VALUES ('19', 'SAN LUIS');
INSERT INTO `Provincia` VALUES ('20', 'SANTA CRUZ');
INSERT INTO `Provincia` VALUES ('21', 'SANTA FE');
INSERT INTO `Provincia` VALUES ('22', 'SANTIAGO DEL ESTERO');
INSERT INTO `Provincia` VALUES ('23', 'TIERRA DEL FUEGO');
INSERT INTO `Provincia` VALUES ('24', 'TUCUMAN');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ciudad`
--

CREATE TABLE `ciudad` (
  idLocalidad int(4) NOT NULL,
  nombre varchar(60) NOT NULL,
  codigoPostal int NOT NULL,
  idProvincia int NOT NULL,
  PRIMARY KEY (idLocalidad),
  FOREIGN KEY (idProvincia) references Provincia(idProvincia)
); 

--
-- Volcado de datos para la tabla `Ciudad`
--

-- Script ciudad.sql

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `PruebaDeVida`
--

CREATE TABLE `PruebaDeVida` (
  `idPruebaDeVida` int NOT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  `descripcion` varchar(400) DEFAULT NULL,
  `estado` varchar(25) DEFAULT NULL,
  `idRestriccion` int DEFAULT NULL,
  `idPersonaRestriccion` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `PruebaDeVida`
--

INSERT INTO `PruebaDeVida` (`idPruebaDeVida`, `fecha`, `descripcion`, `estado`, `idRestriccion`, `idPersonaRestriccion`) VALUES
(1, '2019-10-21 17:12:47', 'Levantar dedo pulgar izquierdo y tocarse la oreja derecha con la mano derecha con una remera naranja', 'Aceptada', 1, 1),
(2, '2019-10-21 22:48:53', 'levantar dedo indice', 'Aceptada', 1, 1),
(3, '2019-10-24 23:01:50', 'Levantar dedo pulgar izquierdo', 'Rechazada', 1, 1),
(4, '2019-11-11 21:53:00', 'Guiñar ojo izquierdoyy', 'Aceptada', 1, 1),
(5, '2019-10-27 15:10:29', 'Tocarse oreja derecha', 'Aceptada', 1, 1),
(6, '2019-11-11 21:54:03', 'saludarkk', 'Rechazada', 1, 1),
(7, '2019-10-30 14:46:59', 'Cerrar ojo izquierdo', 'Rechazada', 1, 1),
(8, '2019-11-25 17:09:25', '', 'Aceptada', 1, 1),
(9, '2019-11-25 17:16:55', 'levantar dos dedos', 'Aceptada', 1, 1),
(10, '2019-11-25 17:25:46', 'levantar cuatro dedos', 'Aceptada', 1, 1),
(11, '2019-11-25 21:41:04', 'Guiñar ojo derecho', 'Rechazada', 1, 1),
(12, '2019-11-26 23:46:46', 'levantar 3 dedos', 'Rechazada', 1, 1),
(13, '2021-01-16 20:09:56', 'Tocarse el pelo con el dedo índice', 'Rechazada', 3, 4),
(14, '2021-01-16 20:27:36', 'Hacer OK con la mano', 'Aceptada', 3, 4),
(15, '2021-01-16 20:29:59', 'Tocarse la oreja con el pulgar', 'Pendiente', 3, 4),
(16, '2021-01-16 20:31:03', 'Hacer OK con la mano', 'Pendiente', 6, 7),
(17, '2021-02-01 18:10:12', 'Tocarse la cabeza con ambas manos', 'Pendiente', 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RestriccionPerimetral`
--

CREATE TABLE `RestriccionPerimetral` (
  `idRestriccion` int NOT NULL,
  `idUsuario` int DEFAULT NULL,
  `idDamnificada` int DEFAULT NULL,
  `idVictimario` int DEFAULT NULL,
  `distancia` int DEFAULT NULL,
  `fechaSentencia` date DEFAULT NULL,
   `idGrupo` int
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `RestriccionPerimetral`
--

INSERT INTO `RestriccionPerimetral` (`idRestriccion`, `idUsuario`, `idDamnificada`, `idVictimario`, `distancia`, `fechaSentencia`,`idGrupo`) VALUES
(1, 20, 2, 1, 500, '2021-01-07',1),
(3, 5, 2, 4, 300, '2019-11-19',2),
(6, 18, 9, 7, 500, '2011-01-14',3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `RolDeUsuario`
--

CREATE TABLE `RolDeUsuario` (
  `idRol` int NOT NULL,
  `nombre` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `RolDeUsuario`
--

INSERT INTO `RolDeUsuario` (`idRol`, `nombre`) VALUES
(1, 'ADMINISTRATIVO'),
(2, 'SUPERVISOR'),
(3, 'AGRESOR'),
(4, 'DAMNIFICADA'),
(5, 'SUPERVISOR_GENERAL');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `TipoIncidencia`
--

CREATE TABLE `TipoIncidencia` (
  `idTipoIncidencia` int NOT NULL,
  `tipoIncidencia` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `TipoIncidencia`
--

INSERT INTO `TipoIncidencia` (`idTipoIncidencia`, `tipoIncidencia`) VALUES
(1, 'VictimarioIlocalizable'),
(2, 'DamnificadaIlocalizable'),
(3, 'PruebaDeVidaFallida'),
(4, 'InfraccionDeRestriccion'),
(5, 'FueraDeRutina');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Ubicacion`
--

CREATE TABLE `Ubicacion` (
  `idUbicacion` int NOT NULL,
  `latitud` decimal(9,6) DEFAULT NULL,
  `longitud` decimal(9,6) DEFAULT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `idPersona` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `Ubicacion`
--

INSERT INTO `Ubicacion` (`idUbicacion`, `latitud`, `longitud`, `fecha`, `idPersona`) VALUES
(1, '-34.592694', '-58.745829', '2019-11-27 16:04:36', 1),
(2, '-34.592605', '-58.747725', '2020-05-28 03:14:09', 2),
(4, '-34.585733', '-58.650988', '2019-11-24 14:58:57', 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `UbicacionRutina`
--

CREATE TABLE `UbicacionRutina` (
  `idUbicacionRutina` int NOT NULL,
  `latitud` decimal(9,6) DEFAULT NULL,
  `longitud` decimal(9,6) DEFAULT NULL,
  `fecha` timestamp NULL DEFAULT NULL,
  `idPersona` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `UbicacionRutina`
--

INSERT INTO `UbicacionRutina` (`idUbicacionRutina`, `latitud`, `longitud`, `fecha`, `idPersona`) VALUES
(6, '-34.585733', '-58.650988', '2019-10-09 21:00:00', 1),
(7, '-34.586305', '-58.644667', '2019-10-02 21:00:00', 1),
(8, '-34.586305', '-58.644667', '2019-10-03 22:00:00', 1),
(9, '-34.586305', '-58.644667', '2019-10-10 23:00:00', 1),
(10, '-34.586305', '-58.644667', '2019-10-11 01:00:00', 1),
(11, '-34.586305', '-58.644667', '2019-10-13 09:00:00', 1),
(12, '-34.586305', '-58.644667', '2019-10-20 09:00:00', 1),
(15, '-34.585733', '-58.650988', '2019-10-21 14:30:00', 1),
(16, '-34.606088', '-58.725930', '2019-11-25 15:00:00', 1);

-- --------------------------------------------------------


--
-- Estructura Stand-in para la vista `VistaRestriccionDTO`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `VistaRestriccionDTO` (
`idRestriccion` int(11)
,`distancia` int(11)
,`idAdministrativo` int(11)
,`email` varchar(64)
,`idVictimario` int(11)
,`apellidoVictimario` varchar(50)
,`nombreVictimario` varchar(50)
,`dniVictimario` varchar(20)
,`idDamnificada` int(11)
,`apellidoDamnificada` varchar(50)
,`nombreDamnificada` varchar(50)
,`dniDamnificada` varchar(20)
,`idGrupo` int(11)
,`nombreGrupo` varchar(50)
,`fechaSentencia` date
);

-- --------------------------------------------------------

--
-- Estructura Stand-in para la vista `VistaUsuarioPersona`
-- (Véase abajo para la vista actual)
--
CREATE TABLE `VistaUsuarioPersona` (
`idUsuario` int(11)
,`email` varchar(64)
,`rolDeUsuario` varchar(25)
,`idPersona` int(11)
);

-- --------------------------------------------------------

--
-- Estructura para la vista `VistaRestriccionDTO`
--
DROP TABLE IF EXISTS `VistaRestriccionDTO`;

CREATE ALGORITHM=UNDEFINED DEFINER=`vdgpps`@`%` SQL SECURITY DEFINER VIEW `VistaRestriccionDTO`  AS SELECT `r`.`idRestriccion` AS `idRestriccion`, `r`.`distancia` AS `distancia`, `r`.`idUsuario` AS `idAdministrativo`,
`r`.`idGrupo` AS `idGrupo`, `g`.`nombreGrupo` AS `nombreGrupo`,`r`.`fechaSentencia` AS `fechaSentencia`, `u`.`email` AS `email`, `r`.`idVictimario` AS `idVictimario`, `pV`.`apellido` AS `apellidoVictimario`, `pV`.`nombre` AS `nombreVictimario`,
`pV`.`DNI` AS `dniVictimario`, `r`.`idDamnificada` AS `idDamnificada`, `pD`.`apellido` AS `apellidoDamnificada`, `pD`.`nombre` AS `nombreDamnificada`, `pD`.`DNI` AS `dniDamnificada` 
FROM ((((`RestriccionPerimetral` `r` 
join `Persona` `pV` on((`r`.`idVictimario` = `pV`.`idPersona`))) 
join `Persona` `pD` on((`r`.`idDamnificada` = `pD`.`idPersona`))) 
join `Usuario` `u` on((`r`.`idUsuario` = `u`.`idUsuario`)))
join `Grupo` `g` on((`r`.`idGrupo` = `g`.`idGrupo`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `VistaUsuarioPersona`
--
DROP TABLE IF EXISTS `VistaUsuarioPersona`;

CREATE ALGORITHM=UNDEFINED DEFINER=`vdgpps`@`%` SQL SECURITY DEFINER VIEW `VistaUsuarioPersona`  AS SELECT `u`.`idUsuario` AS `idUsuario`, `u`.`email` AS `email`, `u`.`rolDeUsuario` AS `rolDeUsuario`, `p`.`idPersona` AS `idPersona` FROM (`Usuario` `u` join `Persona` `p` on((`u`.`idUsuario` = `p`.`idUsuario`))) ;

-- --------------------------------------------------------

--
-- Estructura para la vista `comisaria`
--

CREATE TABLE `comisaria` (
  `idComisaria` int NOT NULL AUTO_INCREMENT,
  `ciudad` varchar(255) DEFAULT NULL,
  `comisariaACargo` varchar(255) DEFAULT NULL,
  `coordenadaX` varchar(255) DEFAULT NULL,
  `coordenadaY` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `partido` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `tipo` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idComisaria`)
);

-- --------------------------------------------------------

--
-- Estructura para parametro
--

CREATE TABLE `parametro` (
  `idParametro` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `valor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idParametro`)

) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `parametro` (`idParametro`,`nombre`,`valor`) VALUES(1,'Tiempo de respuesta','00:05:00');



-- --------------------------------------------------------

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `BotonAntipanico`
--
ALTER TABLE `BotonAntipanico`
  ADD PRIMARY KEY (`idBotonAntipanico`),
  ADD KEY `idDamnificada` (`idDamnificada`);

--
-- Indices de la tabla `Contacto`
--
ALTER TABLE `Contacto`
  ADD PRIMARY KEY (`idContacto`),
  ADD KEY `idDamnificada` (`idDamnificada`);

--
-- Indices de la tabla `Direccion`
--
ALTER TABLE `Direccion`
  ADD PRIMARY KEY (`idDireccion`),
  ADD KEY `idLocalidad` (`idLocalidad`);

--
-- Indices de la tabla `EstadoNotificacion`
--
ALTER TABLE `EstadoNotificacion`
  ADD PRIMARY KEY (`idEstadoNotificacion`);

--
-- Indices de la tabla `EstadoPruebaDeVida`
--
ALTER TABLE `EstadoPruebaDeVida`
  ADD PRIMARY KEY (`idEstadoPruebaDeVida`);

--
-- Indices de la tabla `FotoIdentificacion`
--
ALTER TABLE `FotoIdentificacion`
  ADD PRIMARY KEY (`idFoto`),
  ADD KEY `idPersona` (`idPersona`);

--
-- Indices de la tabla `FotoPruebaDeVida`
--
ALTER TABLE `FotoPruebaDeVida`
  ADD PRIMARY KEY (`idFoto`),
  ADD KEY `idPruebaDeVida` (`idPruebaDeVida`);

--
-- Indices de la tabla `Incidencia`
--
ALTER TABLE `Incidencia`
  ADD PRIMARY KEY (`idIncidencia`),
  ADD KEY `idRestriccion` (`idRestriccion`);

--
-- Indices de la tabla `Infraccion`
--
ALTER TABLE `Infraccion`
  ADD PRIMARY KEY (`idInfraccion`),
  ADD KEY `idRestriccion` (`idRestriccion`);

--
-- Indices de la tabla `Localidad`
--
ALTER TABLE `Localidad`
  ADD PRIMARY KEY (`idLocalidad`),
  ADD KEY `idProvincia` (`idProvincia`);

--
-- Indices de la tabla `Notificacion`
--
ALTER TABLE `Notificacion`
  ADD PRIMARY KEY (`idNotificacion`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `Persona`
--
ALTER TABLE `Persona`
  ADD PRIMARY KEY (`idPersona`),
  ADD KEY `idDireccion` (`idDireccion`),
  ADD KEY `idUsuario` (`idUsuario`);

--
-- Indices de la tabla `Provincia`
--
-- ER TABLE `Provincia`
 --  ADD PRIMARY KEY (`idProvincia`);

--
-- Indices de la tabla `PruebaDeVida`
--
ALTER TABLE `PruebaDeVida`
  ADD PRIMARY KEY (`idPruebaDeVida`),
  ADD KEY `idRestriccion` (`idRestriccion`),
  ADD KEY `idPersonaRestriccion` (`idPersonaRestriccion`);

--
-- Indices de la tabla `RestriccionPerimetral`
--
ALTER TABLE `RestriccionPerimetral`
  ADD PRIMARY KEY (`idRestriccion`),
  ADD KEY `idUsuario` (`idUsuario`),
  ADD KEY `idDamnificada` (`idDamnificada`),
  ADD KEY `idVictimario` (`idVictimario`),
  ADD KEY `idGrupo` (`idGrupo`);

--
-- Indices de la tabla `RolDeUsuario`
--
ALTER TABLE `RolDeUsuario`
  ADD PRIMARY KEY (`idRol`);

--
-- Indices de la tabla `TipoIncidencia`
--
ALTER TABLE `TipoIncidencia`
  ADD PRIMARY KEY (`idTipoIncidencia`);

--
-- Indices de la tabla `Ubicacion`
--
ALTER TABLE `Ubicacion`
  ADD PRIMARY KEY (`idUbicacion`),
  ADD KEY `idPersona` (`idPersona`);

--
-- Indices de la tabla `UbicacionRutina`
--
ALTER TABLE `UbicacionRutina`
  ADD PRIMARY KEY (`idUbicacionRutina`),
  ADD KEY `idPersona` (`idPersona`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `BotonAntipanico`
--
ALTER TABLE `BotonAntipanico`
  MODIFY `idBotonAntipanico` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `Contacto`
--
ALTER TABLE `Contacto`
  MODIFY `idContacto` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT de la tabla `Direccion`
--
ALTER TABLE `Direccion`
  MODIFY `idDireccion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `EstadoNotificacion`
--
ALTER TABLE `EstadoNotificacion`
  MODIFY `idEstadoNotificacion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `EstadoPruebaDeVida`
--
ALTER TABLE `EstadoPruebaDeVida`
  MODIFY `idEstadoPruebaDeVida` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `FotoIdentificacion`
--
ALTER TABLE `FotoIdentificacion`
  MODIFY `idFoto` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `FotoPruebaDeVida`
--
ALTER TABLE `FotoPruebaDeVida`
  MODIFY `idFoto` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `Incidencia`
--
ALTER TABLE `Incidencia`
  MODIFY `idIncidencia` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1284;

--
-- AUTO_INCREMENT de la tabla `Infraccion`
--
ALTER TABLE `Infraccion`
  MODIFY `idInfraccion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=27;

--
-- AUTO_INCREMENT de la tabla `Notificacion`
--
ALTER TABLE `Notificacion`
  MODIFY `idNotificacion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=1030;

--
-- AUTO_INCREMENT de la tabla `Persona`
--
ALTER TABLE `Persona`
  MODIFY `idPersona` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `PruebaDeVida`
--
ALTER TABLE `PruebaDeVida`
  MODIFY `idPruebaDeVida` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT de la tabla `RestriccionPerimetral`
--
ALTER TABLE `RestriccionPerimetral`
  MODIFY `idRestriccion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `RolDeUsuario`
--
ALTER TABLE `RolDeUsuario`
  MODIFY `idRol` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `TipoIncidencia`
--
ALTER TABLE `TipoIncidencia`
  MODIFY `idTipoIncidencia` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `Ubicacion`
--
ALTER TABLE `Ubicacion`
  MODIFY `idUbicacion` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `UbicacionRutina`
--
ALTER TABLE `UbicacionRutina`
  MODIFY `idUbicacionRutina` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  MODIFY `idUsuario` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=24;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `BotonAntipanico`
--
ALTER TABLE `BotonAntipanico`
  ADD CONSTRAINT `BotonAntipanico_ibfk_1` FOREIGN KEY (`idDamnificada`) REFERENCES `Persona` (`idPersona`);

--
-- Filtros para la tabla `Contacto`
--
ALTER TABLE `Contacto`
  ADD CONSTRAINT `Contacto_ibfk_1` FOREIGN KEY (`idDamnificada`) REFERENCES `Persona` (`idPersona`);

--
-- Filtros para la tabla `Direccion`
--
ALTER TABLE `Direccion`
  ADD CONSTRAINT `direccion_ibfk_1` FOREIGN KEY (`idLocalidad`) REFERENCES `Localidad` (`idLocalidad`);

--
-- Filtros para la tabla `FotoIdentificacion`
--
ALTER TABLE `FotoIdentificacion`
  ADD CONSTRAINT `fotoidentificacion_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`);

--
-- Filtros para la tabla `FotoPruebaDeVida`
--
ALTER TABLE `FotoPruebaDeVida`
  ADD CONSTRAINT `FotoPruebaDeVida_ibfk_1` FOREIGN KEY (`idPruebaDeVida`) REFERENCES `PruebaDeVida` (`idPruebaDeVida`);

--
-- Filtros para la tabla `Incidencia`
--
ALTER TABLE `Incidencia`
  ADD CONSTRAINT `incidencia_ibfk_1` FOREIGN KEY (`idRestriccion`) REFERENCES `RestriccionPerimetral` (`idRestriccion`);

--
-- Filtros para la tabla `Infraccion`
--
ALTER TABLE `Infraccion`
  ADD CONSTRAINT `infraccion_ibfk_1` FOREIGN KEY (`idRestriccion`) REFERENCES `RestriccionPerimetral` (`idRestriccion`);

--
-- Filtros para la tabla `Localidad`
--
ALTER TABLE `Localidad`
  ADD CONSTRAINT `localidad_ibfk_1` FOREIGN KEY (`idProvincia`) REFERENCES `Provincia` (`idProvincia`);

--
-- Filtros para la tabla `Notificacion`
--
ALTER TABLE `Notificacion`
  ADD CONSTRAINT `notificacion_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`);

--
-- Filtros para la tabla `Persona`
--
ALTER TABLE `Persona`
  ADD CONSTRAINT `persona_ibfk_1` FOREIGN KEY (`idDireccion`) REFERENCES `Direccion` (`idDireccion`),
  ADD CONSTRAINT `persona_ibfk_2` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`);

--
-- Filtros para la tabla `PruebaDeVida`
--
ALTER TABLE `PruebaDeVida`
  ADD CONSTRAINT `PruebaDeVida_ibfk_1` FOREIGN KEY (`idRestriccion`) REFERENCES `RestriccionPerimetral` (`idRestriccion`),
  ADD CONSTRAINT `PruebaDeVida_ibfk_2` FOREIGN KEY (`idPersonaRestriccion`) REFERENCES `Persona` (`idPersona`);

--
-- Filtros para la tabla `RestriccionPerimetral`
--
ALTER TABLE `RestriccionPerimetral`
  ADD CONSTRAINT `restriccionperimetral_ibfk_1` FOREIGN KEY (`idUsuario`) REFERENCES `Usuario` (`idUsuario`),
  ADD CONSTRAINT `restriccionperimetral_ibfk_2` FOREIGN KEY (`idDamnificada`) REFERENCES `Persona` (`idPersona`),
  ADD CONSTRAINT `restriccionperimetral_ibfk_3` FOREIGN KEY (`idVictimario`) REFERENCES `Persona` (`idPersona`),
  ADD CONSTRAINT `restriccionperimetral_ibfk_4` FOREIGN KEY (`idGrupo`) REFERENCES `Grupo` (`idGrupo`);

--
-- Filtros para la tabla `Ubicacion`
--
ALTER TABLE `Ubicacion`
  ADD CONSTRAINT `ubicacion_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`);

--
-- Filtros para la tabla `UbicacionRutina`
--
ALTER TABLE `UbicacionRutina`
  ADD CONSTRAINT `UbicacionRutina_ibfk_1` FOREIGN KEY (`idPersona`) REFERENCES `Persona` (`idPersona`);
SET FOREIGN_KEY_CHECKS=1;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

CREATE DATABASE IF NOT EXISTS vdg;

USE vdg;

DROP TABLE IF EXISTS BotonAntipanico;
DROP VIEW IF EXISTS VistaUsuarioPersona;
DROP TABLE IF EXISTS Contacto;
DROP VIEW IF EXISTS VistaRestriccionDTO;
DROP TABLE IF EXISTS FotoPruebaDeVida;
DROP TABLE IF EXISTS PruebaDeVida;
DROP TABLE IF EXISTS EstadoPruebaDeVida;
DROP TABLE IF EXISTS UbicacionRutina;
DROP TABLE IF EXISTS Notificacion;
DROP TABLE IF EXISTS EstadoNotificacion;
DROP TABLE IF EXISTS Infraccion;
DROP TABLE IF EXISTS Ubicacion;
DROP TABLE IF EXISTS FotoIdentificacion;
DROP TABLE IF EXISTS Incidencia;
DROP TABLE IF EXISTS RestriccionPerimetral;
DROP TABLE IF EXISTS Persona;
DROP TABLE IF EXISTS Direccion;
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS RolDeUsuario;
DROP TABLE IF EXISTS TipoIncidencia;


CREATE TABLE RolDeUsuario (
	idRol INT AUTO_INCREMENT,
	nombre VARCHAR(50),
	PRIMARY KEY (idRol)
);


CREATE TABLE Usuario (
	idUsuario INT AUTO_INCREMENT,
    email VARCHAR(64) NOT NULL,
	contrasena VARCHAR (64) NOT NULL,
	rolDeUsuario VARCHAR(25),
	PRIMARY KEY (idUsuario)
);

CREATE TABLE Direccion (
	idDireccion INT AUTO_INCREMENT,
	calle VARCHAR (50),
	altura VARCHAR (50),
	piso VARCHAR (50),
	departamento VARCHAR (50),
	idLocalidad INT,
	PRIMARY KEY (idDireccion),
	FOREIGN KEY (idLocalidad) REFERENCES Localidad(idLocalidad)
);


CREATE TABLE Persona (
	idPersona INT AUTO_INCREMENT,
	nombre VARCHAR(50),
    apellido VARCHAR (50),
    DNI VARCHAR (20),
    telefono VARCHAR (50),
    fechaNacimiento DATE,
    idDireccion INT,
    idUsuario INT,
    PRIMARY KEY (idPersona),
    FOREIGN KEY (idDireccion) REFERENCES Direccion(idDireccion),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);


CREATE TABLE FotoIdentificacion (
	idFoto INT AUTO_INCREMENT,
	foto MEDIUMBLOB,
	idPersona INT,
	PRIMARY KEY (idFoto),
	FOREIGN KEY (idPersona) REFERENCES Persona(idPersona)
);


CREATE TABLE RestriccionPerimetral (
	idRestriccion INT AUTO_INCREMENT,
	idUsuario INT,
	idDamnificada INT,
	idVictimario INT,
	distancia INT,
    fechaSentencia DATE,
	PRIMARY KEY (idRestriccion),
	FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario),
	FOREIGN KEY (idDamnificada) REFERENCES Persona(idPersona),
	FOREIGN KEY (idVictimario) REFERENCES Persona(idPersona)
);

CREATE TABLE Incidencia(
	idIncidencia INT AUTO_INCREMENT,
	fecha Timestamp,
	descripcion VARCHAR(500),
	topico VARCHAR (50),
    idRestriccion int,
	PRIMARY KEY (idIncidencia),
	FOREIGN KEY (idRestriccion) REFERENCES RestriccionPerimetral(idRestriccion)
);

CREATE TABLE TipoIncidencia (
	idTipoIncidencia INT AUTO_INCREMENT,
	tipoIncidencia VARCHAR(50),
	PRIMARY KEY (idTipoIncidencia)
);

CREATE TABLE Ubicacion (
	idUbicacion INT AUTO_INCREMENT,
    latitud DECIMAL(9,6),
    longitud DECIMAL(9,6),
    fecha Timestamp,
    idPersona INT,
    PRIMARY KEY (idUbicacion),
	FOREIGN KEY (idPersona) REFERENCES Persona(idPersona)
);

CREATE TABLE Infraccion (
	idInfraccion INT AUTO_INCREMENT,
    fecha Timestamp,
    distancia INT,
	estadoInfraccion VARCHAR (50),
    idRestriccion INT,
    PRIMARY KEY (idInfraccion),
	FOREIGN KEY (idRestriccion) REFERENCES RestriccionPerimetral(idRestriccion)
);


CREATE TABLE EstadoNotificacion( 
	idEstadoNotificacion INT AUTO_INCREMENT,
	estadoNotificacion VARCHAR(50),
	PRIMARY KEY (idEstadoNotificacion)
	);

CREATE TABLE Notificacion (
	idNotificacion INT AUTO_INCREMENT,
    fecha Timestamp,
    asunto VARCHAR(150),
    descripcion VARCHAR(400),
    estadoNotificacion VARCHAR(50),
    idUsuario INT,
    PRIMARY KEY (idNotificacion),
    FOREIGN KEY (idUsuario) REFERENCES Usuario(idUsuario)
);

CREATE TABLE UbicacionRutina (
	idUbicacionRutina INT AUTO_INCREMENT,
    latitud DECIMAL(9,6),
    longitud DECIMAL(9,6),
    fecha Timestamp,
    idPersona INT,
    PRIMARY KEY (idUbicacionRutina),
	FOREIGN KEY (idPersona) REFERENCES Persona(idPersona)
);

CREATE TABLE EstadoPruebaDeVida (
	idEstadoPruebaDeVida INT AUTO_INCREMENT,
	estadoPruebaDeVida VARCHAR(25),
	PRIMARY KEY (idEstadoPruebaDeVida)
);

CREATE TABLE PruebaDeVida (
	idPruebaDeVida INT AUTO_INCREMENT,
    fecha Timestamp,
    descripcion VARCHAR(400),
    estado VARCHAR(25),
    idRestriccion INT,
    idPersonaRestriccion INT,
    PRIMARY KEY (idPruebaDeVida),
    FOREIGN KEY (idRestriccion) REFERENCES RestriccionPerimetral(idRestriccion),
    FOREIGN KEY (idPersonaRestriccion) REFERENCES Persona(idPersona)
);


CREATE TABLE FotoPruebaDeVida (
	idFoto INT AUTO_INCREMENT,
	foto MEDIUMBLOB,
	idPruebaDeVida INT,
	PRIMARY KEY (idFoto),
	FOREIGN KEY (idPruebaDeVida) REFERENCES PruebaDeVida(idPruebaDeVida)
);

CREATE TABLE Contacto (
	idContacto INT AUTO_INCREMENT,
    apellido VARCHAR(50),
    nombre VARCHAR(50),
    email VARCHAR(50),
    telefono VARCHAR(30),
    relacion VARCHAR(30),
    idDamnificada INT,
    PRIMARY KEY (idContacto),
    FOREIGN KEY (idDamnificada) REFERENCES Persona(idPersona)
);


CREATE TABLE BotonAntipanico (
	idBotonAntipanico INT AUTO_INCREMENT,
    latitud DECIMAL(9,6),
    longitud DECIMAL(9,6),
    fecha Timestamp,
    idDamnificada INT,
    PRIMARY KEY (idBotonAntipanico),
    FOREIGN KEY (idDamnificada) REFERENCES Persona(idPersona)
);


CREATE VIEW VistaRestriccionDTO AS
SELECT  r.idRestriccion idRestriccion,
		r.distancia distancia,
        r.idUsuario idAdministrativo,
        u.email email,
        r.idVictimario idVictimario,
        pV.apellido apellidoVictimario,
        pV.nombre nombreVictimario,
        pV.DNI dniVictimario,
        r.idDamnificada idDamnificada,
        pD.apellido apellidoDamnificada,
        pD.nombre nombreDamnificada,
        pD.DNI dniDamnificada
FROM    RestriccionPerimetral r
        INNER JOIN Persona pV
            ON r.idVictimario = pV.idPersona
        INNER JOIN Persona pD
            ON r.idDamnificada = pD.idPersona
        INNER JOIN Usuario u
            ON r.idUsuario = u.idUsuario;

CREATE VIEW VistaUsuarioPersona AS
SELECT	u.idUsuario idUsuario,
        u.email email,
        u.rolDeUsuario rolDeUsuario,
        p.idPersona idPersona
FROM    Usuario u
        INNER JOIN Persona p
            ON u.idUsuario = p.idUsuario;



INSERT INTO EstadoPruebaDeVida (estadoPruebaDeVida) VALUES ("Pendiente");
INSERT INTO EstadoPruebaDeVida (estadoPruebaDeVida) VALUES ("Aceptada");
INSERT INTO EstadoPruebaDeVida (estadoPruebaDeVida) VALUES ("Rechazada");

INSERT INTO EstadoNotificacion (estadoNotificacion) VALUES ("NoVista");
INSERT INTO EstadoNotificacion (estadoNotificacion) VALUES ("Vista");
INSERT INTO EstadoNotificacion (estadoNotificacion) VALUES ("Archivada");

INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('VictimarioIlocalizable');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('DamnificadaIlocalizable');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('PruebaDeVidaFallida');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('InfraccionDeRestriccion');
INSERT INTO TipoIncidencia (tipoIncidencia) VALUES ('FueraDeRutina');

INSERT INTO RolDeUsuario (nombre) VALUES ('ADMINISTRATIVO');
INSERT INTO RolDeUsuario (nombre) VALUES ('SUPERVISOR');
INSERT INTO RolDeUsuario (nombre) VALUES ('AGRESOR');
INSERT INTO RolDeUsuario (nombre) VALUES ('DAMNIFICADA');

INSERT INTO Direccion (calle, altura, idLocalidad) VALUES ('Argerich', '1767', 2156);
INSERT INTO Usuario (email, contrasena, rolDeUsuario) VALUES ("admin@admin.com", "8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918", "SUPERVISOR");
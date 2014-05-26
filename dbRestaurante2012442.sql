CREATE DATABASE dbRestaurante2012442
GO
USE dbRestaurante2012442
GO
CREATE TABLE Tipo(
	idTipo	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	PRIMARY KEY(idTipo)
)
CREATE TABLE Platillo(
	idPlatillo	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	idTipo	INT NOT NULL,
	precio	INT,
	PRIMARY KEY(idPlatillo),
	FOREIGN KEY(idTipo) REFERENCES TipoDeComida(idTipo)
)
CREATE TABLE Ingrediente(
	idIngrediente	INT NOT NULL,
	nombre	VARCHAR(255),
	PRIMARY KEY(idIngrediente)
)
CREATE TABLE DetallePlatillo(
	idDetallePlatillo	INT IDENTITY(1,1),
	idPlatillo	INT NOT NULL,
	idIngrediente	INT NOT NULL,
	cantidad	INT,
	PRIMARY KEY(idPlatillo, idIngrediente),
	FOREIGN KEY(idPlatillo) REFERENCES Platillo(idPlatillo),
	FOREIGN KEY(idIngrediente) REFERENCES Ingrediente(idIngrediente)
)
CREATE TABLE Bebida(
	idBebida	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	idTipo	INT NOT NULL,
	PRIMARY KEY(idBebida),
	FOREIGN KEY(idTipo) REFERENCES Tipo(idTipo)
)
CREATE TABLE Modulo(
	idModulo	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	PRIMARY KEY(idModulo)
)
CREATE TABLE Usuario(
	idUsuario	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	edad	INT,
	telefono	INT,
	idModulo	INT NOT NULL,
	PRIMARY KEY(idUsuario),
	FOREIGN KEY(idModulo) REFERENCES Modulo(idModulo)
)
CREATE TABLE Queja(
	idQueja	INT IDENTITY(1,1),
	descripcion	TEXT,
	fecha	DATE,
	nombreCliente	VARCHAR(255),
	PRIMARY KEY(idQueja)
)
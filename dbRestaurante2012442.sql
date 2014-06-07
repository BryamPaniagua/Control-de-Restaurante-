CREATE DATABASE dbRestaurante2012442
GO
USE dbRestaurante2012442
GO
CREATE TABLE TipoDePlatillo(
	idTipoPlatillo	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	PRIMARY KEY(idTipoPlatillo)
)
CREATE TABLE TipoDeBebida(
	idTipoBebida	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	PRIMARY KEY(idTipoBebida)
)
CREATE TABLE Platillo(
	idPlatillo	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	idTipoPlatillo	INT NOT NULL,
	precio	INT,
	PRIMARY KEY(idPlatillo),
	FOREIGN KEY(idTipoPlatillo) REFERENCES TipoDePlatillo(idTipoPlatillo)
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
	idTipoBebida	INT NOT NULL,
	PRIMARY KEY(idBebida),
	FOREIGN KEY(idTipoBebida) REFERENCES TipoDeBebida(idTipoBebida)
)
CREATE TABLE Modulo(
	idModulo	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	PRIMARY KEY(idModulo)
)
CREATE TABLE Usuario(
	idUsuario	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	nick	VARCHAR(255),
	contrasenia	VARCHAR(255),
	correo	VARCHAR(255),
	edad	INT,
	telefono	INT,
	idModulo	INT NOT NULL,
	PRIMARY KEY(idUsuario),
	FOREIGN KEY(idModulo) REFERENCES Modulo(idModulo)
)
CREATE TABLE Cliente(
	idCliente	INT IDENTITY(1,1),
	nombre	VARCHAR(255),
	noTarjeta	NUMERIC(16),
	pin	INT,
	PRIMARY KEY(idCliente)
)
CREATE TABLE Queja(
	idQueja	INT IDENTITY(1,1),
	descripcion	TEXT,
	fecha	DATE,
	idCliente	INT,
	PRIMARY KEY(idQueja),
	FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente)
)
CREATE TABLE Estado(
	idEstado	INT IDENTITY(1,1),
	descripcion	VARCHAR(255),
	PRIMARY KEY(idEstado)
)
CREATE TABLE Pedido(
	idPedido	INT IDENTITY(1,1),
	idCliente	INT,
	idUsuario	INT,
	idEstado	INT NOT NULL,
	fecha	DATE,
	PRIMARY KEY(idPedido),
	FOREIGN KEY(idUsuario) REFERENCES Usuario(idUsuario),
	FOREIGN KEY(idCliente) REFERENCES Cliente(idCliente),
	FOREIGN KEY(idEstado) REFERENCES Estado(idEstado)
)
CREATE TABLE Factura(
	idFactura INT IDENTITY(1,1),
	idPedido INT NOT NULL,
	total	INT,
	PRIMARY KEY(idFactura),
	FOREIGN KEY(idPedido) REFERENCES Pedido(idPedido)
)

--INGRESO
--TipoDePlatillo
INSERT INTO TipoDePlatillo(nombre) VALUES('Postre')
INSERT INTO TipoDePlatillo(nombre) VALUES('Sopa')
INSERT INTO TipoDePlatillo(nombre) VALUES('Ensalada')
INSERT INTO TipoDePlatillo(nombre) VALUES('Desayunos')
INSERT INTO TipoDePlatillo(nombre) VALUES('Almuerzos')
INSERT INTO TipoDePlatillo(nombre) VALUES('Cenas')

--TipoDeBebida
INSERT INTO TipoDeBebida(nombre) VALUES('Jugo')
INSERT INTO TipoDeBebida(nombre) VALUES('Gaseosa')
INSERT INTO TipoDeBebida(nombre) VALUES('Te frio')

--Platillo
INSERT INTO Platillo(nombre, idTipoPlatillo, precio) VALUES('Pollo Asado', 5, 200)
INSERT INTO Platillo(nombre, idTipoPlatillo, precio) VALUES('Helado', 5, 25)
INSERT INTO Platillo(nombre, idTipoPlatillo, precio) VALUES('Huevo con frijoles', 4, 100)
INSERT INTO Platillo(nombre, idTipoPlatillo, precio) VALUES('Hamburguesa', 6, 75)
INSERT INTO Platillo(nombre, idTipoPlatillo, precio) VALUES('Ensalada de lechuga', 3, 20)

--Ingrediente
INSERT INTO Ingrediente(nombre) VALUES('Tomate')
INSERT INTO Ingrediente(nombre) VALUES('Pollo')
INSERT INTO Ingrediente(nombre) VALUES('Huevos')
INSERT INTO Ingrediente(nombre) VALUES('Frijol')
INSERT INTO Ingrediente(nombre) VALUES('Lechuga')
INSERT INTO Ingrediente(nombre) VALUES('Carne de cerdo')

--DetallePlatillo
INSERT INTO DetallePlatillo(idPlatillo, idIngrediente, cantidad) VALUES(3, 3, 10)
INSERT INTO DetallePlatillo(idPlatillo, idIngrediente, cantidad) VALUES(3, 4, 2)
INSERT INTO DetallePlatillo(idPlatillo, idIngrediente, cantidad) VALUES(5, 5, 11)
INSERT INTO DetallePlatillo(idPlatillo, idIngrediente, cantidad) VALUES(5, 1, 10)
INSERT INTO DetallePlatillo(idPlatillo, idIngrediente, cantidad) VALUES(4, 6, 2)

--Bebida
INSERT INTO Bebida(nombre, idTipoBebida) VALUES('Pepsi', 2)
INSERT INTO Bebida(nombre, idTipoBebida) VALUES('Te Lipton', 3)
INSERT INTO Bebida(nombre, idTipoBebida) VALUES('Coca Cola', 2)
INSERT INTO Bebida(nombre, idTipoBebida) VALUES('Jugo de melocoton', 1)

--Modulo
INSERT INTO Modulo(nombre) VALUES('Mesero')
INSERT INTO Modulo(nombre) VALUES('Chef')
INSERT INTO Modulo(nombre) VALUES('Administrador')

--Usuario
INSERT INTO Usuario(nombre, nick, contrasenia, correo, edad, telefono, idModulo) VALUES('Manuel', 'mm', 'mm', 'm@gmail.com', 21, 24751254, 1)
INSERT INTO Usuario(nombre, nick, contrasenia, correo, edad, telefono, idModulo) VALUES('Juan', 'jop', 'jop', 'jp@yahoo.com', 25, 21104244, 2)
INSERT INTO Usuario(nombre, nick, contrasenia, correo, edad, telefono, idModulo) VALUES('Miguel', 'noj', 'noj', 'mnoj@hotmail.com', 26, 28682854, 3)

--Cliente
INSERT INTO Cliente(nombre, noTarjeta, pin) VALUES('Pedro', 2514254785412541, 2512)
INSERT INTO Cliente(nombre, noTarjeta, pin) VALUES('Ramon', 1254251247853698, 2123)
INSERT INTO Cliente(nombre, noTarjeta, pin) VALUES('cf', 2542542154875214, 1254)

--Queja
INSERT INTO Queja(descripcion, fecha, idCliente) VALUES('Escriba aui sus quejas', '27-05-2014', 1)

--Estado
INSERT INTO Estado(descripcion) VALUES('En espera')
INSERT INTO Estado(descripcion) VALUES('Entregada')
INSERT INTO Estado(descripcion) VALUES('Cancelada')
INSERT INTO Estado(descripcion) VALUES('Pagada')

--Pedido
INSERT INTO Pedido(idCliente, idUsuario, idEstado, fecha) VALUES(1, 1, 1, '27-05-2014')
INSERT INTO Pedido(idCliente, idUsuario, idEstado, fecha) VALUES(2, 1, 4, '27-05-2014')

--Factura
INSERT INTO Factura(idPedido, total) VALUES(2, 250)
-- Active: 1714541077175@@127.0.0.1@3306@inventario
CREATE DATABASE inventario;

DROP DATABASE inventario;

USE inventario;

-- CREACION DE TABLAS

CREATE TABLE producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre_producto VARCHAR(50),
    precio INT NOT NULL,
    precio_oferta INT
);

CREATE TABLE stock (
    id_stock BIGINT PRIMARY KEY AUTO_INCREMENT,
    stock int NOT NULL,
    stock_minimo INT NOT NULL,
    stock_maximo INT NOT NULL,
    producto_id BIGINT NOT NULL UNIQUE,
    Foreign Key (producto_id) REFERENCES producto (id)
);

CREATE TABLE inventario (
    id_inventario BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre_inventario VARCHAR(50) NOT NULL
);

CREATE TABLE inventario_producto (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    inventario_id BIGINT,
    producto_id BIGINT,
    Foreign Key (inventario_id) REFERENCES inventario (id_inventario),
    Foreign Key (producto_id) REFERENCES producto (id)
);

-- COSAS UTILES

SHOW CREATE TABLE producto;

ALTER TABLE producto DROP FOREIGN KEY producto_ibfk_1;

ALTER TABLE producto DROP COLUMN stock_id;

DROP TABLE producto;

-- INSERTAR DATOS

INSERT INTO
    producto (
        nombre_producto,
        precio,
        precio_oferta
    )
VALUES ("Chocolate", 1000, 700);

INSERT INTO
    stock (
        stock,
        stock_minimo,
        stock_maximo,
        producto_id
    )
VALUES (20, 3, 50, 1);

INSERT INTO
    inventario (nombre_inventario)
VALUES ("Sucursal tu hermana");

INSERT INTO
    inventario_producto (inventario_id, producto_id)
VALUES (1, 1);

-- ULTIMO EN SER INSERTADO; EL ID
SELECT * FROM producto WHERE id = LAST_INSERT_ID();

SELECT * FROM producto ORDER BY id DESC LIMIT 1;

SELECT p.*, s.stock, s.stock_minimo, s.stock_maximo
FROM producto p
    LEFT JOIN stock s ON p.id = s.producto_id;
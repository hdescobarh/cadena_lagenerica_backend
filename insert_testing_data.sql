#DROP DATABASE tienda_test;
#CREATE DATABASE tienda_test;
USE tienda_test;

# Datos prueba para modulo usuarios

# para admininicial el pss: admin123456
INSERT INTO usuarios (cedula_usuario, email_usuario, nombre_usuario, password, usuario)
values ("0", "admininicial@falso.com", "Administrador inicial", "$2a$10$sAWQ7SL/np50tTFs4ZEsseHvKGnfpvrQTqCEtkWtfj.Ssb4gagijC", "admininicial");

INSERT INTO usuarios (cedula_usuario, email_usuario, nombre_usuario, password, usuario)
values ("10111", "usuario1@falso.com", "Nombre1 Apellido1", "$2a$10$g/GAPN7TEU.V4TeouTWm3OfEd9aMzd4jVX3U1QbchvUHJDXn.2O7S", "username1");

INSERT INTO usuarios (cedula_usuario, email_usuario, nombre_usuario, password, usuario)
values ("10222", "usuario2@falso.com", "Nombre2 Apellido2", "$2a$10$Sp2cCA.V5/IE4IeJKwdcs.uYjroTkgrjts6pXCob.NS4R2essZ4ZO", "username2");

INSERT INTO usuarios (cedula_usuario, email_usuario, nombre_usuario, password, usuario)
values ("10333", "usuario3@falso.com", "Nombre3 Apellido3", "$2a$10$NEg4xEKUBt/YG87swZ5M/e3VMdNhzroDsNdORxGsWSWYiBtoY.NSK", "username3");

# Datos prueba para modulo clientes

INSERT INTO clientes (cedula_cliente, email_cliente, nombre_cliente, direccion_cliente, telefono_cliente)
values ("90111", "cliente1@falso.com", "Nombre Cliente1", "Avenida Siempreviva 1", "111-111-111");

INSERT INTO clientes (cedula_cliente, email_cliente, nombre_cliente, direccion_cliente, telefono_cliente)
values ("90222", "cliente2@falso.com", "Nombre Cliente2", "Avenida Siempreviva 2", "222-222-222");

INSERT INTO clientes (cedula_cliente, email_cliente, nombre_cliente, direccion_cliente, telefono_cliente)
values ("90333", "cliente3@falso.com", "Nombre Cliente3", "Avenida Siempreviva 3", "333-333-333");

# Datos prueba para modulo proveedores

INSERT INTO proveedores (nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor)
values ("50111", "Bogotá D.C.", "Avenida El Dorado1", "Proveedor1", "315 111 1111");

INSERT INTO proveedores (nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor)
values ("50222", "Cali, Valle", "Avenida El Dorado2", "Proveedor2", "325 222 2222");

INSERT INTO proveedores (nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor)
values ("50333", "Madrid, Cundinamarca", "Avenida El Dorado3", "Proveedor3", "335 333 3333");

INSERT INTO proveedores (nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor)
values ("1", "Cali, Valle", "Calle prueba 1", "Proveedor lista1", "300 111 0101");

INSERT INTO proveedores (nitproveedor, ciudad_proveedor, direccion_proveedor, nombre_proveedor, telefono_proveedor)
values ("2", "Medellín, Antioquía", "Calle prueba 2", "Proveedor lista2", "300 222 0202");

# Datos prueba modulo productos

INSERT INTO productos(codigo_producto, ivacompra, nitproveedor, nombre_producto, precio_compra, precio_venta)
values("808001", "19", "50111", "Papitas marca1", "12500", "13800");

INSERT INTO productos(codigo_producto, ivacompra, nitproveedor, nombre_producto, precio_compra, precio_venta)
values("808002", "19", "50111", "Cebollitas marca1", "2900", "3500");

INSERT INTO productos(codigo_producto, ivacompra, nitproveedor, nombre_producto, precio_compra, precio_venta)
values("808003", "19", "50222", "Crema dental marca2", "11320", "13500");

INSERT INTO productos(codigo_producto, ivacompra, nitproveedor, nombre_producto, precio_compra, precio_venta)
values("808004", "19", "50222", "Crema dental marca3", "9250", "13000");

INSERT INTO productos(codigo_producto, ivacompra, nitproveedor, nombre_producto, precio_compra, precio_venta)
values("808005", "19", "50333", "Camiseta marca10", "30200", "55230");

# Datos prueba modulo ventas

INSERT INTO ventas(cedula_cliente, cedula_usuario, ivaventa, valor_venta, total_venta)
VALUES("90111", "10111", "12996", "68400", "81396");
INSERT INTO detalle_ventas(cantidad_producto, codigo_producto, codigo_venta, valoriva, valor_venta, valor_total)
VALUES("3", "808001", LAST_INSERT_ID(), "19", "13800", "41400"),
("2", "808003", LAST_INSERT_ID(), "19", "13500", "27000");

INSERT INTO ventas(cedula_cliente, cedula_usuario, ivaventa, valor_venta, total_venta)
VALUES("90111", "10111", "3325", "17500", "20825");
INSERT INTO detalle_ventas(cantidad_producto, codigo_producto, codigo_venta, valoriva, valor_venta, valor_total)
VALUES("5", "808002", LAST_INSERT_ID(), "19", "3500", "17500");

INSERT INTO ventas(cedula_cliente, cedula_usuario, ivaventa, valor_venta, total_venta)
VALUES("90333", "10111", "3325", "165690", "197171.1");
INSERT INTO detalle_ventas(cantidad_producto, codigo_producto, codigo_venta, valoriva, valor_venta, valor_total)
VALUES("3", "808005", LAST_INSERT_ID(), "19", "165690", "197171.1");
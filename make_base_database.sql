CREATE DATABASE distribuidoramintic;

USE distribuidoramintic;

CREATE TABLE clientes(
	cedula_cliente BIGINT(20) NOT NULL,
    direccion_cliente VARCHAR(255) NOT NULL,
    email_cliente VARCHAR(255) NOT NULL,
    nombre_cliente VARCHAR(255) NOT NULL,
    telefono_cliente VARCHAR(255) NOT NULL,
    PRIMARY KEY (cedula_cliente)
    );
    
CREATE TABLE usuarios(
	cedula_usuario BIGINT(20) NOT NULL,
    email_usuario VARCHAR(255) NOT NULL,
    nombre_usuario VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    usuario VARCHAR(255) NOT NULL,
    PRIMARY KEY(cedula_usuario),
    UNIQUE(usuario)
);

CREATE TABLE ventas(
	codigo_venta BIGINT(20) NOT NULL AUTO_INCREMENT,
    cedula_cliente BIGINT(20) NOT NULL,
    cedula_usuario BIGINT(20) NOT NULL,
    ivaventa DOUBLE NOT NULL,
    total_venta DOUBLE NOT NULL,
    valor_venta DOUBLE NOT NULL,
    PRIMARY KEY(codigo_venta),
    FOREIGN KEY(cedula_cliente) REFERENCES clientes(cedula_cliente),
    FOREIGN KEY(cedula_usuario) REFERENCES usuarios(cedula_usuario)
);

CREATE TABLE proveedores(
nitproveedor BIGINT(20) NOT NULL,
ciudad_proveedor VARCHAR(255) NOT NULL,
direccion_proveedor VARCHAR(255) NOT NULL,
nombre_proveedor VARCHAR(255) NOT NULL,
telefono_proveedor VARCHAR(255) NOT NULL,
PRIMARY KEY(nitproveedor)
);

CREATE TABLE productos(
	codigo_producto BIGINT(20) NOT NULL,
    ivacompra DOUBLE NOT NULL,
    nitproveedor BIGINT(20) NOT NULL,
    nombre_producto VARCHAR(255) NOT NULL,
    precio_compra DOUBLE NOT NULL,
    precio_venta DOUBLE NOT NULL,
    PRIMARY KEY (codigo_producto),
    FOREIGN KEY (nitproveedor) REFERENCES proveedores(nitproveedor)
);

CREATE TABLE detalle_ventas(
codigo_detalle_venta BIGINT(20) NOT NULL AUTO_INCREMENT,
cantidad_producto INT(11) NOT NULL,
codigo_producto BIGINT(20) NOT NULL,
codigo_venta BIGINT(20) NOT NULL,
valor_total DOUBLE NOT NULL,
valor_venta DOUBLE NOT NULL,
valoriva DOUBLE NOT NULL,
PRIMARY KEY (codigo_detalle_venta),
FOREIGN KEY(codigo_producto) REFERENCES productos(codigo_producto),
FOREIGN KEY(codigo_venta) REFERENCES ventas(codigo_venta)
);







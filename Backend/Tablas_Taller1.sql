create database inventario;

use inventario;

CREATE TABLE inventario (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo ENUM('Producto', 'Ingrediente') NOT NULL,
    cantidad int NOT NULL,
    precio DECIMAL(10, 2)
);

select * from inventario;
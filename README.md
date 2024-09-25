# Inventario API

## Descripción

La **Inventario API** es una API RESTful diseñada para gestionar el inventario de productos. Permite realizar operaciones de creación, lectura, actualización y eliminación (CRUD) en los ítems de inventario.

## Características

- **Obtener todos los ítems**: Listar todos los ítems disponibles en el inventario.
- **Crear un nuevo ítem**: Añadir nuevos ítems al inventario.
- **Obtener ítem por ID**: Obtener detalles de un ítem específico utilizando su ID.
- **Actualizar ítem existente**: Modificar la información de un ítem ya existente.
- **Eliminar ítem**: Eliminar un ítem del inventario.

## Tecnologías

- **Spring Boot**: Framework para construir aplicaciones web basadas en Java.
- **Jakarta EE**: Para la especificación de los recursos y las entidades.
- **OpenAPI**: Para la documentación y pruebas de la API.
- **Swagger**: Para la visualización de la API y su documentación.

### Developers
Daniel Latorre Ruiz // Backend

Andres David Perez Santiago // frontend
## Estructura de la Tabla `inventario`

La tabla de inventario está definida como sigue:

```sql
CREATE TABLE inventario (
    id_item INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    tipo ENUM('Producto', 'Ingrediente') NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2)
);



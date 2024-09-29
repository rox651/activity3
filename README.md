# Proyecto: Gestión de Empleados con Spring Boot

Este proyecto es una aplicación web de gestión de empleados desarrollada utilizando **Spring Boot**. La aplicación permite crear, actualizar, eliminar y visualizar información de los empleados a través de una interfaz web y proporciona validaciones para garantizar que los datos introducidos sean correctos. Los datos se almacenan en una base de datos, y el sistema utiliza un flujo basado en controladores, servicios y repositorios para manejar las operaciones CRUD (Crear, Leer, Actualizar, Eliminar).

## Estructura del Proyecto

### Modelos

#### EmployeeEntity

La entidad EmployeeEntity es la representación del empleado en la base de datos. Esta clase está anotada con @Entity, y tiene las siguientes propiedades:

-  **id**: Identificador único de cada empleado.
-  **firstName**: Nombre del empleado.
-  **lastName**: Apellido del empleado.
-  **email**: Correo electrónico del empleado

### Controladores

#### EmployeeController

Este controlador maneja las solicitudes relacionadas con los empleados y utiliza las plantillas de Thymeleaf para renderizar las vistas.

**Endpoints principales:**

-  GET /employees: Lista todos los empleados.
-  GET /add-employee: Muestra un formulario para añadir un nuevo empleado.
-  POST /create-employee: Crea un nuevo empleado con los datos del formulario.
-  GET /edit-employee/{id}: Muestra un formulario para editar un empleado existente.
-  POST /update-employee/{id}: Actualiza los datos de un empleado.

### Servicios

#### EmployeeService

La capa de servicio maneja la lógica de negocio. Es responsable de interactuar con el repositorio de empleados y aplicar cualquier lógica adicional antes de realizar las operaciones CRUD.

### Repositorios

#### EmployeeRepository

Este es el repositorio que interactúa directamente con la base de datos. Extiende JpaRepository para permitir operaciones CRUD en la entidad EmployeeEntity.

### Vistas (Thymeleaf Templates)

Las vistas se manejan utilizando Thymeleaf.

### Validación

Se utiliza la validación de campos a través de las anotaciones de jakarta.validation en la entidad `EmployeeEntity`

### Personalización de Páginas de Error

Se añadió una página personalizada para manejar errores 404 y otros códigos de estado HTTP. Esta página muestra un mensaje informativo y un enlace para regresar a la página principal.

## Ejecución del Proyecto

1. Clona este repositorio: git clone
2. Configura tu base de datos en el archivo application.properties (Actualmente se esta usando h2.Driver).
3. Ejecuta la aplicación usando el comando: `mvn spring-boot:run`.
4. Accede a la aplicación en el navegador: http://localhost:8080/.

## Pruebas

Las pruebas automatizadas se pueden ejecutar con el siguiente comando:

`./mvn test`

# Challenge ONE | Java | Back-end | Hotel Alura
Este proyecto consiste en un programa que se encargue de gestionar un hotel  :hotel: (**México Mágico Suites**). Donde podremos primeramente loguearnos, realizar una reserva, ingresar datos del huésped, así como editar ambos. Para el desarrollo de este proyecto se siguió la metodología MVC y algunas bibliotecas de terceros, el programa está elaborado en lenguaje **Java** :coffee: para toda la interfaz y **MySQL** para la base de datos.
## <img width="64" height="64" src="https://img.icons8.com/nolan/64/1A6DFF/C822FF/java-coffee-cup-logo.png" alt="java-coffee-cup-logo"/> Tecnologías Utilizadas:

- Java
- Eclipse
- MySql
- Biblioteca JCalendar
- Plugin WindowBuilder </br>

## <img width="64" height="64" src="https://img.icons8.com/color/48/java-coffee-cup-logo--v1.png" alt="java-coffee-cup-logo--v1"/>Requisitos para utilizar:

- Java
- MySQL

## ⚙️ Configurando MySQL:

- Creando la Base de Datos
```sql
CREATE DATABASE hotel_alura;
```
- Accediendo a la Base de Datos creada
```sql
USE hotel_alura;
```
- Creando la Tabla Reservas
```sql
CREATE TABLE reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    fecha_entrada DATE NOT NULL,
    fecha_salida DATE NOT NULL,
    valor DECIMAL(10, 2) NOT NULL,
    forma_pago VARCHAR(255) NOT NULL
);
```
- Creando la Tabla Huéspedes
```sql
CREATE TABLE huespedes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    apellido VARCHAR(255) NOT NULL,
    fecha_nacimiento DATE NOT NULL,
    nacionalidad VARCHAR(255) NOT NULL,
    telefono VARCHAR(20),
    id_reserva INT,
    FOREIGN KEY (id_reserva) REFERENCES reservas(id)
);
```

### Componentes:  :pencil:
- :one: Vista menu principal.
- :two: Vista de inicio de sesión.
- :three: Vistra principal (registro de huespedes)
	- Modificar informacion de usuario
- :four: Vista administracion de huespedes
- :five: Vista administracion de habitaciones
- :six: Vista configuraciones (Solo administradores)
	- Vista añadir nuevo usuario
1. :arrow_forward: Vista menu principal
Esta es la primera interfase que se ejecuta en donde podemos seleccionar iniciar sesion o registrarse (en creacion)



--- 

# Challenge ONE | Java | Back-end | Hotel Alura
Este proyecto consiste en un programa que se encargue de gestionar un hotel  :hotel: (**México Mágico Suites**). Donde podremos primeramente loguearnos, realizar una reserva, ingresar datos del huésped, así como editar ambos, el programa está elaborado en lenguaje **Java** :coffee: para toda la interfaz y **MySQL** para la base de datos.
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
## ⚙️ Configurando la Conexión a MySQL:
Para que la aplicación funcione correctamente, es necesario configurar la conexión a la base de datos MySQL mediante el uso de variables de entorno.

## :pencil: Componentes:  
- :one: Vista menu principal.
- :two: Vista de inicio de sesión.
- :three: Vista menu usuario.
- :four: Vista registro de reserva.
- :five: Vista registro de huésped.
- :six: Vista búsqueda y edición.

1. :arrow_forward: Vista menu principal:
Esta es la primera interfaz que se ejecuta en donde podemos seleccionar, iniciar sesión o registrarse (en creación).

![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2809).png)

2. :arrow_forward: Vista de inicio de sesión:
En esta interfaz podremos loguearnos introduciendo nombre de usuario y contraseña (por el momento solo cuenta con un único usuario: admin contraseña: admin).

![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2810).png)

3. :arrow_forward: Vista menu usuario:
En esta interfaz nos da la bienvenida mostrándonos una leyenda, fecha y hora actual, así como dos opciones para ingresar a realizar una búsqueda o realizar un registro de reserva.

![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2811).png)

4. :arrow_forward: Vista registro de reserva:
En esta interfaz nos permite realizar una reserva, seleccionando tanto la fecha de entrada como la de salida, la cual nos genera el costo por noche, tomando en cuenta que el costo por noche es de $250.00 y nos permite seleccionar la forma de pago: Tarjeta de crédito, débito o efectivo.

![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2812).png)
![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2813).png)

5. :arrow_forward: Vista registro de huésped:
En esta interfaz nos permite registrar los datos del huésped: nombre, apellido, fecha de nacimiento, nacionalidad y teléfono, así como un campo para nuestro número de reserva que es autogenerado y no editable. Una vez estén correctos nuestros datos se lanza ventana informando los datos guardados satisfactoriamente.

![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2814).png)
![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2815).png)
![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2820).png)

6. :arrow_forward: Vista búsqueda y edición:
En esta interfaz nos permite realizar una búsqueda, ya sea por número de reserva o apellido, así mismo editar tanto nuestra reserva como nuestro huésped, abriendo nuevos cuadros de diálogo para insertar los nuevos datos.

![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2817).png)
![Menu Principal](https://github.com/juanVarela666/Hotel-Alura/blob/main/img/Captura%20de%20pantalla%20(2819).png)
--- 

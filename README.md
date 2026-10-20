# TALLER 8 - TIENDA DON ERNESTO

- El taller se organiza de manera de 4 paquetes que contienen cada uno una parte distinta de codigo del programa:
1. model
2. dao
3. vista
4. conexion
- La Base de Datos la Ejecute en XAMPP con el puerto 8080 en local
- La Base de datos fue creada con este script:
  
CREATE DATABASE IF NOT EXISTS tienda;

USE tienda;

CREATE TABLE categoria (

id_categoria INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(50) NOT NULL UNIQUE

);

CREATE TABLE producto (

id_producto INT AUTO_INCREMENT PRIMARY KEY,

nombre VARCHAR(80) NOT NULL,

precio DECIMAL(10,2) NOT NULL,

cantidad INT NOT NULL DEFAULT 0,

id_categoria INT NOT NULL,

FOREIGN KEY (id_categoria) REFERENCES categoria(id_categoria)

);

CREATE TABLE ventas(

id_venta INT AUTO_INCREMENT PRIMARY KEY,

id_producto INT NOT NULL,

id_categoria INT NOT NULL,

nombre VARCHAR(80) NOT NULL,

cantidad INT NOT NULL,

precio DECIMAL(10,2),

FOREIGN KEY (id_producto) REFERENCES producto(id_producto)

);

INSERT INTO categoria (nombre)

VALUES ('Lacteos'), ('Aseo'), ('Bebidas'), ('Granos');

# FUNCIONAMIENTO
- Al ejecutar el programa en NetBeans se abrira la interfaz de inventario, actualmente hay 5 productos ya guardados, se pueden actualizar, eliminar y agregar mas productos
- Aparecen marcados con rojo aquellos que su cantidad es menor a 5
- Cualquier modificacion que hagas en la interfaz se vera reflejada en la base de datos, y eso significa tambien que si se cierra el programa, al abrirlo la informacion queda almacenada 
- si das click en alguna fila de la tabla te permite seleccionarla
- Agregue un funcionamiento extra "VENTAS", una nueva interfaz, en la cual al dar click en el boton de vender se abrira esa nueva interfaz
- En la nueva funcion de vender permite que el inventario que hay se pueda vender, hay dos JComboBox en los cuales son producto y cantidad, la cantidad que aparece ahi es dependiendo el stock que exista
- Cuando demos al boton vender, se agregara a la base de datos en la tabla ventas y en la tabla ventas de la interfaz, en la cual muestra el producto, la cantidad y el precio
- El precio se calcula multiplicando la cantidad que selecciona por el precio unitario del producto

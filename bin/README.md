# VDG Backend 🌐

Este proyecto contiene el backend para la aplicación VDG. Proporciona instrucciones sobre cómo configurar y ejecutar el repositorio.

## Instrucciones para correr el repo

1. **Crear una base de datos (MySQL):** Crea una base de datos llamada `vdg` en tu servidor MySQL junto a un usario vdgpps y una contraseña vdgpps2019.
   ```
   -- Crear la base de datos
   CREATE DATABASE vdg;
      
   -- Usar la base de datos creada
   USE vdg;

   --Crear usuario y darle permisos
   CREATE USER 'vdgpps'@'%' IDENTIFIED BY 'vdgpps2019';

   GRANT ALL PRIVILEGES ON *.* TO 'vdgpps'@'%';
    
   FLUSH PRIVILEGES;
   ```

3. **Ejecutar los scripts SQL:** Ejecuta los scripts SQL que contienen los inserts iniciales en tu base de datos `vdg` (carpeta `ScriptMysql`).

4. **Configurar properties:** Se debe configurar el `aplication.properties` con los parametros de la base de datos y el puerto como se muestra a continuacion.
   Los parámetros de la base de datos y el puerto se configuran en el archivo `application.properties`.

   ![image](https://github.com/Nicolas2k19/PP2Backend/assets/86579814/ec539424-c248-4ac3-ae84-f9393bab5acb)
   
   La aplicación se ejecuta en el puerto 9090 por defecto.
   
6. **Limpiar el proyecto:** En Eclipse, ve a `Project > Clean` para limpiar el proyecto.

7. **Compilar y correr:** Compila el proyecto y ejecútalo.

## Documentación de la API

La documentación de la API se genera automáticamente con Swagger y está disponible en:

[http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

![image](https://github.com/Nicolas2k19/PP2Backend/assets/86579814/e105e139-640a-4384-bebd-e8ad43be27c4)


Puedes utilizar esta página para explorar y probar las API proporcionadas por el backend.

## Pruebas con Postman

Se incluye un archivo llamado `Vdg.postman_collection.json`, el cual contiene pruebas de las APIs que puedes importar en Postman.


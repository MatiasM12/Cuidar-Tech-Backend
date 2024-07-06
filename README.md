# Cuidar Tech Backend 🌐

Cuidar Tech es un proyecto dedicado a la prevención de la violencia de género mediante el monitoreo de restricciones perimetrales. Este proyecto se basa en la continuación del proyecto original desarrollado por la Universidad Nacional de General Sarmiento (UNGS), que puedes encontrar aquí: https://www.ungs.edu.ar/new/cuidar-tech-una-posible-solucion-tecnologica-para-intervenir-en-casos-de-violencia-domestica-contra-las-mujeres.En esta nueva fase, hemos implementado dos funcionalidades clave: pruebas de vida automáticas y predicción de rutinas para prevenir violaciones de restricciones perimetrales. Ademas de que implementamos otras mejores tanto esteticas como funcionales. Este es el repositorio que contiene el backend del proyecto, podes econtrar el front aquí: https://github.com/MatiasM12/Cuidar-Tech-Frontend y el respositorio de la app movil aqui: https://github.com/MatiasM12/VdgApp.

## Instrucciones para correr el repo 👨‍🏫

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

3. **Ejecutar los scripts SQL:** Ejecuta los scripts SQL que contienen los inserts iniciales en tu base de datos `vdg` (carpeta `ScriptMysql`). Como se muestra a continuacion:

   Una manera de ejecutarlos es corriendo el comando: "source <`rutadelarchivo`>", como por ejemplo:

   ![image](https://github.com/Nicolas2k19/PP2Backend/assets/86579814/72a6ed9f-2881-4863-86eb-6d4710ae9a4c)

   Debemos ejecuutar los scripts en el siguiente orden:
   
         -vdg.sql
         -fotoIdentificacion1.sql
         -fotoIdentificacion2.sql
         -fotoIdentificacion3.sql
         -fotoPruebaDeVida.sql
         -incidencia.sql
         -localidad.sql
         -notificacion.sql
         -ciudad.sql
         -configMensajes.sql
         -comisaria.sql

5. **Configurar properties:** Se debe configurar el `aplication.properties` con los parametros de la base de datos y el puerto como se muestra a continuacion.
   Los parámetros de la base de datos y el puerto se configuran en el archivo `application.properties`.

   ![image](https://github.com/Nicolas2k19/PP2Backend/assets/86579814/ec539424-c248-4ac3-ae84-f9393bab5acb)
   
   La aplicación se ejecuta en el puerto 9090 por defecto.
   
6. **Limpiar el proyecto:** En Eclipse, ve a `Project > Clean` para limpiar el proyecto.

7. **Compilar y correr:** Compila el proyecto y ejecútalo.

## Configuración de CORS para Comunicación entre Backend, Frontend y Aplicación Móvil 🛠️

Para asegurar una comunicación adecuada entre el backend, el frontend y la aplicación móvil, es fundamental configurar el archivo de CORS (Cross-Origin Resource Sharing). A continuación, se detallan los pasos necesarios:

### Configuración de CORS en Java

1. **Ubicación del Archivo de Configuración**: Dirígete a `src/vdg/CorsConfig.java`.

2. **Ejemplo de Configuración en CorsConfig.java**:

![image](https://github.com/Nicolas2k19/PP2Backend/assets/86579814/723d19db-791d-4f3c-b8dc-9e22f02e5f55)


## Documentación de la API 📚

La documentación de la API se genera automáticamente con Swagger y está disponible en:

[http://localhost:9090/swagger-ui.html](http://localhost:9090/swagger-ui.html)

![image](https://github.com/Nicolas2k19/PP2Backend/assets/86579814/e105e139-640a-4384-bebd-e8ad43be27c4)


Puedes utilizar esta página para explorar y probar las API proporcionadas por el backend.

## Pruebas con Postman 🧬

Se incluye un archivo llamado `Vdg.postman_collection.json`, el cual contiene pruebas de las APIs que puedes importar en Postman.

## Manual de usuario 📕
Para obtener una guia detallada de como funciona el sistema porfavopr leea el manual de usuario que subimos a esta repositorio.


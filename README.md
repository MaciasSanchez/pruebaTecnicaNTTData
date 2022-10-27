# pruebaApiRest
prueba NTTData

Dentro de la carpeta api rest, se encuentra el desarrollo del servicio en Spring Boot



Despliegue del servicio.

Dentro de powershell abrimos la ruta donde se guarde nuestro proyecto

En el terminal de nuestro servicios ejecutamos el comando mvn package -Dmaven.test.skip
Luego procedemos a generar el contenedor ejecutamos el comando docker build -t api-rest

Una vez generado el contenedor y tener el archivo jar. ejecutamos el comando 
docker-compose up --build

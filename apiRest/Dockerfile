FROM openjdk:11
ADD /target/nttdata-ws-prueba-1.0.0.jar nttdata-ws-prueba-1.0.0.jar
RUN mkdir -p /usr/src/apirest
WORKDIR /usr/src/apirest
EXPOSE 8003
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-Duser.timezone=EC", "-jar", "nttdata-ws-prueba-1.0.0.jar"]
ENTRYPOINT [ "java","-jar","/nttdata-ws-prueba-1.0.0.jar" ]
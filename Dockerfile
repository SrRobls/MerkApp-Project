# Usa una imagen de OpenJDK como base
FROM openjdk:17-jdk-slim

# Establecer el directorio de trabajo en /app
WORKDIR /app

# Copia el archivo JAR de la aplicación
COPY target/Merkapp-0.0.1-SNAPSHOT.jar merkapp.jar

# Exponer el puerto que usa tu aplicación (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "merkapp.jar"]

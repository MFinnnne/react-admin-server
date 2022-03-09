FROM openjdk:11
VOLUME ['/tmp','/usr/local/upload-dir']
COPY ./target/*.jar /app.jar
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]
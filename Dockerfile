FROM openjdk:11

VOLUME /tmp
COPY ./target/*.jar /app.jar
CMD ["--server.port=5000"]
EXPOSE 5000
ENTRYPOINT ["java","-jar","/app.jar"]

FROM adoptopenjdk/openjdk11
COPY app.jar app.jar
EXPOSE 8080
EXPOSE 3306
ENTRYPOINT ["java", "-jar", "app.jar"]

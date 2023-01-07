FROM adoptopenjdk/openjdk11
COPY ./app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM adoptopenjdk/openjdk11
CMD ["./gradlew build -x test"]
ARG JAR_FILE_PATH=build/libs/workforus.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

FROM adoptopenjdk/openjdk11
CMD ["ls", "-al"]
RUN pwd
COPY build/app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

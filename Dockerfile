FROM adoptopenjdk/openjdk11
CMD ["ls", "-al"]
RUN pwd
COPY app.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]

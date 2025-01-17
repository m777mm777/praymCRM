FROM openjdk:17
COPY target/*.jar app.jar
COPY target/classes/static/images /app/resources/static/images
ENTRYPOINT ["java","-jar","/app.jar"]
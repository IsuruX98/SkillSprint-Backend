FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/service-registry-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8761
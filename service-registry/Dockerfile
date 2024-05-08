FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/service-registry-0.0.1-SNAPSHOT.jar skillsprint-service-registry.jar
ENTRYPOINT ["java","-jar","/skillsprint-service-registry.jar"]
EXPOSE 8761
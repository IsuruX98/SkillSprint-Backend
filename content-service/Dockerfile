FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/content-service-0.0.1-SNAPSHOT.jar skillsprint-content-service.jar
ENTRYPOINT ["java","-jar","/skillsprint-content-service.jar"]
EXPOSE 8087
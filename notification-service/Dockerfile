FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/notification-service-0.0.1-SNAPSHOT.jar skillsprint-notification-service.jar
ENTRYPOINT ["java","-jar","/skillsprint-notification-service.jar"]
EXPOSE 8084
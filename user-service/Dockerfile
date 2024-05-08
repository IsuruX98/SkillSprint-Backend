FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/user-service-0.0.1-SNAPSHOT.jar skillsprint-user-service.jar
ENTRYPOINT ["java","-jar","/skillsprint-user-service.jar"]
EXPOSE 8077
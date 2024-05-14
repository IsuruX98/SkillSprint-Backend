FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/learner-service-0.0.1-SNAPSHOT.jar skillsprint-learner-service.jar
ENTRYPOINT ["java","-jar","/skillsprint-learner-service.jar"]
EXPOSE 8079
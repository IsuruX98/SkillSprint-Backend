FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/course-service-0.0.1-SNAPSHOT.jar skillsprint-course-service.jar
ENTRYPOINT ["java","-jar","/skillsprint-course-service.jar"]
EXPOSE 8078
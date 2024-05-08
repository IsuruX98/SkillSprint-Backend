FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/api-gateway-0.0.1-SNAPSHOT.jar skillsprint-gateway.jar
ENTRYPOINT ["java","-jar","/skillsprint-gateway.jar"]
EXPOSE 9191
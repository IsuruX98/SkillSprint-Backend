FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ./target/payment-service-0.0.1-SNAPSHOT.jar skillsprint-payment-service.jar
ENTRYPOINT ["java","-jar","/skillsprint-payment-service.jar"]
EXPOSE 8085
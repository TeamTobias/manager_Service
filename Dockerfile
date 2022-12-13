FROM openjdk:17-ea-11-jdk-slim
VOLUME /tmp
COPY build/libs/managerService-1.0.jar managerservice.jar
ENTRYPOINT ["java","-jar","/managerservice.jar"]
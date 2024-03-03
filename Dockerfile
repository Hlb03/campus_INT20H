FROM openjdk:17-alpine

COPY /target/student_campus-v1.jar /microservices/student_campus-v1.jar

ENTRYPOINT ["java", "-jar", "/microservices/student_campus-v1.jar"]
FROM openjdk:18-slim-buster
COPY ./target/hr-1.0.jar  /home/hr/hr-1.0.jar
CMD ["java", "-jar", "/home/hr/hr-1.0.jar"]

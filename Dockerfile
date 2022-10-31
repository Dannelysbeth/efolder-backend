FROM openjdk:17
ADD target/efolder-backend.jar efolder-backend.jar
EXPOSE 8080
ENTRYPOINT ["java", ".jar", "efolder-backend.jar"]
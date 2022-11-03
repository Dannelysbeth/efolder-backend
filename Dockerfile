#FROM openjdk:17
#ADD target/efolder-0.0.1-SNAPSHOT.jar efolder-backend.jar
#EXPOSE 8080
#ENTRYPOINT ["java", "-jar", "efolder-backend.jar"]

#FROM openjdk:17-jdk-alpine
#ARG JAR_FILE=target/efolder-backend.jar
#COPY ${JAR_FILE} efolder-backend.jar
#ENTRYPOINT ["java","-jar","/efolder-backend.jar"]

FROM openjdk:17
COPY ./target/efolder-0.0.1-SNAPSHOT.jar efolder-0.0.1-SNAPSHOT.jar
CMD ["java","-jar","efolder-0.0.1-SNAPSHOT.jar"]
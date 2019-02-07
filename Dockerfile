FROM openjdk:8-jdk-alpine
LABEL maintainer="ag.shubham94@gmail.com"
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=target/user-tasks-mgmt-1.0.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file 
ENTRYPOINT ["java","-jar","/app.jar"]
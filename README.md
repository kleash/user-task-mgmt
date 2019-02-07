# user-task-mgmt

## Overview
This application allows a user to securely maintain a TO-DO list.

## Technical Stack
 - Java 8
 - Spring Boot
 - Spring Security
 - Thymeleaf
 - Spring JPA
 - Mockito
 - h2 DB

## Security considerations
The application uses basic authentication mechanism to secure the access of private webpages.

### How to build and deploy the application to Docker

Make sure maven and java is already installed in your workspace.
Go to root directory of cloned folder (ypur path should have pom.xml)

```sh
mvn clean package
docker build -t user-task-mgmt .
docker run -d -p 8080:8080 user-task-mgmt
```

The above commands will build the application, create a docker image from the JAR and start the docker container.

You can access the application at ```localhost:8080```


### Login details for users

You can user following users for testing:

* user1:password
* user2:password

### Anything extra you would have done give more time
* Create registration functionality so that there is no need to hardcode users
* Better UI
* More comprehensive Unit and Integration Tests
* Docker compose with separate application and database containers

FROM openjdk:17-jdk-alpine
LABEL maintainer="synclab.it"
ADD target/challenginatorUserService-0.0.1-SNAPSHOT.jar challenginator_servicebe_user.jar
ENTRYPOINT ["java","-jar","challenginator_servicebe_user.jar"]
EXPOSE 8080
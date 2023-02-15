FROM openjdk:11
LABEL maintainer="Grave Site Management"
ADD target/MyProject-0.0.1-SNAPSHOT.jar MyProject.jar
ENTRYPOINT ["java", "-jar", "MyProject.jar"]

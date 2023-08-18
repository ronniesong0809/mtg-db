FROM amazoncorretto:11-alpine-jdk

MAINTAINER ronniesong0809@gmail.com
VOLUME /tmp
COPY build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]

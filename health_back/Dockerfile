FROM openjdk:11-jre-slim

MAINTAINER timmy

ARG JAR_FILE=health_back-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILES_ACTIVE=pro
ENV TZ=Asia/Taipei
ENV JAVA_OPTS="-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"

RUN mkdir -p /usr/app
WORKDIR /usr/app

ADD ${JAR_FILE} /usr/app/health_back.jar

EXPOSE 8082:8082

CMD java ${JAVA_OPTS} -jar /usr/app/health_back.jar

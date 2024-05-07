FROM openjdk:11-jre-slim

MAINTAINER timmy

ARG JAR_FILE=health_provider-0.0.1-SNAPSHOT.jar

ENV SPRING_PROFILES_ACTIVE=pro
RUN mkdir -p /usr/app
WORKDIR /usr/app

ENV TZ=Asia/Taipei
ENV JAVA_OPTS="-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}"

COPY ./target/${JAR_FILE} /usr/app/health_provider.jar

EXPOSE 81

CMD java ${JAVA_OPTS} -jar /usr/app/health_provider.jar

FROM maven:3-alpine as builder
WORKDIR /root



RUN mvn clean package

FROM tomcat:11-

EXPOSE 8080
COPY builder:target/Service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/Service.war


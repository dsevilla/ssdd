ARG JAVA_VERSION=17
FROM tomcat:11-jdk${JAVA_VERSION}

EXPOSE 8080
COPY es.um.sisdist.RestTest/target/es.um.sisdist.RestTest-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/Service.war

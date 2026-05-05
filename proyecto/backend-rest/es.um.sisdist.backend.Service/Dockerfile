ARG JAVA_VERSION=17
FROM tomcat:11-jre${JAVA_VERSION}

EXPOSE 8080
COPY target/Service-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/Service.war

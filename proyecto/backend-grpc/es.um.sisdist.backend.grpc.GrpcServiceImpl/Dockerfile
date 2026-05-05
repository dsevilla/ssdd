ARG JAVA_VERSION=17
FROM eclipse-temurin:${JAVA_VERSION}-jre-noble

COPY target/GrpcServiceImpl-0.0.1-SNAPSHOT.jar /root

WORKDIR /root

EXPOSE 50051

CMD ["java", "-jar", "GrpcServiceImpl-0.0.1-SNAPSHOT.jar"]

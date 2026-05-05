JAVA_VERSION ?= 17

backend-grpc:
	(cd es.um.sisdist.backend.grpc.GrpcService \
		&& docker run \
		   -v $$(pwd):/var/maven \
		   -v ~/.m2:/var/maven/.m2 -t \
		   -w /var/maven \
		   --rm -u $$(id -u):$$(id -g) \
		   -e MAVEN_CONFIG=/var/maven/.m2 \
		   -e JAVA_VERSION=$(JAVA_VERSION) \
		   maven:3-eclipse-temurin-$(JAVA_VERSION) \
		   mvn -B -e -C -Duser.home=/var/maven -DskipTests clean install)

	(cd es.um.sisdist.backend.grpc.GrpcServiceImpl \
		&& docker run \
		   -v $$(pwd):/var/maven \
		   -v ~/.m2:/var/maven/.m2 -t \
		   -w /var/maven \
		   --rm -u $$(id -u):$$(id -g) \
		   -e MAVEN_CONFIG=/var/maven/.m2 \
		   -e JAVA_VERSION=$(JAVA_VERSION) \
		   maven:3-eclipse-temurin-$(JAVA_VERSION) \
		   mvn -B -e -C -T 4 -Duser.home=/var/maven -DskipTests clean package)

containers:
	(cd es.um.sisdist.backend.grpc.GrpcServiceImpl \
	      	&& docker build --build-arg JAVA_VERSION=$(JAVA_VERSION) -t dsevilla/ssdd-backend-grpc:1.0 .)

clean:
	docker rmi -f dsevilla/ssdd-backend-grpc:1.0

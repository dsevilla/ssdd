backend-rest:
	(cd es.um.sisdist.backend.Service \
		&& docker run \
		   -v $$(pwd):/var/maven \
		   -v ~/.m2:/var/maven/.m2 -t \
		   -w /var/maven \
		   --rm -u $$(id -u):$$(id -g) \
		   -e MAVEN_CONFIG=/var/maven/.m2 maven:3-eclipse-temurin-17 \
		   mvn -B -e -C -T 4 -Duser.home=/var/maven -DskipTests clean package)

containers:
	(cd es.um.sisdist.backend.Service \
		&& docker build -t dsevilla/ssdd-backend-rest:1.0 .)

clean:
	docker rmi -f dsevilla/ssdd-backend-rest:1.0

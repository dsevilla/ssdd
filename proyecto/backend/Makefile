backend:
	(cd es.um.sisdist.backend.dao \
		&& mvn clean install)
build:
	(cd es.um.sisdist.backend.dao \
		&& docker run \
		   -v $$(pwd):/var/maven \
		   -v ~/.m2:/var/maven/.m2 -ti \
		   -w /var/maven \
		   --rm -u $$(id -u):$$(id -g) \
		   -e MAVEN_CONFIG=/var/maven/.m2 maven:3-eclipse-temurin-17-alpine \
		   mvn -Duser.home=/var/maven -DskipTests clean install)


containers:

clean:
	echo "backend cleans nothing."

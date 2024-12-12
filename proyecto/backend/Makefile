backend:
	(cd es.um.sisdist.backend.dao \
		&& docker run \
		   -v $$(pwd):/var/maven \
		   -v ~/.m2:/var/maven/.m2 -t \
		   -w /var/maven \
		   --rm -u $$(id -u):$$(id -g) \
		   -e MAVEN_CONFIG=/var/maven/.m2 maven:3-eclipse-temurin-17 \
		   mvn -B -e -C -T 4 -Duser.home=/var/maven -DskipTests clean install)

containers:

clean:
	echo "backend cleans nothing."

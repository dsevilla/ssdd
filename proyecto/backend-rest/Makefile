backend-rest:
	(cd es.um.sisdist.backend.Service \
		&& mvn -DskipTests clean package)

containers:
	(cd es.um.sisdist.backend.Service \
		&& docker build -t dsevilla/ssdd-backend-rest:1.0 .)

clean:
	docker rmi -f dsevilla/ssdd-backend-rest:1.0

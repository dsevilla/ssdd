backend-rest:
	(cd es.um.sisdist.videofaces.backend.Service \
		&& mvn clean package -DskipTests)

containers:
	(cd es.um.sisdist.videofaces.backend.Service \
		&& docker build -t dsevilla/videofaces-backend-rest:1.0 .)

clean:
	docker rmi -f dsevilla/videofaces-backend-rest:1.0

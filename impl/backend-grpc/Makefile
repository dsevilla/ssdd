backend-grpc:
	(cd es.um.sisdist.videofaces.backend.grpc.GrpcService \
		&& mvn clean install)
	(cd es.um.sisdist.videofaces.backend.grpc.GrpcServiceImpl \
	      	&& mvn clean package -s mvn-settings.xml)

containers:
	(cd es.um.sisdist.videofaces.backend.grpc.GrpcServiceImpl \
	      	&& docker build -t dsevilla/videofaces-backend-grpc:1.0 .)

clean:
	docker rmi -f dsevilla/videofaces-backend-grpc:1.0 

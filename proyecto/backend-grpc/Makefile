backend-grpc:
	(cd es.um.sisdist.backend.grpc.GrpcService \
		&& mvn clean install)
	(cd es.um.sisdist.backend.grpc.GrpcServiceImpl \
	      	&& mvn clean package)

containers:
	(cd es.um.sisdist.backend.grpc.GrpcServiceImpl \
	      	&& docker build -t dsevilla/ssdd-backend-grpc:1.0 .)

clean:
	docker rmi -f dsevilla/ssdd-backend-grpc:1.0 

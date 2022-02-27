#! /usr/bin/env make -f
all: make-containers

make-containers: frontend backend backend-grpc backend-rest

frontend:
	$(MAKE) -C $@

backend:
	$(MAKE) -C $@

backend-grpc:
	$(MAKE) -C $@

backend-rest:
	$(MAKE) -C $@

.PHONY: all make-containers frontend backend backend-grpc backend-rest

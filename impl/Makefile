#! /usr/bin/env make -f
SUBDIRS = frontend backend backend-grpc backend-rest

all: make-containers

make-containers: $(SUBDIRS)

frontend:
	$(MAKE) -C $@

backend:
	$(MAKE) -C $@

backend-grpc:
	$(MAKE) -C $@

backend-rest:
	$(MAKE) -C $@

clean:
	for i in $(SUBDIRS); do \
		$(MAKE) -C $$i $@; \
	done

.PHONY: all make-containers frontend backend backend-grpc backend-rest clean

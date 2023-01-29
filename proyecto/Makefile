#! /usr/bin/env make -f
SUBDIRS = frontend backend backend-grpc backend-rest

all: $(SUBDIRS)

run-devel:
	docker-compose -f docker-compose-devel.yml up

containers: $(SUBDIRS)
	for i in $(SUBDIRS); do \
		$(MAKE) -C $$i $@; \
	done

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

.PHONY: all run-devel containers frontend backend backend-grpc backend-rest clean

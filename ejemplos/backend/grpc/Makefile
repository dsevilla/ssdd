MAVEN_IMAGE := maven:3-eclipse-temurin-17
WORKDIR := /workspace
PROJECT_DIR := $(WORKDIR)/es.um.sisdist.grpc-test1

.PHONY: build clean test

build:
	docker run --rm \
		-v $(PWD):$(WORKDIR) \
		-w $(PROJECT_DIR) \
		$(MAVEN_IMAGE) \
		mvn -DskipTests package

clean:
	docker run --rm \
		-v $(PWD):$(WORKDIR) \
		-w $(PROJECT_DIR) \
		$(MAVEN_IMAGE) \
		mvn clean

test:
	docker run --rm \
		-v $(PWD):$(WORKDIR) \
		-w $(PROJECT_DIR) \
		$(MAVEN_IMAGE) \
		mvn test

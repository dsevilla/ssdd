JAVA_VERSION ?= 17
MAVEN_IMAGE := maven:3-eclipse-temurin-$(JAVA_VERSION)
WORKDIR := /workspace
PROJECT_DIR := $(WORKDIR)/es.um.sisdist.grpc-test1

.PHONY: build clean test

build:
	docker run --rm \
		-v $(PWD):$(WORKDIR) \
		-w $(PROJECT_DIR) \
		-e JAVA_VERSION=$(JAVA_VERSION) \
		$(MAVEN_IMAGE) \
		mvn -DskipTests package

clean:
	docker run --rm \
		-v $(PWD):$(WORKDIR) \
		-w $(PROJECT_DIR) \
		-e JAVA_VERSION=$(JAVA_VERSION) \
		$(MAVEN_IMAGE) \
		mvn clean

test:
	docker run --rm \
		-v $(PWD):$(WORKDIR) \
		-w $(PROJECT_DIR) \
		-e JAVA_VERSION=$(JAVA_VERSION) \
		$(MAVEN_IMAGE) \
		mvn test

VERSION:=1.0

all:
	docker build -t dsevilla/ssdd-llamachat:$(VERSION) .
	docker build -f Dockerfile-dummy -t dsevilla/ssdd-llamachat-dummy:$(VERSION) .

clean:
	docker rmi -f dsevilla/ssdd-llamachat:$(VERSION)
	docker rmi -f dsevilla/ssdd-llamachat-dummy:$(VERSION)

.PHONY: all clean

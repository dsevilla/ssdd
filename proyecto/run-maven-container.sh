#! /bin/sh

docker run -ti -v `pwd`:/root/maven maven:3-openjdk-17-slim bash

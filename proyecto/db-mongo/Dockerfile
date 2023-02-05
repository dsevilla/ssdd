FROM mongo:latest

ENV MONGO_INITDB_ROOT_USERNAME=root \
    MONGO_INITDB_ROOT_PASSWORD=root \
    MONGO_INITDB_DATABASE=ssdd

ADD initialize.js /docker-entrypoint-initdb.d

EXPOSE 27017


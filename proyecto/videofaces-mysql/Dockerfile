FROM mysql:8

ENV MYSQL_DATABASE=videofaces \
    MYSQL_ROOT_PASSWORD=root

ADD schema.sql /docker-entrypoint-initdb.d

EXPOSE 3306


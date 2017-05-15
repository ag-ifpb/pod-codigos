#!/bin/bash

#atualizar o jar
mvn package -DskipTests=true

#construir o container
docker build -t java-rmi-client .

#java-rmi-server-running é o nome do container server
#host.docker é o alias que vai servir como host
docker run -it --rm --name java-rmi-client-running \
  --env RMI_TYPE=client --env RMI_SERVER_HOST=host.docker \
  --link=java-rmi-server-running:host.docker \
  java-rmi-client

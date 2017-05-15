#!/bin/bash

#atualizar o jar
mvn package -DskipTests=true

#construir o container
docker build -t java-rmi-server .

#java-rmi-server-running é o nome do container a ser ligado ao client
docker run -it --rm --env RMI_TYPE=server\
	--name java-rmi-server-running\
	java-rmi-server 
	
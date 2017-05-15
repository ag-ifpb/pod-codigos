#Created by Ari Garcia
FROM java:8-jdk

#copy jar application to /opt in container
COPY target/ag-rmi-indocker-0.0.1-SNAPSHOT.jar /opt

#set work dir
WORKDIR /opt

#final command
CMD java -cp ag-rmi-indocker-0.0.1-SNAPSHOT.jar ag.ifpb.rmi.indocker.App $RMI_TYPE $RMI_SERVER_HOST
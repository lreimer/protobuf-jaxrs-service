FROM qaware/zulu-centos-payara-micro:8u192-5.183

COPY build/libs/protobuf-jaxrs-service.war /opt/payara/deployments/

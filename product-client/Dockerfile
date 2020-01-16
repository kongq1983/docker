FROM java:8
MAINTAINER kong
ADD target/product-client-1.0-SNAPSHOT.jar productclient.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","productclient.jar"]
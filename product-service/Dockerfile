FROM java:8
MAINTAINER kong
ADD target/product-service-1.0-SNAPSHOT.jar productservice.jar
EXPOSE 10000
ENTRYPOINT ["java","-jar","productservice.jar"]
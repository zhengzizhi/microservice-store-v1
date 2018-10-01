# Online Store Web

The online store web application is responsible for serving as the main user interface of the online store backend. Users are able to register, login, and order products using this interface.

## How to get catalog without authorization
http://localhost:8787/api/catalog/v1/catalog

## How to run this project in localhost
cd /opt/coding/microservice-store-v1/discovery-service && mvn clean && mvn install && mvn spring-boot:run
cd /opt/coding/microservice-store-v1/user-service && mvn clean && mvn install && mvn spring-boot:run
cd /opt/coding/microservice-store-v1/online-store-web && mvn clean && mvn install && mvn spring-boot:run
cd /opt/coding/microservice-store-v1/edge-service && mvn clean && mvn install && mvn spring-boot:run
cd /opt/coding/microservice-store-v1/inventory-service && mvn clean && mvn install && mvn spring-boot:run
cd /opt/coding/microservice-store-v1/catalog-service && mvn clean && mvn install && mvn spring-boot:run

http://localhost:8787/api/catalog/v1/catalog

# MicroServices_Fault_Tolerance
MicroServices Level 2 - Fault Tolerance and Resilience using Hystrix 

This Project uses an application for Service Discovery and Netflix Hystrix for implementing below patterns
1. Circuit Breaker Pattern
2. Bulk Head Pattern

Eureka Service Discovery Server (runs on port 8761)
And three microservices which are Eureka Clients

Movie Catalog Service (runs on port 8081)
Movie Ratings Data Service (runs on port 8082)
Movie Info Service (runs on port 8083)
Hystrix Dashboard(runs on port 8081)

Movie Catalog Service discovers 2 other microservices and communicates with them using RestTemplate
Fallback methods are added for both the Microservice calls using Hystrix

Movie Info Service commuicates with an external service https://www.themoviedb.org/ using an API key in RestTemplate

<img width="300" alt="API Architecture" src="https://github.com/dineschandgr/MicroServices_Fault_Tolerance/blob/master/API_Architecture.jpg">




Requirements :

#add pom.xml file You need to add pom.xml with the maven dependencies

#Create AWS RDS MySQL DB or a local MYSQL DB

#To Create a RDS MYSQL Database

Create an RDS mysql 1st database named MovieCatalogDB (only 1 DB can be created from AWS RDS Console per service)
Set the RDS DB to public accessibility to YES (just for testing purposes. in real projects its in a private VPC and can be used only from a authorized VPC)
Copy the DB endpoint url, user name and pwd
Open the Port 3306 for your local machine IP
#Use the DB tool Sqlectron

Connect to the first mysql DB MovieCatalogDB creating from AWS RDS console. Create a table named movie_catalogue and insert sample data
Create a 2nd database named MovieInfoDB. Create a table named movie_info and insert sample data
Create a 3rd database named RatingsDataDB. Create a table named rating_info and insert sample data

#Update the DB credentials and Sever Port in application.properties file for every microservices project

#include management.endpoints.web.exposure.include=hystrix.stream for hystrix

#Create an api key from themoviedb site and include in application.properties of Movie Info Service

1. Start the Discovery Application Server Application first which runs on port 8761
2. Runs all three microservices which are registered which the Eureka Server
3. Access http://localhost:8761 to see Eureka Discovery Server Dashboard and verify that all 3 microservices are up and registered
4. Access http://localhost:8081/hystrix to view the Hystrix Dashboard
5. Access http://localhost:8081/catalog/user_name to access Movie Catalog Microservice
6. Access http://localhost:8082/movies/movie_id to access Movie Info Service
7. Access http://localhost:8083/ratingsdata/user/user_name to access Ratings Data Service
8. Microservice running on port 8081 consumes the services running on port 8082 and port 8083



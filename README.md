# webshop
Shopping website 

## APIs
+ Authentication with Spring security 
+ USER 
    + GET `/v1/api/users` : Get users
    + GET `/v1/api/users/id` : Get user by user id 
    + DELETE `v1/api/users/id` : Delete user by id
    
+ PRODUCT 
    + `/v1/api/products?page=2&limit=20` : Get products with pagination 
    
    
    
    
    
## Using
+ Spring boot
+ Lombok
+ Spring security 
+ PostgreSQL 
+ Swagger 
    +  `localhost:port/v2/api-docs`
    + Swagger UI : `localhost:port/swagger-ui.html`
+ spring-data
+ Spring session
+ Redis 
+ SSL enabled (local)
+ Messaging with RabbitMQ(later)
+ i18n
+ Custom validator class 
+ Custom exception handler class 
+ JUnit 




### Document project
+ API version 


## Run the project 

+ How to build 
``
+ Run unit test class 

+ Docker 
    + Build Docker image : to build Docker image locally when you run maven command, which builds the application jar and uses src/docker/Dockerfile to build a container 
        + `mvn clean webshop:buildDockerImage`
    + Start Docker container 
    + Stop Docker container 
        + `docker ps`
        + `docker stop <container-id>`




## Screenshot demos 

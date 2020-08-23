# Spring Boot MySQL JWT Authentication
#### This project contains boilerplate code for jwt based authentication

|API endpoint     | request-method | request-body |
|-----------------|--------|--------|
| `/authenticate` | `POST` | <code> { "username": "johndoe98", "password":"your^Pass#123" } </code> |
| `/register`     | `POST` | <code> {"username": "johndoe98", "firstName": "john", "lastName": "doe", "password":"your^Pass#123", "email":"vardhansahani@domain.com", "homeAddress":"Mumbai, Maharashtra.", "officeAddress":"Thane, Maharashtra.", "contact": "9999999999" } </code> |
| `/user`         | `POST` | <code> {"Authorization": "Bearer \<jwt-token-from-authentication\>"} </code>  |

<br /><br />
#### Steps before deploying app
- Start Database server.
- Configure [applicaton.properties](https://github.com/apache15/AuthSpringBootMySQL/blob/master/src/main/resources/application.properties)
    ```
    jwt.secret=authjwt
    spring.datasource.url=jdbc:mysql://localhost:3306/userdb?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=true
    spring.datasource.username=<mysql-username>
    spring.datasource.password=<mysql-password>
    ```

- Clone [current repository](https://github.com/apache15/AuthSpringBootMySQL/)
- cd to root of repository
- Run command \
    ```sh
    $ cd 
    $ sudo ./mvnw clean spring-boot:run
    ```
    
> Similarly it also provides functionality for checking username if already exists and update user details.

# Spring Boot MySQL JWT Authentication

|API endpoint     | request-method | request-body |
|-----------------|--------|--------|
| `/authenticate` | POST | <code> { "username": "johndoe98", "password":"your^Pass#123" } </code> |
| `/register`     | POST | <code> {"username": "johndoe98", "firstName": "john", "lastName": "doe", "password":"your^Pass#123", "email":"vardhansahani@domain.com", "homeAddress":"Mumbai, Maharashtra.", "officeAddress":"Thane, Maharashtra.", "contact": "9999999999" } </code> |
| `/user`         | POST | <code> {"Authorization": "Bearer \<jwt-token-from-authentication\>"} </code>  |

- Start Database server.
- Configure [applicaton.properties](https://github.com/apache15/AuthSpringBootMySQL/blob/master/src/main/resources/application.properties)
    <pre># set your secret key
    jwt.secret=authjwt
    # set your jdbc url
    spring.datasource.url=jdbc:mysql://localhost:3306/userdb?createDatabaseIfNotExist=true&autoReconnect=true&useSSL=false
    spring.datasource.username=root
    spring.datasource.password=
    spring.datasource.platform=mysql
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true</pre>

- Clone [current repository](https://github.com/apache15/AuthSpringBootMySQL/)
- cd to root of repository
- Run command
    ```
    $ ./mvnw clean spring-boot:run
    ```
    
> Similarly it also provides functionality for checking username if already exists and update user details.

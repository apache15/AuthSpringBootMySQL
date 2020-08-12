# Spring Boot MySQL JWT Authentication

|API endpoint     | request-method | request-body |
|-----------------|--------|--------|
| `/authenticate` | POST | <code> ` { "username": "johndoe98", "password":"your^Pass#123" } ` </code> |
| `/register`     | POST | `{"username": "johndoe98", "firstName": "john", "lastName": "doe", "password":"your^Pass#123", "email":"vardhansahani@domain.com", "homeAddress":"Mumbai, Maharashtra.", "officeAddress":"Thane, Maharashtra.", "contact": "9999999999" }`|
| `/user`         | POST | `{"Authorization": "Bearer <jwt-token-from-authentication>"}` |

- Start Database server.
- Configure [applicaton.properties](https://github.com/apache15/AuthSpringBootMySQL/blob/master/src/main/resources/application.properties)
- Clone [current repository](https://github.com/apache15/AuthSpringBootMySQL/)
- cd to root of repository
- Run command
    ```
    $ ./mvnw clean spring-boot:run
    ```
    
> Similarly it also provides functionality for checking username if already exists and update user details.

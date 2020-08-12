## Spring Boot MySQL JWT Auth

|routes           | method | reuest |
|-----------------|--------|--------|
| `/authenticate` | POST | `{ "username": "johndoe98", "password":"your^Pass#123" }`|
| `/register`     | POST | `{"username": "johndoe98", "firstName": "john", "lastName": "doe", "password":"your^Pass#123", "email":"vardhansahani@domain.com", "homeAddress":"Mumbai, Maharashtra.", "officeAddress":"Thane, Maharashtra.", "contact": "9999999999" }`|
| `/user`         | POST | `{"Authorization": "Bearer <jwt-token-from-authentication>"}` |

- Start MySQL
- Create database with name springjwt
- Clone [current repository](https://github.com/apache15/AuthSpringBootMySQL/)
- cd to root of cloned repository and Run
    ```
    $ ./mvnw clean spring-boot:run
    ```
- To register, open Postman Client and access `POST \ http://127.0.0.1:8080/register`
    ```
    {
        "username": "johndoe",
        "password": "imjohn",
        "email": "johndoe@mail.com",
        "contact": "9999999999"
    }
    ```
- To authenticate (log in), access `POST \ http://127.0.0.1:8080/authenticate`
    ```
    {
        "username": "johndoe",
        "password": "imjohn"
    }
    ```
- To access logged in user details `POST \ http://127.0.0.1:8080/user`
    ```
    {}
    ```
> Similarly it also provides functionality for checking username if already exists and update user details.

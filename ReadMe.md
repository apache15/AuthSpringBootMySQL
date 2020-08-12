## Spring Boot MySQL JWT Auth
#### Steps to be followed

- Start MySQL
- Create database with name springjwt
- Clone repository ___https://github.com/apache15/spring-boot-mysql-jwt-auth___
- cd to root of cloned repository
- Run
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

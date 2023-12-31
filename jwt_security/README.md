# API: JWT with SpringBoot Security
In this project, I will demonstrate how to integrate JWT and SpringBoot Security to do CRUD by the user role.

## Setting
    - Create PostgreSQL database named with jwt_security
    - OR change the config setting application.yml file
![Screenshot 2023-10-31 at 23.14.52.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.14.52.png)

## Introduction - function
## Generate user token (POST)
    - user will retrieve their token and know the expiration
    - Role: USER/ADMIN
![Screenshot 2023-10-31 at 23.41.28.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.41.28.png)
## Get all user (GET)
    - use ADMIN token, otherwise you can't review all the user
![Screenshot 2023-10-31 at 23.29.42.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.29.42.png)

## Get specific user information (GET)
    - pass your email and token
    - if the email not matches with the token, you will not get the result
![Screenshot 2023-10-31 at 23.31.47.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.31.47.png)

## Update user (PUT)
    - pass your email and token
    - only allow to update name and password
![Screenshot 2023-10-31 at 23.41.57.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.41.57.png)

## Delete user (DELETE)
    - pass your email and token
![Screenshot 2023-10-31 at 23.35.46.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.35.46.png)

## OpenAPI/Swagger UI
    http://localhost:8080/swagger-ui.html
![Screenshot 2023-11-08 at 22.59.56.png](README_Picture%2FScreenshot%202023-11-08%20at%2022.59.56.png)

## Other
    - Clone the DEV version to use partial function in the front-end page
    - http://localhost:8080/
![Screenshot 2023-10-31 at 23.43.09.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.43.09.png)
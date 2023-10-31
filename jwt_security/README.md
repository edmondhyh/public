# API: JWT with SpringBoot Security
In this project, I will demonstrate how to integrate JWT and SpringBoot Security to do CRUD by the user role.

## Setting
    - Create PostgreSQL database named with <span style="color: orange;">jwt_security</span>
    - OR change the config setting <span style="color: orange;">application.yml</span> file
![Screenshot 2023-10-31 at 23.14.52.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.14.52.png)

## Introduction - function
## Generate user token (POST)
    - user will retrieve their token and know the expiration
    - Role: USER/ADMIN
![Screenshot 2023-10-31 at 23.23.58.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fw9%2F4q37w8rs4txcy89mf9gfnjvh0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_EKWFKB%2FScreenshot%202023-10-31%20at%2023.23.58.png)

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
![Screenshot 2023-10-31 at 23.34.15.png](..%2F..%2F..%2F..%2F..%2F..%2Fvar%2Ffolders%2Fw9%2F4q37w8rs4txcy89mf9gfnjvh0000gn%2FT%2FTemporaryItems%2FNSIRD_screencaptureui_H5vl4L%2FScreenshot%202023-10-31%20at%2023.34.15.png)

## Delete user (DELETE)
    - pass your email and token
![Screenshot 2023-10-31 at 23.35.46.png](README_Picture%2FScreenshot%202023-10-31%20at%2023.35.46.png)

## Other

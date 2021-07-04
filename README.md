<h1 align="center">
  <br>
  <a><img src="https://github.com/lekhana3003/WalPP/blob/main/docs/images/wallet-image.png" alt="Wallet"></a>
  <br>
  WalPP
  <br>
</h1>

<p align="center"><b>Wal</b>let <b>P</b>lug in and <b>P</b>lay Service</p>

<p align="center">
    <a alt="Java">
        <img src="https://img.shields.io/badge/Java-v1.8-orange.svg" />
    </a>
    <a alt="Spring Boot">
        <img src="https://img.shields.io/badge/Spring%20Boot-v2.3.3-brightgreen.svg" />
    </a> 
    <a alt="Docker">
        <img src="https://img.shields.io/badge/Docker-v19-yellowgreen.svg" />
    </a>
    <a alt="Dependencies">
        <img src="https://img.shields.io/badge/dependencies-up%20to%20date-brightgreen.svg" />
    </a>
    <a alt="Contributions">
        <img src="https://img.shields.io/badge/contributions-welcome-orange.svg" />
    </a>
    <a alt="License">
        <img src="https://img.shields.io/badge/license-MIT-blue.svg" />
    </a>
</p>

## Table of Contents ##
1. [Application](#Application)
2. [Database Schema](#Database-Schema)
3. [Technology](#Technology)
4. [Application Structure](#Application-Structure)
5. [Run Locally](#Running-the-server-locally)
6. [Run Insider Docker](#Running-the-server-in-Docker-Container)
7. [API Documentation](#API-Documentation)
8. [Contributor](#Contributor)
9. [License](#License)

## Application ##
A java based Wallet service that can be easily integrated to any application or service. The service provides features such as 
### 
- Easy User Integration  
    User Account can be created in the wallet using just user id and the intial wallet balance to be maintained.
- Database  
    Application utilizes benefits of SQL database.
- Independence  
    The service can be run as an independent service as docker container or jar application
- Balance  
    Seperate endpoint to fetch the user balance at any point
- Transactions Filter  
     Transaction history can be fetched based on time range, user, type of transaction, and transaction id

The application is running on localhost and available at endpoint, on port 8080  
```
http://localhost:8080
```

Some of the important api endpoints are as follows :

- http://localhost:8080/createAccountWallet (HTTP:POST)
- http://localhost:8080/getBalance (HTTP:POST)
- http://localhost:8080/credit (HTTP:POST)
- http://localhost:8080/debit (HTTP:POST)
- http://localhost:8080/getTransactions (HTTP:POST)
  

## Database Schema ##
The current schema looks as follows:

<img src="https://github.com/lekhana3003/WalPP/blob/main/docs/images/database.png" alt="spring boot"></a>


- The user keeps the details of user along with balance and intial balance of the user. The user id given is the unique key of the table.
- The transactions table maintains the details about party name, transaction id, transaction details, amount of transaction and type of transaction type.
  
## Technology ##
Following libraries were used during the development of this starter kit :

- **Spring Boot** - Server side framework
- **Docker** - Containerizing framework
- **SQL** - SQL database 
- **Swagger** - API documentation


## Application Structure ##
Every API has payload and status in the response body,
- For Successful response
    ```
        {
          "status": "string",
          "payload": {}
        }
     ```
- For error response
    ```
        {
          "status": "string",
          "errors": "string"
        }
     ```
Guidelines to use the service, 
- Create User Account,  
    By making the POST request to the endpoint
    http://localhost:8080/createAccountWallet
    
    ```
    {
      "intialBalance": int,
      "userId": int
    }
    ```
   The user id is considered to be unique, and the endpoint user is assumed to maintain unique values. 
- Credit Transaction  
    By making the POST request to the endpoint  
        http://localhost:8080/credit
    ```
   {
     "amount": int,
     "party_name": "string",
     "transactionDetails": "string",
     "transactionID": "string",
     "userId": int
   }
     ```
 - Debit Transaction  
     By making the POST request to the endpoint  
         http://localhost:8080/debit
     ```
    {
      "amount": int,
      "party_name": "string",
      "transactionDetails": "string",
      "transactionID": "string",
      "userId": int
    }
      ```
 - Balance  
     By making the POST request to the endpoint  
         http://localhost:8080/getBalance
      ```
         {
           "userId": int
         }
      ```
  - Transaction History  
      By making the POST request to the endpoint  
     http://localhost:8080/getTransactions  
      This api can be used to apply filters such as,
      - transaction id - to get a transaction of the given transaction id
      - aftertimestamp - to get all list of transactions after the given timestamp
      - beforetimestamp - to get all list of transactions before the given timestamp
      - user id - to get all list of transactions of the given users  
      
      ```
     {
       "amount": int,
       "party_name": "string",
       "transactionDetails": "string",
       "transactionID": "string",
       "userId": int
     }
      
       ```
## Running the server locally ##
To be able to run this Spring Boot app you will need to first build it. To build and package a wallet service into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.

```
maven package
```
or you can also use

```
mvn install
```

To run the Spring Boot app from a command line in a Terminal window you can you the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.

```
java -jar target/WalPP-0.0.1-SNAPSHOT.jar
```

You can also use Maven plugin to run the app. Use the below example to run your Spring Boot app with Maven plugin :

```
mvn spring-boot:run
```

If you do not have a SQL instance running and still just want to create the JAR, then please use the following command:

```
mvn install -DskipTests
```

This will skip the test cases and won't check the availability of a mongodb instance and allow you to create the JAR.

You can follow any/all of the above commands, or simply use the run configuration provided by your favorite IDE and run/debug the app from there for development purposes. Once the server is setup you should be able to access the admin interface at the following URL :

http://localhost:8080

And the REST APIs can be accessed over the following base-path :

http://localhost:8080/api/

## Running the server in Docker Container ##
##### Docker #####
Command to build the container :

```
docker build -t walppimage --network=host .
```

Command to run the container :

```
docker run -p 8080:8080 walppimage
```

Please **note** when you build the container image and if SQL is running locally on your system, you will need to provide your system's IP address (or cloud hosted database's IP) in the application.properties file to be able to connect to the database from within the container.

##### Docker Compose #####
Another alternative to run the application is to use the docker-compose.yml file and utility which also contains SQL. To build the application using docker-compose simply execute the following command :
```
docker-compose build
```

And to run the application, please execute the following command :
```
docker-compose up
```

## API Documentation ##
API documentation is implemented using the Swagger, the swagger documentation allows for users to test out the API before integration.  
The API documentation is available at, 
http://localhost:8080/swagger-ui.html

It is presented well structured UI which has two specs :

1. UserController
    - /createAccountWallet
2. TransactionController
    - /getBalance
    - /credit
    - /debit
    - /getTransactions


<p align="center">
    <b>End Points</b><br>
    <br>
    <img width="600" src="https://github.com/lekhana3003/WalPP/blob/main/docs/images/all-end-points.png">
</p>

## Author ##

* Author: [Lekhana Ganji](https://in.linkedin.com/in/lekhanag3003)
* Email: <a href="mailto:lekhanag.3003@gmail.com">lekhanag.3003@gmail.com </a>
## License ##
```
   Copyright 2021 Lekhana Ganji
   
   Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, 
   including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
   and to permit persons to whom the Software is furnished to do so,subject to the following conditions:
   The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. 
   IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
   ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```

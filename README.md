# Spring Boot, Spring Security,  JPA, and Docker

## Prerequisites:
* Docker
* JDK 17
* Maven 3.*


## Install and run the project
1. download/clone the project
2. Create docker image from following command
* `docker build -t warehouse:1.0 .`
3. Run the docker-compose using the following command
* `docker-compose up -d`


4. Let's send the process deal request.

   ``` 
   curl -X 'POST' \
        'http://localhost:8080/deals/processDeal' \
        -H 'accept: */*' \
        -H 'Content-Type: application/json' \
        -d '{
        "id": "010d961a-d6fc-40d2-8cfd-350b6ff1e34f",
        "fromCurrencyISOCode": "SAR",
        "toCurrencyISOCode": "PKR",
        "dealTime": "2023-11-25T10:57:00",
        "amount": 100.50
        
        }' 
   ```


> **Note:** Dockerfile and docker-compose.yml files are in project root dir.

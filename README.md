# Money transfer RESTful application 

This is a simple money transfer RESTful application.
Application is based on:
  - Spark framework
  - JOOQ
  - Google Guice
### How to install
```sh
$ mvn clean install
```
### How to run
```sh
$ java -jar uber-jar.jar -p 8080
```

Application will start on port 8080 or whatever port you choose. H2 database tcp server will start automatically on address configured in config.properties file.
TCP mode supports multiple connections so you can connect to database from IDE.
```sh
jdbc:h2:tcp://localhost:9092/~/H2/jooq-h2
```


### REST endpoints
| Http method | path |Comment|
| ------ | ------ |------|
| POST | /customer | Create a customer
| GET | /customer/:id | Get the customer by ID
| GET | /customer/accounts/:accountNumber | Get all customers by account number
| GET | /customer?firstName=TEST_FIRST_NAME&lastName=TEST_LAST_NAME | Get customers by first and last name
| POST | /account | Create account
| POST | /account/addCustomer | Add customer to account
| GET | /account/customers/:customerID | Get all customer's accounts
| GET | /account/:accountNumber | Get account details
| POST | /transaction/transfer | Transfer funds from one account to another
| POST | /transaction/withdraw | Withdraw funds from account
| POST | /transaction/deposit | Deposit funds to account

## Samples of POST queries
http://localhost:8080/customer
```json
{
    "firstName": "John",
    "lastName": "Marley",
    "email": "johnMarley@gmail.com"
 }
```
http://localhost:8080/account
```json
{
    "customerID": 1,
    "account": {
      "accountNumber": "2000",
      "type": "SAVINGS",
      "description": "",
      "balance": 60000,
      "currencyCode": "USD"
    }
}
```
http://localhost:8080/account/addCustomer
```json
{
    "customerID": 1,
    "account": {
      "accountNumber": "1006"
    }
}
```
http://localhost:8080/account/transfer
```json
{
    "amount": 2000,
    "fromAccountNumber": "1001",
    "toAccountNumber": "1004"
}
```
http://localhost:8080/account/withdraw
```json
{
    "amount": 50000,
    "fromAccountNumber": "1001"
}
```
http://localhost:8080/account/deposit
```json
{
    "amount": 50000,
    "toAccountNumber": "1004"
}
```


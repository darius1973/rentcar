
Domain particularities:

 - Customer unique key is cid ( customer id, which can be passport id or card id ).
 - Car data unique key is the license plate.
 - When calculating the lease rate api , a contract containing information about both customer and car is persisted

Persistence is in memory.

Microservices:

 Car data microservice:

 - retrieve car data for one license plate:
 GET http://localhost:7777/cardata/{licensePlate}
 - add car data:
 POST http://localhost:7777/cardata
 with the details in json body, like for example :
   {
       "licensePlate":"12MCV001",
       "model":"Ford K",
       "make":"Ford",
       "co2Emission": 4.50,
       "nbrOfDoors": 3,
       "version": "K",
       "grossPrice": 10000.00
       "netPrice": 6000


   }
 - change car data
  PUT http://localhost:7777/cardata/{licensePlate}
  with the json body of the details that have to change, like
  {
    "grossPrice": 10000.00
  }

 - delete car data
 DEL http://localhost:7777/cardata/{licensePlate}


 Customer microservice:

  - retrieve customer for one customer id:
  GET http://localhost:9988/customer/{cid}

  - add customer:
  POST http://localhost:9988/customer
  with the details in json body, like for example :
    {
        "cid":"007",
        "name":"Bond",
        "street":"DS",
        "houseNumber": "11",
        "zipCode": "11AV",
        "email": "a@bcd.com",
        "phoneMumber": "1234"
        "place": "LDN"

    }

  - change customer details
   PUT http://localhost:9988/customer/{cid}
   with the json body of the details that have to change, like
   {
     "street":"ABC"
   }

  - delete customer data
  DEL http://localhost:9988/customer/{cid}

Lease rate calculator:
  POST http://localhost:9999/lease-rate
  with the json body of contract details, like:
  {
    "cid": "007",
    "licensePlate": "12MVC01",
    "millage": 15000,
    "duration": 3,
    "interestRate": 3.50
  }

 the reply is calculated lease rate.

 Service communication: service-2-service via netflix eureka :

 http://localhost:8761/eureka/

 TODO:
  - add more testing for lease rate api
  - implement security
  - implement termination of contract
  - prevent deleting car data or customer data while a contract is still active
  - documentation ( swagger for spring boot)









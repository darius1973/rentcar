Domain particularities:

 - Customer unique key is cid ( customer id, which can be passport id or card id ).
 - Car data unique key is the license plate.
 - When calculating the lease rate api , a contract containing information about both customer and car is persisted

Persistence is in memory.

Microservices:

 Car data microservice docs:
  http://localhost:7777/swagger-ui.html#/car-data-resource
 
 Customer microservice docs:
  http://localhost:9988/swagger-ui.html#/customer-data-resource

 
 Service communication: service-2-service via netflix eureka :

 http://localhost:8761/eureka/
 
 First service to start it has to be the service-registry (the one holding the netflix eureka server)

 TODO:
  - add more testing for lease rate api
  - implement security
  - implement termination of contract
  - prevent deleting car data or customer data while a contract is still active


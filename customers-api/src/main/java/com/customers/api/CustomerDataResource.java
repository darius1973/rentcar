package com.customers.api;

import domain.CarData;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public  class CustomerDataResource {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customer/{cid}")
    public Customer retrieveCustomer(@PathVariable String  cid) {
        return customerService.retrieveCustomer(cid);
    }


    @DeleteMapping("/customer/{cid}")
    public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable String cid) {
        customerService.deleteCustomerData(cid);
        return new ResponseEntity<HttpStatus>(HttpStatus.ACCEPTED);
    }

    @PostMapping("/customer")
    public ResponseEntity<CarData> createCustomer(@Valid @RequestBody Customer customer) {
        Customer savedCustomer = customerService.persist(customer);
        return new ResponseEntity(customer, HttpStatus.CREATED);
    }
    @PutMapping(value = "/customer/{cid}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable String cid, @Valid @RequestBody Customer customer) {
        String updatedCustomer = customerService.updateCustomerData(customer, cid);
        if(StringUtils.isEmpty(updatedCustomer)) {
            return ResponseEntity.notFound().build();
        } else {
            return new ResponseEntity<>(customer, HttpStatus.OK);
        }
    }
}

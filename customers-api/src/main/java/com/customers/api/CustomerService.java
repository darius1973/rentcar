package com.customers.api;


import com.customers.api.exception.CustomerNotFoundException;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.CustomerRepository;

import java.util.Optional;


@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    public Customer retrieveCustomer(String cid) {
        Optional<Customer> customer = customerRepository.findById(cid);
        if(!customer.isPresent()) {
            throw new CustomerNotFoundException("customer id: " + cid);
        }
        return customer.get();
    }

    public void deleteCustomerData(String cid) {
        customerRepository.deleteById(cid);
    }

    public Customer persist(Customer customer) {
        return customerRepository.save(customer);
    }

    public String  updateCustomerData(Customer customer, String cid) {
        Optional<Customer> customerOptional = customerRepository.findById(cid);
        if(!customerOptional.isPresent()) {
            return null;
        } else {
            customer.setCid(cid);
            customerRepository.save(customer);
            return cid;
        }
    }
}

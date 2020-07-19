package com.customers.api;

import com.customers.api.exception.CustomerNotFoundException;
import domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repositories.CustomerRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    private Customer customer;


    @Before
    public void setUp() throws Exception {
        customer = customer();
    }


    @Test
    public void retrieveCustomer() throws Exception {


        when(customerRepository.findById("007")).thenReturn(Optional.of(customer));

        Customer customerData = customerService.retrieveCustomer("007");

        assertThat(customerData.getCid()).isEqualTo("007");
        assertThat(customerData.getName()).isEqualTo("Bond");
    }

    @Test
    public void retrieveCustomerException() throws Exception {

        assertThatThrownBy(() -> {
            customerService.retrieveCustomer("DUMMY");
        }).isInstanceOf(CustomerNotFoundException.class)
                .hasMessage("customer id: DUMMY");
    }


    @Test
    public void deleteCustomerData() throws Exception {

        customerService.deleteCustomerData("007");

        verify(customerRepository, times(1)).deleteById("007");
    }

    @Test
    public void persist() throws Exception {

        when(customerRepository.save(customer)).thenReturn(customer);

        Customer persistedCustomer = customerService.persist(customer);

        assertThat(persistedCustomer).isEqualTo(customer);
    }

    @Test
    public void updateCustomerFound() throws Exception {
        when(customerRepository.findById("007")).thenReturn(Optional.of(customer));

        String updatedCustomerId = customerService.updateCustomerData(customer, "007");

        assertThat(updatedCustomerId).isEqualTo("007");
    }

    @Test
    public void updateCarDataNotFound() throws Exception {

        String updatedCustomerId = customerService.updateCustomerData(customer, "DUMMY");

        assertThat(updatedCustomerId).isNull();
    }


    private Customer customer() {
        Customer customer = new Customer();
        customer.setCid("007");
        customer.setName("Bond");
        return customer;
    }

}
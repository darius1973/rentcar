package com.customers.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class CustomerResourceTest {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private Customer customer;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        customer = customer();
    }

    @Test
    public void retrieveCustomer() throws Exception {

      when(customerService.retrieveCustomer("007")).thenReturn(customer);

      mvc.perform(MockMvcRequestBuilders.get("/customer/{cid}","007")
              .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andDo(print())
              .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.cid").value("007"))
              .andExpect(jsonPath("$.name").value("Bond"));
    }

    private Customer customer() {
        Customer customer = new Customer();
        customer.setCid("007");
        customer.setName("Bond");
        customer.setStreet("DS");
        customer.setHouseNumber("11");
        customer.setZipCode("11AVV");
        customer.setEmail("a@bcd.com");
        customer.setPhoneNumber("1234");
        customer.setPlace("LDN");
        return customer;
    }

    @Test
    public void deleteCustomerData() throws Exception {
        mvc.perform( MockMvcRequestBuilders.delete("/customer/{cid}", "007") )
                .andExpect(status().isAccepted());
    }

    @Test
    public void createCustomer() throws Exception {

        when(customerService.persist(customer)).thenReturn(customer);

        mvc.perform( MockMvcRequestBuilders
                .post("/customer")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.cid").value("007"))
                .andExpect(jsonPath("$.name").value("Bond"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateCustomerNotFound() throws Exception {

        when(customerService.updateCustomerData(customer, "007")).thenReturn(null);

        mvc.perform( MockMvcRequestBuilders
                .put("/customer/{cid}", "007")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCustomerSuccess() throws Exception {

        when(customerService.updateCustomerData(any(Customer.class), eq("007"))).thenReturn("007");

        mvc.perform( MockMvcRequestBuilders
                .put("/customer/{cid}", "007")
                .content(asJsonString(customer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cid").value("007"))
                .andExpect(jsonPath("$.name").value("Bond"));

    }

    @TestConfiguration
    static class Config {
        @MockBean
        private CustomerService customerService;
    }

}
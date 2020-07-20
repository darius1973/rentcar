package com.lease.rate;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.Contract;
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
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
public class LeaseRateResourceTest {

    @Autowired
    private LeaseRateService leaseRateService;

    @Autowired
    private ServiceAccessor serviceAccessor;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private Contract contract;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        contract = contract();
    }

    @Test
    public void calculateLeaseRate() throws Exception {

        when(leaseRateService.calculateLeaseRate(any(Contract.class))).thenReturn(3.0);

        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post("/lease-rate")
                .content(asJsonString(contract))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        Double leaseRate = Double.parseDouble(mvcResult.getResponse().getContentAsString());
        assertThat(leaseRate).isEqualTo(3.0);

    }

    private Contract contract() {
        Contract contract = new Contract();
        contract.setCid("007");
        contract.setLicensePlate("1MCV01");
        contract.setMillage(15000.00);
        contract.setDuration(3);
        contract.setInterestRate(3.0);

        return contract;
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @TestConfiguration
    static class Config {
        @MockBean
        private LeaseRateService leaseRateService;

        @MockBean
        private ServiceAccessor serviceAccessor;
    }
}
package com.car.data.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import domain.CarData;
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
public class CarDataResourceTest {

    @Autowired
    private CarDataService carDataService;
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mvc;

    private CarData carData;

    @Before
    public void setUp() throws Exception {
        mvc = MockMvcBuilders.webAppContextSetup(wac).build();
        carData = carData();
    }

    @Test
    public void retrieveCarData() throws Exception {

      when(carDataService.retrieveCarData("12MVC01")).thenReturn(carData());

      mvc.perform(MockMvcRequestBuilders.get("/cardata/{licensePlate}","12MVC01")
              .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isOk())
              .andDo(print())
              .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
              .andExpect(jsonPath("$.licensePlate").value("12MVC01"))
              .andExpect(jsonPath("$.model").value("Ford T"));
    }

    private CarData carData() {

        CarData carData = new CarData();
        carData.setLicensePlate("12MVC01");
        carData.setModel("Ford T");
        carData.setMake("Ford");
        carData.setCo2Emission(4.50);
        carData.setNbrOfDoors(5);
        carData.setVersion("K");
        carData.setGrossPrice(1000.00);
        carData.setNetPrice(6000.00);
        return carData;
    }

    @Test
    public void deleteCarData() throws Exception {
        mvc.perform( MockMvcRequestBuilders.delete("/cardata/{licensePlate}", "12MVC01") )
                .andExpect(status().isAccepted());
    }

    @Test
    public void createCarData() throws Exception {

        when(carDataService.persist(carData)).thenReturn(carData);

        mvc.perform( MockMvcRequestBuilders
                .post("/cardata")
                .content(asJsonString(carData))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.licensePlate").value("12MVC01"))
                .andExpect(jsonPath("$.model").value("Ford T"));
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateCarDataNotFound() throws Exception {

        when(carDataService.updateCarData(carData, "12MVC01")).thenReturn(null);

        mvc.perform( MockMvcRequestBuilders
                .put("/cardata/{licensePlate}", "12MVC01")
                .content(asJsonString(carData))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void updateCarDataSuccess() throws Exception {

        when(carDataService.updateCarData(any(CarData.class), eq("12MVC01"))).thenReturn("12MVC01");

        mvc.perform( MockMvcRequestBuilders
                .put("/cardata/{licensePlate}", "12MVC01")
                .content(asJsonString(carData))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.licensePlate").value("12MVC01"))
                .andExpect(jsonPath("$.model").value("Ford T"));

    }

    @TestConfiguration
    static class Config {
        @MockBean
        private CarDataService carDataService;
    }

}
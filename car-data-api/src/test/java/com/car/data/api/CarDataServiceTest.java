package com.car.data.api;

import com.car.data.api.exception.CarDataNotFoundException;

import domain.CarData;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repositories.CarDataRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CarDataServiceTest {

    @Mock
    private CarDataRepository carDataRepository;

    @InjectMocks
    private CarDataService carDataService;

    private CarData carData;


    @Before
    public void setUp() throws Exception {
        carData = carData();
    }


    @Test
    public void retrieveCarData() throws Exception {


        when(carDataRepository.findByLicensePlate("12MVC01")).thenReturn(Optional.of(carData));

        CarData carData = carDataService.retrieveCarData("12MVC01");

        assertThat(carData.getLicensePlate()).isEqualTo("12MVC01");
        assertThat(carData.getModel()).isEqualTo("Ford T");
    }

    @Test
    public void retrieveCarDataException() throws Exception {

        assertThatThrownBy(() -> {
            carDataService.retrieveCarData("DUMMY");
        }).isInstanceOf(CarDataNotFoundException.class)
                .hasMessage("licensePlate: DUMMY");
    }


    @Test
    public void deleteCarData() throws Exception {

        carDataService.deleteCarData("01MVV3");

        verify(carDataRepository, times(1)).deleteById("01MVV3");
    }

    @Test
    public void persist() throws Exception {

        when(carDataRepository.save(carData)).thenReturn(carData);

        CarData persistedCarData = carDataService.persist(carData);

        assertThat(persistedCarData).isEqualTo(carData);
    }

    @Test
    public void updateCarDataFound() throws Exception {
        when(carDataRepository.findById("12MVC01")).thenReturn(Optional.of(carData));

        String updatedLicensePlate = carDataService.updateCarData(carData, "12MVC01");

        assertThat(updatedLicensePlate).isEqualTo("12MVC01");
    }

    @Test
    public void updateCarDataNotFound() throws Exception {

        String updatedLicensePlate = carDataService.updateCarData(carData, "DUMMY");

        assertThat(updatedLicensePlate).isNull();
    }


    private CarData carData() {
        CarData carData = new CarData();
        carData.setLicensePlate("12MVC01");
        carData.setModel("Ford T");
        return carData;
    }

}
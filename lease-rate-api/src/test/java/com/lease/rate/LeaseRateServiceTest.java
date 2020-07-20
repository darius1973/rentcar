package com.lease.rate;

import domain.CarData;
import domain.Contract;
import domain.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repositories.ContractRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LeaseRateServiceTest {


    @Mock
    private LeaseCalculator leaseCalculator;


    @Mock
    private ServiceAccessor serviceAccessor;

    @Mock
    private ContractRepository contractRepository;

    @InjectMocks
    private LeaseRateService leaseRateService;

    private Customer customer;

    private CarData carData;

    private Contract contract;


    @Before
    public void setUp() throws Exception {
        customer = customer();
        carData = carData();
        contract = contract();
    }

    @Test
    public void calculateLeaseRate() throws Exception {
        when(serviceAccessor.getCarDataFor("12MVC01")).thenReturn(carData);
        when(serviceAccessor.getCustomerFor("007")).thenReturn(customer);
        when(leaseCalculator.calculateLeaseRateFor(carData, contract)).thenReturn(0.70);

        Double leaseRate = leaseRateService.calculateLeaseRate(contract);

        verify(contractRepository).save(contract);
        assertThat(leaseRate).isEqualTo(0.70);

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

    private Contract contract() {
        Contract contract = new Contract();
        contract.setCid("007");
        contract.setLicensePlate("12MVC01");
        contract.setMillage(15000.00);
        contract.setDuration(3);
        contract.setInterestRate(3.0);

        return contract;
    }



}
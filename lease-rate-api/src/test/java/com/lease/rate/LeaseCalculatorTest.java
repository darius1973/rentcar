package com.lease.rate;

import domain.CarData;
import domain.Contract;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class LeaseCalculatorTest {


    private Contract contract;

    private CarData carData;


    @Before
    public void setUp() throws Exception {

        contract = contract();
        carData = carData();
    }

    @Test
    public void calculateLeaseRateForTest() {

        LeaseCalculator leaseCalculator = new LeaseCalculator();
        Double leaseRate = leaseCalculator.calculateLeaseRateFor(carData,contract);

        assertThat(leaseRate).isEqualTo(500.6275);
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
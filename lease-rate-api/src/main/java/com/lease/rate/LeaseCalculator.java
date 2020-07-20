package com.lease.rate;

import domain.CarData;
import domain.Contract;
import domain.Customer;
import org.springframework.stereotype.Component;

@Component
public class LeaseCalculator {

    public Double calculateLeaseRateFor(Customer customer, CarData carData, Contract contract) {

        //Lease rate = ((( mileage / 12 ) * duration ) / Nett price) + ((( Interest rate / 100 ) * Nett price) / 12 )
        Double leaseRate = (contract.getMillage()/12 *contract.getDuration()/carData.getNetPrice()
        + (((contract.getInterestRate()/100)+carData.getNetPrice())/12));

        return leaseRate;
    }
}

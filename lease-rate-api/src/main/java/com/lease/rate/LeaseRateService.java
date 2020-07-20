package com.lease.rate;

import com.lease.rate.exception.LeaseRateCalculatorException;
import domain.CarData;
import domain.Contract;
import domain.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositories.ContractRepository;


@Service
public class LeaseRateService {

    @Autowired
    private LeaseCalculator leaseCalculator;


    @Autowired
    private ServiceAccessor serviceAccessor;

    @Autowired
    private ContractRepository contractRepository;


    public Double calculateLeaseRate(Contract contract)  {

        CarData carData = serviceAccessor.getCarDataFor(contract.getLicensePlate());
        Customer customer = serviceAccessor.getCustomerFor(contract.getCid());
        if(carData.equals(null)) {
            throw new LeaseRateCalculatorException("Missing car data info for license plate " + contract.getLicensePlate());
        }
        if(customer.equals(null)) {
            throw new LeaseRateCalculatorException("Missing customer info for customer id  " + contract.getCid());
        }
        Double leaseRate = leaseCalculator.calculateLeaseRateFor(carData, contract);
        contract.setLeaseRate(leaseRate);
        contractRepository.save(contract);

        return leaseRate;
    }

}

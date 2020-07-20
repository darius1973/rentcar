package com.lease.rate;

import domain.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LeaseRateResource {

    @Autowired
    private  LeaseRateService leaseRateService;


    @RequestMapping(value = "/lease-rate", method = RequestMethod.POST)
    public Double calculateLeaseRate(@RequestBody @Valid Contract contract) {
        return leaseRateService.calculateLeaseRate(contract);
    }
}

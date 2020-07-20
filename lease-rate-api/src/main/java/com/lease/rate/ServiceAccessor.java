package com.lease.rate;

import com.lease.rate.exception.LeaseRateCalculatorException;
import com.lease.rate.properties.CarDataApiProperties;
import com.lease.rate.properties.CustomerApiProperties;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.DiscoveryClient;
import domain.CarData;
import domain.Customer;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class ServiceAccessor {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;


    @Autowired
    private CarDataApiProperties carDataApiProperties;

    @Autowired
    private CustomerApiProperties customerApiProperties;


    public CarData getCarDataFor(String licensePlate) {
        InstanceInfo carDataApi = discoveryClient.getApplication(carDataApiProperties.getName())
                .getInstances().get(0);
        URI carDataUrl = toURL(
                carDataApi, carDataApiProperties.getScheme(), carDataApiProperties.getPath() + licensePlate);
        return restTemplate.getForObject(carDataUrl, CarData.class);
    }

    public Customer getCustomerFor(String cid) {
        InstanceInfo customerApi = discoveryClient.getApplication(customerApiProperties.getName())
                .getInstances().get(0);
        URI customerURL = toURL(
                customerApi, customerApiProperties.getScheme(), customerApiProperties.getPath() + cid);
        return restTemplate.getForObject(customerURL, Customer.class);
    }


    private URI toURL(InstanceInfo instanceInfo,String scheme, String path) {
        try {
            URIBuilder builder = new URIBuilder();
            builder.setScheme(scheme);
            builder.setHost(instanceInfo.getIPAddr());
            builder.setPort(instanceInfo.getPort());
            builder.setPath(path);

            return builder.build();

        } catch (Exception e) {
            throw new LeaseRateCalculatorException("Exception in building the URL ", e);
        }
    }
}

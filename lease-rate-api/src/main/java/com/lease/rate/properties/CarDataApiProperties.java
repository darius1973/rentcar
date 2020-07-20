package com.lease.rate.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("car-data")
@Component
@Getter
@Setter
public class CarDataApiProperties {

    private String name;
    private String scheme;
    private String path;
}

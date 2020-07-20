package com.lease.rate.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties("customer")
@Component
@Getter
@Setter
public class CustomerApiProperties {

    private String name;
    private String scheme;
    private String path;
}

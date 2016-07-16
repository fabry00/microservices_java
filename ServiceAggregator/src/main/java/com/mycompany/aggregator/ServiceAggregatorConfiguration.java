package com.mycompany.aggregator;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class ServiceAggregatorConfiguration extends Configuration {
    public static final String SERVICE_NAME = "ServiceAggregator";
    public static final String SERVICE_DESC = "Service aggregator";
}

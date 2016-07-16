package com.mycompany.processservice;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class ProcessServiceConfiguration extends Configuration {

    public static final String SERVICE_NAME = "ProcessService";
    // TODO: implement service configuration
    public static final String SERVICE_DESC = "Gives all the information about processes";
}

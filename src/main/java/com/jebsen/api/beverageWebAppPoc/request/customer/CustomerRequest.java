package com.jebsen.api.beverageWebAppPoc.request.customer;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class CustomerRequest implements Serializable {
    @NotBlank
    String salesRepADName;
}

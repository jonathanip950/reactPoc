package com.jebsen.api.beverageWebAppPoc.request.salesRepRole;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SalesRepRoleRequest {
    @NotBlank
    String salesRepADName;
}

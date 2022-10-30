package com.jebsen.api.beverageWebAppPoc.request.promotionQuery;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PriceQueryRequest implements Serializable {
    @NotBlank
    @JsonProperty(value = "accountNumber")
    private String account_number;
    @NotBlank
    @JsonProperty(value = "division")
    private String division;
//    @JsonProperty(value = "outletNumber")
//    private String outlet_number;
//    @JsonProperty(value = "orgId")
//    private int org_id;
}

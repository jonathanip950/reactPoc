package com.jebsen.api.beverageWebAppPoc.request.priceList;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
public class PriceListRequest implements Serializable {
//    private int orgId;
//    private int priceListId;
    @NotBlank
    private String salesRepADName;
}

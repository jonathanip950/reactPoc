package com.jebsen.api.beverageWebAppPoc.request.quotation;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Promotion {
    private BigDecimal orgId;
    private BigDecimal billToSiteId;
    private BigDecimal orderTypeId;
    private BigDecimal inventoryItemId;
    private BigDecimal buyQty;
    private BigDecimal getQty;
    private BigDecimal getItem;
    private BigDecimal percent;
    private BigDecimal price;
    private BigDecimal newPrice;
    private boolean overlap;
    private String nature;
    private String salesActivation;
}

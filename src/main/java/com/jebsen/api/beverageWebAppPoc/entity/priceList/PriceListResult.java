package com.jebsen.api.beverageWebAppPoc.entity.priceList;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class PriceListResult implements Serializable {
    private BigDecimal LIST_HEADER_ID;
    private String CURRENCY_CODE;
    private BigDecimal MARGIN_RATIO;
    private String PRODUCT;
    private String PRINCIPAL;
    private String PRODUCT_DESCRIPTION;
    private String PRODUCT_BRAND;
    private BigDecimal ORGANIZATION_ID;
    private BigDecimal INVENTORY_ITEM_ID;
    private String PRODUCT_UOM_CODE;
    private BigDecimal UNIT_PRICE;
    private String DRAFT_BEER_FLAG;
}

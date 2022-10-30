package com.jebsen.api.beverageWebAppPoc.entity.customer;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@ToString
public class CustomerResult implements Serializable {
    private BigDecimal ORG_ID;
    private String PARENT_NAME;
    private BigDecimal CUST_ACCOUNT_ID;
    private String ACCOUNT_NUMBER;
    private String COMPANY_NAME;
    private String OUTLET_NAME;
    private BigDecimal SHIP_TO_SITE_ID;
    private String SHIP_TO_ADDRESS;
    private String SHIP_TO_DISTRICT;
    private BigDecimal BILL_TO_SITE_ID;
    private String BILL_TO_ADDRESS;
    private BigDecimal PAYMENT_TERM_ID;
    private BigDecimal SALESREP_ID;
    private BigDecimal PRICE_LIST_ID;
    private String WALK_IN_CUST;
}

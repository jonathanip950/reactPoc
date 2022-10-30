package com.jebsen.api.beverageWebAppPoc.entity.quotation;

import com.jebsen.api.beverageWebAppPoc.validator.ContentByte;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@ToString
public class SOInHeader implements Serializable {
    
    private BigDecimal ORDER_TYPE_ID;
    private BigDecimal SOLD_TO_ORG_ID;
    private BigDecimal SHIP_TO_ORG_ID;
    private BigDecimal INVOICE_TO_ORG_ID;
    private BigDecimal ORDER_SOURCE_ID;

    private BigDecimal PRICE_LIST_ID;
    private Timestamp PRICING_DATE;
    @ContentByte(max=30)
    private String FLOW_STATUS_CODE;
    @ContentByte(max=50)
    private String CUST_PO_NUMBER;
    private BigDecimal SOLD_FROM_ORG_ID;

    private BigDecimal SHIP_FROM_ORG_ID;
    private BigDecimal SALESREP_ID;
    @ContentByte(max=15)
    private String TRANSACTIONAL_CURR_CODE;
    private BigDecimal PAYMENT_TERM_ID;
    @ContentByte(max=1000)
    private String REMARKS;

    private BigDecimal FREIGHT_CHARGE;
    @ContentByte(max=30)
    private String PAYMENT_TYPE;
    @ContentByte(max=100)
    private String REAL_CUSTOMER;
    @ContentByte(max=240)
    private String SALES_TERMS;
    @ContentByte(max=240)
    private String ATTENTION;

    private BigDecimal JS_ORDER_TXN_ID;
    @ContentByte(max=150)
    private String CONTACT_NAME;
    @ContentByte(max=150)
    private String PHONE_NUMBER;
    @ContentByte(max=150)
    private String EMAIL_ADDRESS;
    @ContentByte(max=30)
    private String TIME_SLOT;
}

package com.jebsen.api.beverageWebAppPoc.entity.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder(toBuilder = true)
public class SOOutHeader implements Serializable {
    @JsonProperty
    private BigDecimal HEADER_ID;
    @JsonProperty
    private BigDecimal ORDER_NUMBER;
    @JsonProperty
    private BigDecimal ORDER_TYPE_ID;
    @JsonProperty
    private BigDecimal SOLD_TO_ORG_ID;
    @JsonProperty
    private BigDecimal  SHIP_TO_ORG_ID;

    @JsonProperty
    private BigDecimal INVOICE_TO_ORG_ID;
    @JsonProperty
    private BigDecimal ORDER_SOURCE_ID;
    @JsonProperty
    private BigDecimal PRICE_LIST_ID;
    @JsonProperty
    private Timestamp PRICING_DATE;
    @JsonProperty
    private String FLOW_STATUS_CODE;

    @JsonProperty
    private String CUST_PO_NUMBER;
    @JsonProperty
    private BigDecimal SOLD_FROM_ORG_ID;
    @JsonProperty
    private BigDecimal SHIP_FROM_ORG_ID;
    @JsonProperty
    private BigDecimal SALESREP_ID;
    @JsonProperty
    private String TRANSACTIONAL_CURR_CODE;

    @JsonProperty
    private BigDecimal PAYMENT_TERM_ID;
    @JsonProperty
    private String REMARKS;
    @JsonProperty
    private BigDecimal FREIGHT_CHARGE;
    @JsonProperty
    private String  CONTACT_NAME;
    @JsonProperty
    private String PHONE_NUMBER;


    @JsonProperty
    private String EMAIL_ADDRESS;
    @JsonProperty
    private String TIME_SLOT;

}

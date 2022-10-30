package com.jebsen.api.beverageWebAppPoc.request.quotation;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class QuotationHeader{
    private Date orderDate;
    private BigDecimal totalItems;
    private String attention;
    private String salesTerms;
    private Date startDate;
    private Date endDate;
    private String remarks;
    private BigDecimal soldFromOrgId;
    private BigDecimal soldToOrgId;
    private BigDecimal shipFromOrgId;
    private BigDecimal shipToOrgId;
    private BigDecimal invoiceToOrgId;
    private BigDecimal salesRepId;
    private BigDecimal priceListId;
    private String transactionalCurrencyCode;
    private BigDecimal paymentTerms;
}

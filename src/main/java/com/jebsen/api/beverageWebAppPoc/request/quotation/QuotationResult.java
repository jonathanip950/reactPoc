package com.jebsen.api.beverageWebAppPoc.request.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@ToString
public class QuotationResult implements Serializable {
    @JsonProperty(value = "result_code")
    private String resultCode;
    @JsonProperty(value = "result_message")
    private String resultMsg;
    @JsonProperty(value = "result_message_count")
    private Integer resultMsgCount;
    @JsonProperty(value = "order_number")
    private BigDecimal orderNumber;
    @JsonProperty(value = "products")
    private List<String> products = new ArrayList<>();
}

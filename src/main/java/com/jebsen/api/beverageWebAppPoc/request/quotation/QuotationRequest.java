package com.jebsen.api.beverageWebAppPoc.request.quotation;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@JsonTypeName(value = "quotation")
@JsonTypeInfo(include = JsonTypeInfo.As.WRAPPER_OBJECT, use = JsonTypeInfo.Id.NAME)
@ToString
public class QuotationRequest {
    @NotNull
    private QuotationHeader quotationHeader;
    @NotNull
    private List<QuotationLine> quotationLines;
}

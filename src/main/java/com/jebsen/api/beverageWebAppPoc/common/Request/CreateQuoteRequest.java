package com.jebsen.api.beverageWebAppPoc.common.Request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.jebsen.api.beverageWebAppPoc.entity.quotation.ModifierInRecord;
import com.jebsen.api.beverageWebAppPoc.entity.quotation.SOInHeader;
import com.jebsen.api.beverageWebAppPoc.entity.quotation.SOInLine;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Data
@ToString
public class CreateQuoteRequest implements Serializable {
    @JsonProperty("SO_HEADER_IN_RECORD")
    private SOInHeader SO_HEADER_IN_RECORD;
    @JsonProperty("SO_LINE_IN_RECORD")
    private List<SOInLine> SOInLineList;
    @JsonProperty("MODIFIER_IN_RECORD")
    private List<ModifierInRecord> ModifierInRecordList;
}

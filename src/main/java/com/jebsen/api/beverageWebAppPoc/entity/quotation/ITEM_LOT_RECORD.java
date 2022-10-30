package com.jebsen.api.beverageWebAppPoc.entity.quotation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ITEM_LOT_RECORD implements Serializable {
    @JsonProperty
    private String LOT_NUMBER;
}

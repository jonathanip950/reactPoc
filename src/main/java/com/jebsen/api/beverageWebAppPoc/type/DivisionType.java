package com.jebsen.api.beverageWebAppPoc.type;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum DivisionType {
    WINE(2899, "wine"),
    BEER(2898, "beer");

    private int orgId;
    private String division;

    DivisionType(int orgId, String division) {
        this.orgId = orgId;
        this.division = division;
    }
}

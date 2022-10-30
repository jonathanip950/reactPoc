package com.jebsen.api.beverageWebAppPoc.storedProcedure;

import com.jebsen.api.beverageWebAppPoc.entity.priceList.PriceListResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class PriceListStoredProcedure extends StoredProcedure {

    public PriceListStoredProcedure(@Autowired JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "APPS.JCL_M_GET_PRICE_LIST_PKG.JCL_GET_PRICE_LIST_BY_AD");
        declareParameter(new SqlParameter("P_AD_ACCOUNT", Types.VARCHAR));
        declareParameter(new SqlOutParameter("P_PRICE_LIST_TBL", Types.ARRAY,"JCL_M_GET_PRICE_LIST_PKG.PRICE_LIST_TBL", new SqlReturnStructArray<PriceListResult>(new BeanPropertyStructMapper<PriceListResult>(PriceListResult.class))));
        compile();
    }

    public List<PriceListResult> execute(String salesRepADName) {
        Map<String, Object> out = super.execute(salesRepADName);

        return Arrays.stream(((Object[]) out.get("P_PRICE_LIST_TBL")))
                .map(s -> (PriceListResult) s)
                .collect(Collectors.toList());
    }
}

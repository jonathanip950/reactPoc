package com.jebsen.api.beverageWebAppPoc.storedProcedure;

import com.jebsen.api.beverageWebAppPoc.request.promotionQuery.PriceQueryRequest;
import com.jebsen.api.beverageWebAppPoc.entity.priceQuery.PriceQueryResult;
import com.jebsen.api.beverageWebAppPoc.mapper.struct.SqlStructConnectionUnwarp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlReturnStructArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import java.sql.Types;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Component
public class PriceQueryProcedure extends StoredProcedure {
    public PriceQueryProcedure(@Autowired JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "APPS.JCL_SFA_M_PRICING_PKG.PRICE_QUERY");
        declareParameter(new SqlParameter("P_BATCH_NUMBER", Types.VARCHAR));
        declareParameter(new SqlParameter("P_PRICE_QUERY", Types.STRUCT,"JCL_SFA_M_PRICING_PKG.P_PRICE_QUERY_REC_TY"));
        declareParameter(new SqlOutParameter("X_PRICE_QUERY_LIST", Types.ARRAY,"JCL_SFA_M_PRICING_PKG.X_PRICE_QUERY_TAB_TY", new SqlReturnStructArray<PriceQueryResult>(new BeanPropertyStructMapper<PriceQueryResult>(PriceQueryResult.class))));
        compile();
    }

    public List<PriceQueryResult> execute(PriceQueryRequest priceQueryRequest) {
        Map<String, Object> paramIn = new HashMap<String, Object>();
        paramIn.put("P_BATCH_NUMBER", System.currentTimeMillis());
        paramIn.put("P_PRICE_QUERY",  new SqlStructConnectionUnwarp<PriceQueryRequest>(priceQueryRequest, new BeanPropertyStructMapper<PriceQueryRequest>(PriceQueryRequest.class), "JCL_SFA_M_PRICING_PKG.P_PRICE_QUERY_REC_TY"));

        Map<String, Object> out = super.execute(paramIn);
        return Arrays.stream(((Object[]) out.get("X_PRICE_QUERY_LIST")))
                .map(s -> (PriceQueryResult) s)
                .collect(Collectors.toList());
    }
}

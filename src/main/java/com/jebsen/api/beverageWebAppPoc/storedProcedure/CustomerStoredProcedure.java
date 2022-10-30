package com.jebsen.api.beverageWebAppPoc.storedProcedure;

import com.jebsen.api.beverageWebAppPoc.entity.customer.CustomerResult;
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
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CustomerStoredProcedure extends StoredProcedure {

    public CustomerStoredProcedure(@Autowired JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "JCL_M_CUSTOMER_PKG.GET_CUSTOMER");
        declareParameter(new SqlParameter("P_AD_LOGIN", Types.VARCHAR));
        declareParameter(new SqlOutParameter("P_CUSTOMER_TBL", Types.ARRAY,"JCL_M_CUSTOMER_PKG.CUSTOMER_TBL", new SqlReturnStructArray<CustomerResult>(new BeanPropertyStructMapper<CustomerResult>(CustomerResult.class))));
        compile();
    }

    public List<CustomerResult> execute(String salesRepADName) {
        Map<String, Object> out = super.execute(salesRepADName);

        List<CustomerResult> results = Arrays.stream(((Object[]) out.get("P_CUSTOMER_TBL")))
                .map(s -> (CustomerResult) s)
                .collect(Collectors.toList());

        log.info("Customer Result - salesRepADName: {}, customers total: {}, first customer: {}", salesRepADName, results.size(), results.size()>0?results.get(0).getCOMPANY_NAME():null);

        return results;
    }
}

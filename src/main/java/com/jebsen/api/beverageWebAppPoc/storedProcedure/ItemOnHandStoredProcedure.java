package com.jebsen.api.beverageWebAppPoc.storedProcedure;

import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.OnHandRecord;
import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.OnHandRecordResult;
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
public class ItemOnHandStoredProcedure extends StoredProcedure {

    public ItemOnHandStoredProcedure(@Autowired JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "JCL_ISG_INVENTORY_PKG.GET_ITEM_ONHAND_BY_ITEM");
        declareParameter(new SqlParameter("P_ORG_ID", Types.NUMERIC));
        declareParameter(new SqlParameter("P_ITEM_ID", Types.NUMERIC));
        declareParameter(new SqlOutParameter("P_ONHAND_TBL", Types.ARRAY,"JCL_ISG_INVENTORY_PKG.ONHAND_TBL", new SqlReturnStructArray<OnHandRecordResult>(new BeanPropertyStructMapper<OnHandRecordResult>(OnHandRecordResult.class))));
        compile();
    }

    public List<OnHandRecordResult> execute(int orgId, int itemId) {
        Map<String, Object> out = super.execute(orgId, itemId);

        return Arrays.stream(((Object[]) out.get("P_ONHAND_TBL")))
                .map(s -> (OnHandRecordResult) s)
                .collect(Collectors.toList());
    }
}

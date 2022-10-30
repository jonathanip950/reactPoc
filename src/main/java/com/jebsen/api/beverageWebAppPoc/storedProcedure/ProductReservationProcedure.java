package com.jebsen.api.beverageWebAppPoc.storedProcedure;

import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.OnHandRecordResult;
import com.jebsen.api.beverageWebAppPoc.entity.itemOnHand.ReservationRecordResult;
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
public class ProductReservationProcedure extends StoredProcedure {
    public ProductReservationProcedure(@Autowired JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "APPS.JCL_ISG_INVENTORY_PKG.GET_ITEM_RESERVATION_DETAILS");
        declareParameter(new SqlParameter("P_ORG_ID", Types.NUMERIC));
        declareParameter(new SqlParameter("P_ITEM_ID", Types.NUMERIC));
        declareParameter(new SqlParameter("P_ITEM_SUBINV", Types.VARCHAR));
        declareParameter(new SqlParameter("P_ITEM_LOT", Types.VARCHAR));
        declareParameter(new SqlOutParameter("P_RESERVATION_TBL", Types.ARRAY,"JCL_ISG_INVENTORY_PKG.RESERVATION_TBL", new SqlReturnStructArray<ReservationRecordResult>(new BeanPropertyStructMapper<ReservationRecordResult>(ReservationRecordResult.class))));
        compile();
    }

    public List<ReservationRecordResult> _execute(OnHandRecordResult onHandRecord) {
        Map<String, Object> out = super.execute(onHandRecord.getORG_ID(), onHandRecord.getITEM_ID(), onHandRecord.getSUBINVENTORY(), onHandRecord.getLOT());

        return Arrays.stream(((Object[]) out.get("P_RESERVATION_TBL")))
                .map(s -> (ReservationRecordResult) s)
                .collect(Collectors.toList());
    }
}

package com.jebsen.api.beverageWebAppPoc.storedProcedure;

import com.jebsen.api.beverageWebAppPoc.entity.quotation.*;
import com.jebsen.api.beverageWebAppPoc.mapper.struct.SqlStructArrayValueConnectionUnwarp;
import com.jebsen.api.beverageWebAppPoc.mapper.struct.SqlStructConnectionUnwarp;
import com.jebsen.api.beverageWebAppPoc.request.quotation.QuotationResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jdbc.support.oracle.BeanPropertyStructMapper;
import org.springframework.data.jdbc.support.oracle.SqlReturnStruct;
import org.springframework.data.jdbc.support.oracle.SqlReturnStructArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class CreateQuoteProcedure extends StoredProcedure {

    public CreateQuoteProcedure(@Autowired JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, "JCL_PRICE_QUOTATION_API_PKG.CREATE_QUOTE");
        declareParameter(new SqlParameter("I_HEADER_REC", Types.STRUCT, "JCL_PRICE_QUOTATION_API_PKG.SO_HEADER_IN_RECORD"));
        declareParameter(new SqlParameter("I_LINE_TBL", Types.ARRAY));
        declareParameter(new SqlParameter("I_MODIFIER_TBL", Types.ARRAY));
        declareParameter(new SqlOutParameter("O_HEADER_REC_OUT", Types.STRUCT, "JCL_PRICE_QUOTATION_API_PKG.SO_HEADER_OUT_RECORD", new SqlReturnStruct(SOOutHeader.class)));
        declareParameter(new SqlOutParameter("O_LINE_TBL_OUT", Types.ARRAY, "JCL_PRICE_QUOTATION_API_PKG.SO_LINE_OUT_TBL", new SqlReturnStructArray<SOOutLine>(new BeanPropertyStructMapper<SOOutLine>(SOOutLine.class))));
        declareParameter(new SqlOutParameter("O_RETURN_STATUS", Types.VARCHAR));
        declareParameter(new SqlOutParameter("O_MSG_COUNT", Types.NUMERIC));
        declareParameter(new SqlOutParameter("O_MSG_DATA", Types.VARCHAR));

        compile();
    }

    public QuotationResult execute(SOInHeader sOInHeader, List<SOInLine> sOInLineList, List<ModifierInRecord> modifierInRecordList) {
        Map<String, Object> paramIn = new HashMap<String, Object>();
        SOInLine[] sOInLineListobjects = sOInLineList.<SOInLine>toArray(new SOInLine[sOInLineList.size()]);
        ModifierInRecord[] modifierInRecordListobjects = modifierInRecordList.<ModifierInRecord>toArray(new ModifierInRecord[modifierInRecordList.size()]);

        paramIn.put("I_HEADER_REC", new SqlStructConnectionUnwarp<SOInHeader>(
                sOInHeader,
                new BeanPropertyStructMapper<SOInHeader>(SOInHeader.class),
                "JCL_PRICE_QUOTATION_API_PKG.SO_HEADER_IN_RECORD"));

        paramIn.put("I_LINE_TBL", new SqlStructArrayValueConnectionUnwarp<SOInLine>(
                sOInLineListobjects,
                new BeanPropertyStructMapper<SOInLine>(SOInLine.class),
                "JCL_PRICE_QUOTATION_API_PKG.SO_LINE_IN_RECORD",
                "JCL_PRICE_QUOTATION_API_PKG.SO_LINE_IN_TBL"));

        paramIn.put("I_MODIFIER_TBL", new SqlStructArrayValueConnectionUnwarp<ModifierInRecord>(
                modifierInRecordListobjects,
                new BeanPropertyStructMapper<ModifierInRecord>(ModifierInRecord.class),
                "JCL_PRICE_QUOTATION_API_PKG.MODIFIER_IN_RECORD",
                "JCL_PRICE_QUOTATION_API_PKG.MODIFIER_IN_TBL"));

        Map<String, Object> out = super.execute(paramIn);
        return convertResult(out);
    }

    @SneakyThrows
    private QuotationResult convertResult(Map<String, Object> out) {
        SOOutHeader soOutHeader = (SOOutHeader)out.get("O_HEADER_REC_OUT");
        List<String> soOutLine = Arrays.stream(((Object[]) out.get("O_LINE_TBL_OUT")))
                .map(line -> ((SOOutLine) line).getPRODUCT_DESCRIPTION())
                .collect(Collectors.toList());

        String resultStatusCode = (String) out.get("O_RETURN_STATUS");
        String resultMessage = (String) out.get("O_MSG_DATA");
        Integer resultMessageCnt = ((BigDecimal) out.get("O_MSG_COUNT")).intValue();

        return QuotationResult.builder().resultCode(resultStatusCode).resultMsg(resultMessage).resultMsgCount(resultMessageCnt).orderNumber(soOutHeader.getORDER_NUMBER()).products(soOutLine).build();
    }
}

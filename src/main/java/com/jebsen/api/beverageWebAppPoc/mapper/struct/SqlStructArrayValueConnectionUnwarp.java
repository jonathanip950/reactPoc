package com.jebsen.api.beverageWebAppPoc.mapper.struct;

import lombok.Cleanup;
import oracle.jdbc.OracleConnection;
import org.springframework.data.jdbc.support.oracle.SqlStructArrayValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlStructArrayValueConnectionUnwarp<T> extends SqlStructArrayValue<T> {
    public SqlStructArrayValueConnectionUnwarp(T[] values, StructMapper<T> mapper, String structTypeName) {
        super(values, mapper, structTypeName);
    }

    public SqlStructArrayValueConnectionUnwarp(T[] values, StructMapper<T> mapper, String structTypeName, String arrayTypeName) {
        super(values, mapper, structTypeName, arrayTypeName);
    }

    @Override
    protected Object createTypeValue(Connection conn, int sqlType, String typeName) throws SQLException {
        Connection connection = conn.unwrap(OracleConnection.class);
        return super.createTypeValue(connection, sqlType, typeName);
    }
}

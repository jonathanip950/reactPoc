package com.jebsen.api.beverageWebAppPoc.mapper.struct;

import oracle.jdbc.OracleConnection;
import org.springframework.data.jdbc.support.oracle.SqlStructArrayValue;
import org.springframework.data.jdbc.support.oracle.SqlStructValue;
import org.springframework.data.jdbc.support.oracle.StructMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class SqlStructConnectionUnwarp<T> extends SqlStructValue<T> {
    public SqlStructConnectionUnwarp(T source) {
        super(source);
    }

    public SqlStructConnectionUnwarp(T source, String defaultTypeName) {
        super(source, defaultTypeName);
    }

    public SqlStructConnectionUnwarp(T source, StructMapper mapper) {
        super(source, mapper);
    }

    public SqlStructConnectionUnwarp(T source, StructMapper mapper, String defaultTypeName) {
        super(source, mapper, defaultTypeName);
    }

    @Override
    protected Object createTypeValue(Connection conn, int sqlType, String typeName) throws SQLException {
        Connection connection = conn.unwrap(OracleConnection.class);
        return super.createTypeValue(connection, sqlType, typeName);
    }
}

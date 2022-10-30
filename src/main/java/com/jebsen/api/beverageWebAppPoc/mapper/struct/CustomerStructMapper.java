package com.jebsen.api.beverageWebAppPoc.mapper.struct;

import com.jebsen.api.beverageWebAppPoc.request.customer.CustomerRequest;
import oracle.sql.STRUCT;
import org.springframework.data.jdbc.support.oracle.StructMapper;

import java.sql.Connection;
import java.sql.SQLException;

public class CustomerStructMapper implements StructMapper<CustomerRequest> {

    @Override
    public STRUCT toStruct(CustomerRequest customerRequest, Connection connection, String s) throws SQLException {
        // write your code here

        return null;
    }

    @Override
    public CustomerRequest fromStruct(STRUCT struct) throws SQLException {
        return null;
    }
}

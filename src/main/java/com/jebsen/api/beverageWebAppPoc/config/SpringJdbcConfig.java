package com.jebsen.api.beverageWebAppPoc.config;

import com.jebsen.api.beverageWebAppPoc.storedProcedure.CustomerStoredProcedure;
import com.jebsen.api.beverageWebAppPoc.storedProcedure.ItemOnHandStoredProcedure;
import com.jebsen.api.beverageWebAppPoc.storedProcedure.PriceListStoredProcedure;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.storeprocedure")
    public DataSource storeProcedureDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public JdbcTemplate storeProcedureJdbcTemplate(DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

}

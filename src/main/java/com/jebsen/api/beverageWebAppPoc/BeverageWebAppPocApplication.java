package com.jebsen.api.beverageWebAppPoc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@EnableWebMvc
//@ComponentScan("com.jebsen")
public class BeverageWebAppPocApplication {
	public static void main(String[] args) {
		log.info("Hello Jebsen");
		SpringApplication.run(BeverageWebAppPocApplication.class, args);
	}
//
}

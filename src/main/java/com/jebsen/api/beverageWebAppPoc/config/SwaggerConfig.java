package com.jebsen.api.beverageWebAppPoc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.data.util.Predicates;
//import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SwaggerConfig {

    @Value("${spring.application.environment:NA}")
    private String env;
    @Value("${beveragePocAppDomain:localhost}")
    private String host;

//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .host(host)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .paths(Predicates.negate(PathSelectors.regex("/error")))
//                .build();
//    }

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("Beverage Web App POC")
//                .description("Beverage Web App POC")
//                .version("v1.0")
//                .contact(new Contact("Kei Au", "", "siukeiau@jebsen.com"))
//                .build();
//    }
}

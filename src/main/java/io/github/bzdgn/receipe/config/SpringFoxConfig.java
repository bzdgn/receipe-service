package io.github.bzdgn.receipe.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)  
          .select()                                  
//          .apis(RequestHandlerSelectors.basePackage("io.github.bzdgn.receipe.controller"))
          .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
          .paths(PathSelectors.any())                          
          .build()
          .apiInfo(getApiInfo());
    }
    
	private ApiInfo getApiInfo() {
		return new ApiInfoBuilder()
		        .title("Receipe API")
		        .version("1.0")
		        .description("API for managing employees.")
		        .contact(new Contact("Levent Divilioglu", "http://www.github.com/bzdgn", "ld@example.com"))
		        .license("Apache License Version 2.0")
		        .build();
		}
}

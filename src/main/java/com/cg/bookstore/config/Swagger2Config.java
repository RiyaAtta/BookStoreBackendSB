package com.cg.bookstore.config;



import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
	@Bean
	public Docket createRestApi(){
	  return new Docket(DocumentationType.SWAGGER_2)
	      .apiInfo(apiDetails())
	      .select()
	      .apis(RequestHandlerSelectors.basePackage("com.cg.bookstore"))
	      .paths(PathSelectors.any())
	      .build();
	}
	
	private ApiInfo apiDetails() {
	    return new ApiInfo(
	      "BOOK STORE MANAGEMENT API", 
	      "Team members->Ankita Basu,"
	      + "Anupam Roy,"
	      + "Shounak Sengupta,"
	      + "Roshma Ritanjit,"
	      + "Akash Pal,"
	      + "Riya Atta", 
	      "API TOS", 
	      "Terms of service", 
	      new springfox.documentation.service.Contact("", "http://localhost:9003/swagger-ui/", "ankitabasu0608@gmail.com"), 
	      "License of API", "API license URL", Collections.emptyList());
	}
}
package project.webshop.config.swagger;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(metaData())
                .select()
                /*.paths(regex("/products.*"))*/ // for only /products api
                .paths(Predicates.not(regex("/error.*")))
                .apis(RequestHandlerSelectors.basePackage("project.webshop.controller"))
                .build();

    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Webshop API")
                .description("Webshop API documentation")
//                .contact(new Contact("name", "url", "email"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }
}

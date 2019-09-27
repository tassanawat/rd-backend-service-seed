package th.go.rd.rdoffline.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

@Configuration
@EnableSwagger2
@Profile("dev")
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("th.go.rd.rdoffline"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }

    private ApiInfo metaData() {
    return new ApiInfo("SpringBoot+SQLite application",
            "A simple SpringBoot application that provides API ",
            "1.0", "", new Contact("Rd Offline", "http://localhost:2019/monitoring/data", "") ,
            "Apache License Version 2.0",
            "https://www.apache.org/licenses/LICENSE-2.0",
            Collections.emptyList());

    }
}

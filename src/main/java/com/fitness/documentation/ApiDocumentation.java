package com.fitness.documentation;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiDocumentation {

    @Bean
    public OpenAPI customApiDocs(){
        return new OpenAPI()
                
                .info(new Info()
                        .title("Fitness Tracker API")
                        .description("Track you fitness Activity with Finess Tracker API")
                        .contact(new Contact().name("Kaal").url("https://kaal.com").email("kaal@kaal.com"))
                        .version("V1.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")));
    }
}

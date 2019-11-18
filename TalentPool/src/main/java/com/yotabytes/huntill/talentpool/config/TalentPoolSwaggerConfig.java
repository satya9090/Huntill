package com.yotabytes.huntill.talentpool.config;

import org.springframework.context.annotation.Configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class TalentPoolSwaggerConfig {

	@Bean	
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new Info().title("Talent Pool Application").description(
                        "This is Talent Pool application"));
    }
}

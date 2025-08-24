package com.karthik.visionlabs.configs;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

@Configuration
public class ZenStreamConfig {
    @Bean
    public RestClient zenStreamRestClient(
            @Value("${zenstream.api.base-url}") String baseUrl,
            @Value("${zenstream.api.key}") String apiKey
    ){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .defaultHeader(HttpHeaders.AUTHORIZATION,"Bearer "+apiKey)
                .build();
    }
}

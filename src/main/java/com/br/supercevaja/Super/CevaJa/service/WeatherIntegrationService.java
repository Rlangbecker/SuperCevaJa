package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.model.integration.WeatherResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherIntegrationService {

    private final RestTemplate restTemplate;

    @Value("${clima-external-api}")
    private String uri;


    public WeatherIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public WeatherResponse findByName(String name) {
        String urlCompleta = this.uri + "/" + name;
        return this.restTemplate.getForObject(urlCompleta, WeatherResponse.class);
    }
}

package com.br.supercevaja.Super.CevaJa.model.integration;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherResponse {

    private TempsResponse current;
}

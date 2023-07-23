//package com.br.supercevaja.Super.CevaJa;
//
//import com.br.supercevaja.Super.CevaJa.model.integration.TempsResponse;
//import com.br.supercevaja.Super.CevaJa.model.integration.WeatherResponse;
//import com.br.supercevaja.Super.CevaJa.service.WeatherIntegrationService;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.web.client.RestTemplate;
//
//import static javax.management.Query.eq;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyString;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
//public class WeatherIntegrationServiceTest {
//    @InjectMocks
//    private WeatherIntegrationService weatherIntegrationService;
//
//    @Mock
//    private RestTemplate restTemplate;
//
//    @Mock
//    private RestTemplateBuilder restTemplateBuilder;
//
//    @Test
//    public void deveTestarBuscarTemperaturaAtualComSucesso() {
//        TempsResponse tempsResponse = new TempsResponse();
//        tempsResponse.setTemp_c(23);
//
//        WeatherResponse weatherResponse = new WeatherResponse();
//        weatherResponse.setCurrent(tempsResponse);
//
//        when(restTemplateBuilder.build()).thenReturn(restTemplate);
//        weatherIntegrationService = new WeatherIntegrationService(restTemplateBuilder);
//
//        when(this.restTemplateBuilder.build()).thenReturn(restTemplate);
//
//        when(restTemplate.getForObject(anyString(), any()))
//                .thenReturn(weatherResponse);
//
//
//        TempsResponse tempsResponseFinal = weatherIntegrationService.buscarTemperaturaAtual();
//
//
//        assertNotNull(tempsResponseFinal);
//        assertEquals(tempsResponse, tempsResponseFinal);
//
//
//    }
//
//
//}

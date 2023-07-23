package com.br.supercevaja.Super.CevaJa;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreatePedidoDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.pedidoDto.PedidoDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.model.Pedido;
import com.br.supercevaja.Super.CevaJa.model.integration.TempsResponse;
import com.br.supercevaja.Super.CevaJa.repository.CervejaRepository;
import com.br.supercevaja.Super.CevaJa.repository.PedidoRepository;
import com.br.supercevaja.Super.CevaJa.service.CervejaService;
import com.br.supercevaja.Super.CevaJa.service.PedidoService;
import com.br.supercevaja.Super.CevaJa.service.UsuarioService;
import com.br.supercevaja.Super.CevaJa.service.WeatherIntegrationService;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.parameters.P;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PedidoServiceTest {
    @InjectMocks
    private PedidoService pedidoService;

    @Mock
    private PedidoRepository pedidoRepository;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private CervejaService cervejaService;
    @Mock
    private CervejaRepository cervejaRepository;

    @Mock
    private WeatherIntegrationService weatherIntegrationService;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(pedidoService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCreateComSucesso() throws RegraDeNegocioException {

        TempsResponse tempsResponse = new TempsResponse();
        tempsResponse.setTemp_c(18);

        when(usuarioService.validarUsuario(any()))
                .thenReturn(any());

        when(cervejaService.calcularValorTotal(getPedidoCreteDto().getCervejas().get(0)))
                .thenReturn(BigDecimal.valueOf(100));

        when(weatherIntegrationService.buscarTemperaturaAtual())
                .thenReturn(tempsResponse);

        when(cervejaService.buscarPorNomeDto(any()))
                .thenReturn(getCervejaDto());

        when(pedidoRepository.save(any()))
                .thenReturn(getPedido());

      PedidoDto pedidoDto = pedidoService.criarPedido(getPedidoCreteDto());


        assertNotNull(pedidoDto);
        assertNotNull(pedidoDto.getUsernameUsuario());
        assertNotNull(pedidoDto.getValorFinal());
    }

//    @Test
//    public void deveTestarRetornarValorComDescontoComSucesso() throws RegraDeNegocioException {
//
//        CervejaCreatePedidoDto cervejaCreatePedidoDto = new CervejaCreatePedidoDto();
//        cervejaCreatePedidoDto.setNome("PILSEN");
//        cervejaCreatePedidoDto.setQuantidade(20);
//
//        when(cervejaRepository.findCervejaByNome(any()))
//                .thenReturn(Optional.of(getCerveja()));
//
//        when(cervejaService.calcularValorTotal(cervejaCreatePedidoDto))
//                .thenReturn(BigDecimal.valueOf(150));
//
//        BigDecimal valorRetorno = pedidoService.retornarValorComDesconto(getCervejaCreatePedidoDto());
//
//        assertNotEquals(BigDecimal.valueOf(150),valorRetorno);
//
//    }

    private static PedidoCreateDto getPedidoCreteDto(){

        PedidoCreateDto pedidoCreateDto = new PedidoCreateDto();
        CervejaCreatePedidoDto cervejaCreatePedidoDto1 = new CervejaCreatePedidoDto();
        cervejaCreatePedidoDto1.setQuantidade(12);
        cervejaCreatePedidoDto1.setNome("PILSEN");
        List<CervejaCreatePedidoDto> lista = new ArrayList<>();
        lista.add(cervejaCreatePedidoDto1);

        pedidoCreateDto.setUsernameUsuario("ricardo");
        pedidoCreateDto.setCervejas(lista);

        return pedidoCreateDto;
    }

    private static PedidoDto getPedidoDto(){

        PedidoDto pedidoDto = new PedidoDto();

        List<CervejaDto> lista = new ArrayList<>();
        lista.add(getCervejaDto());

        pedidoDto.setValorFinal(BigDecimal.valueOf(100));
        pedidoDto.setUsernameUsuario("ricardo");
        pedidoDto.setCervejas(lista);

        return pedidoDto;
    }

    private static Pedido getPedido(){

        Pedido pedido = new Pedido();

        pedido.setIdPedido(1);
        pedido.setValorFinal(BigDecimal.valueOf(100));

        return pedido;
    }

    private static Cerveja getCerveja() {
        Cerveja cerveja = new Cerveja();

        cerveja.setQuantidade(100);
        cerveja.setValor(BigDecimal.valueOf(15));
        cerveja.setIdCerveja(1);
        cerveja.setNome("PILSEN");

        return cerveja;
    }

    private static CervejaDto getCervejaDto() {
        CervejaDto cervejaDto = new CervejaDto();

        cervejaDto.setQuantidade(100);
        cervejaDto.setValor(BigDecimal.valueOf(10));
        cervejaDto.setIdCerveja(1);
        cervejaDto.setNome("Pilsen");

        return cervejaDto;
    }

    private static CervejaCreatePedidoDto getCervejaCreatePedidoDto() {
        CervejaCreatePedidoDto cervejaCreatePedidoDto = new CervejaCreatePedidoDto();
        cervejaCreatePedidoDto.setNome("Pilsen");
        cervejaCreatePedidoDto.setQuantidade(20);

        return cervejaCreatePedidoDto;
    }

}

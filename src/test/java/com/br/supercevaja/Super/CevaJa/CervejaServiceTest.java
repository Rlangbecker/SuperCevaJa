package com.br.supercevaja.Super.CevaJa;

import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaCreatePedidoDto;
import com.br.supercevaja.Super.CevaJa.dto.cervejaDto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.exception.RegraDeNegocioException;
import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.repository.CervejaRepository;
import com.br.supercevaja.Super.CevaJa.service.CervejaService;
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
public class CervejaServiceTest {
    @InjectMocks
    private CervejaService cervejaService;

    @Mock
    private CervejaRepository cervejaRepository;

    private ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init() {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        ReflectionTestUtils.setField(cervejaService, "objectMapper", objectMapper);
    }

    @Test
    public void deveTestarCreateComSucesso() throws RegraDeNegocioException {

        when(cervejaRepository.save(any()))
                .thenReturn(getCerveja());


        CervejaDto cervejaRetorno = cervejaService.cadastrarCerveja(getCervejaCreateDto());


        assertNotNull(cervejaRetorno);
        assertNotNull(cervejaRetorno.getIdCerveja());
        assertEquals(cervejaRetorno.getNome(), getCervejaCreateDto().getNome());
    }

    @Test
    public void deveTestarBuscarPorNomeDtoComSucesso() throws RegraDeNegocioException {

        when(cervejaRepository.findCervejaByNome(any()))
                .thenReturn(Optional.of(getCerveja()));

        CervejaDto cervejaRetorno = cervejaService.buscarPorNomeDto("Weiss");

        assertNotNull(cervejaRetorno);
        assertEquals(cervejaRetorno.getNome(), getCerveja().getNome());
    }

    @Test
    public void deveTestarCalcularValorTotal() throws RegraDeNegocioException {

        when(cervejaRepository.findCervejaByNome(any()))
                .thenReturn(Optional.of(getCerveja()));

        BigDecimal valorRetorno = cervejaService.calcularValorTotal(getCervejaCreatePedidoDto());

        assertNotEquals(0, valorRetorno);
    }

    @Test
    public void deveTestarBuscarPorIdComSucesso() throws RegraDeNegocioException {

        when(cervejaRepository.findById(any()))
                .thenReturn(Optional.of(getCerveja()));

        CervejaDto cervejaRetorno = cervejaService.buscarPorId(1);

        assertNotNull(cervejaRetorno);
        assertEquals(getCerveja().getIdCerveja(), cervejaRetorno.getIdCerveja());
    }

    @Test
    public void deveTestarAlterarPorNomeComSucesso() throws RegraDeNegocioException {
        Cerveja cervejaEditado = getCerveja();
        cervejaEditado.setNome("CervejaTest");

        when(cervejaRepository.findCervejaByNome(any()))
                .thenReturn(Optional.of(getCerveja()));
        when(cervejaRepository.save(any()))
                .thenReturn(cervejaEditado);

        CervejaDto cervejaRetorno = cervejaService.alterarPorNome("Cerveja", getCervejaCreateDto());

        assertNotNull(cervejaRetorno);
        assertEquals(cervejaRetorno.getNome(), cervejaEditado.getNome());

    }

    @Test
    public void deveTestarDeletarPorNomeComSucesso() throws RegraDeNegocioException {

        Cerveja cervejaEditado = getCerveja();
        cervejaEditado.setAtivo(false);

        when(cervejaRepository.findCervejaByNome(any()))
                .thenReturn(Optional.of(getCerveja()));

        when(cervejaRepository.save(any()))
                .thenReturn(cervejaEditado);

        cervejaService.deletarPorNome("cerveja");
    }

    @Test
    public void deveTestarRetornarTiposCervejaComSucesso() {

        Cerveja cerveja1 = getCerveja();
        Cerveja cerveja2 = getCerveja();
        cerveja2.setIdCerveja(2);

        List<Cerveja> cervejaList = new ArrayList<>();
        cervejaList.add(cerveja1);
        cervejaList.add(cerveja2);

        when(cervejaRepository.findAll())
                .thenReturn(cervejaList);

        List<CervejaDto> listaRetorno = cervejaService.retornarTiposCerveja();

        assertNotNull(listaRetorno);
    }


    private static CervejaCreateDto getCervejaCreateDto() {
        CervejaCreateDto cervejaCreateDto = new CervejaCreateDto();

        cervejaCreateDto.setNome("Pilsen");
        cervejaCreateDto.setValor(BigDecimal.valueOf(10));
        cervejaCreateDto.setQuantidade(100);

        return cervejaCreateDto;
    }

    private static CervejaCreatePedidoDto getCervejaCreatePedidoDto() {
        CervejaCreatePedidoDto cervejaCreatePedidoDto = new CervejaCreatePedidoDto();
        cervejaCreatePedidoDto.setNome("Pilsen");
        cervejaCreatePedidoDto.setQuantidade(20);

        return cervejaCreatePedidoDto;
    }

    private static Cerveja getCerveja() {
        Cerveja cerveja = new Cerveja();
        cerveja.setIdCerveja(1);
        cerveja.setAtivo(true);
        cerveja.setValor(BigDecimal.valueOf(10));
        cerveja.setQuantidade(100);
        cerveja.setNome("Pilsen");

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
}
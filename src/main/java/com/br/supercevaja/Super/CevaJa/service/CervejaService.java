package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.dto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.CervejaCreatePedidoDto;
import com.br.supercevaja.Super.CevaJa.dto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.model.enums.TipoCeva;
import com.br.supercevaja.Super.CevaJa.repository.CervejaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CervejaService {

    private final CervejaRepository cervejaRepository;
    private final ObjectMapper objectMapper;

    public CervejaDto cadastrarCerveja(TipoCeva tipoCeva, CervejaCreateDto cervejaCreateDto) {
        Cerveja cerveja = objectMapper.convertValue(cervejaCreateDto, Cerveja.class);
        cerveja.setTipoCerveja(tipoCeva);
        Cerveja cervejaReturn = cervejaRepository.save(cerveja);
        CervejaDto cervejaDtoRetorno = objectMapper.convertValue(cervejaReturn, CervejaDto.class);
        cervejaDtoRetorno.setTipoCeva(cervejaReturn.getTipoCerveja().getDescricao());
        return cervejaDtoRetorno;
    }

    public Cerveja buscarPorNome(String tipoCerveja) throws Exception {
        Cerveja cervejaRetorno = cervejaRepository.findCervejaByTipoCerveja(tipoCerveja)
                .orElseThrow(() -> new Exception("Cerveja não encontrada!"));
        return cervejaRetorno;
    }

    public BigDecimal calcularValorTotal(CervejaCreatePedidoDto cervejaCreatePedidoDto) throws Exception {
       Cerveja cerveja = buscarPorNome(cervejaCreatePedidoDto.getNome());
        BigDecimal valorTotal = new BigDecimal(cervejaCreatePedidoDto.getQuantidade())
                .multiply(cerveja.getValor());
        return valorTotal;
    }
    public CervejaDto buscarPorId(Integer id) throws Exception {
        Cerveja cervejaReturn = cervejaRepository.findById(id).
                orElseThrow(() -> new Exception("Id não encontrado"));
        CervejaDto cervejaDtoRetorno = objectMapper.convertValue(cervejaReturn, CervejaDto.class);
        cervejaDtoRetorno.setTipoCeva(cervejaReturn.getTipoCerveja().getDescricao());
        return cervejaDtoRetorno;
    }

    public CervejaDto alterarPorId(Integer id, CervejaCreateDto cervejaCreateDto) throws Exception {
        Cerveja cerveja = cervejaRepository.findById(id).orElseThrow(() -> new Exception("ID não encontrado"));
        cerveja.setNome(cervejaCreateDto.getNome());
        cerveja.setValor(cervejaCreateDto.getValor());
        cerveja.setQuantidade(cervejaCreateDto.getQuantidade());
        CervejaDto cevaReturn = objectMapper.convertValue(cervejaRepository.save(cerveja), CervejaDto.class);
        return cevaReturn;
    }

    public void deletarPorId(Integer id) throws Exception {
        cervejaRepository.findById(id)
                .orElseThrow(() -> new Exception("ID não encontrado"));
        cervejaRepository.deleteById(id);
    }
}

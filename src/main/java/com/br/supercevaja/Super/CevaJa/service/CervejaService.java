package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.dto.CervejaCreateDto;
import com.br.supercevaja.Super.CevaJa.dto.CervejaDto;
import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.repository.CervejaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CervejaService {

    private final CervejaRepository cervejaRepository;
    private final ObjectMapper objectMapper;

    public CervejaDto cadastrarCerveja(CervejaCreateDto cervejaCreateDto) {
        Cerveja cerveja = objectMapper.convertValue(cervejaCreateDto, Cerveja.class);
        Cerveja cervejaReturn = cervejaRepository.save(cerveja);
        return objectMapper.convertValue(cervejaReturn, CervejaDto.class);
    }

    public CervejaDto buscarPorId(Integer id) throws Exception {
        Cerveja cervejaReturn = cervejaRepository.findById(id).
                orElseThrow(() -> new Exception("Id não encontrado"));
        return objectMapper.convertValue(cervejaReturn, CervejaDto.class);
    }

    public CervejaDto alterarPorId(Integer id, CervejaCreateDto cervejaCreateDto) throws Exception {
        Cerveja cerveja = cervejaRepository.findById(id).orElseThrow(() -> new Exception("ID não encontrado"));
        cerveja.setNome(cervejaCreateDto.getNome());
        cerveja.setTipoCerveja(cervejaCreateDto.getTipoCeva());
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

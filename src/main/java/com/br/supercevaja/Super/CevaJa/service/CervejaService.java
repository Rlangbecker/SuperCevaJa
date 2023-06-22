package com.br.supercevaja.Super.CevaJa.service;

import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import com.br.supercevaja.Super.CevaJa.repository.CervejaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CervejaService {

    private final CervejaRepository cervejaRepository;

    public Cerveja cadastrarCerveja(Cerveja cerveja) {
        return cervejaRepository.save(cerveja);
    }

    public Optional<Cerveja> buscarPorId(Integer id) {
        return cervejaRepository.findById(id);
    }
}

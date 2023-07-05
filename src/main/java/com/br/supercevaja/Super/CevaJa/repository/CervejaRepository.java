package com.br.supercevaja.Super.CevaJa.repository;

import com.br.supercevaja.Super.CevaJa.model.Cerveja;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CervejaRepository extends JpaRepository<Cerveja, Integer> {
    Optional<Cerveja> findCervejaByTipoCerveja(String tipoCerveja);
}

package com.br.supercevaja.Super.CevaJa.repository;

import com.br.supercevaja.Super.CevaJa.model.Cargo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

    Cargo findCargoByNome(String nome);
}

package com.br.supercevaja.Super.CevaJa.repository;

import com.br.supercevaja.Super.CevaJa.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}

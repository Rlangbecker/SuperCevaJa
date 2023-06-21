package com.br.supercevaja.Super.CevaJa.model;


import com.br.supercevaja.Super.CevaJa.model.enums.TipoPayment;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idPedido;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idUsuario", referencedColumnName = "idUsuario")
    private Usuario usuario;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pedido")
    private List<Cerveja> cervejas;
    @Column
    private BigDecimal valorFinal;
    @Column
    private TipoPayment tipoPagamento;
}

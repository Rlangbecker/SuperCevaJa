package com.br.supercevaja.Super.CevaJa.model;

import com.br.supercevaja.Super.CevaJa.model.enums.TipoCerveja;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Cerveja {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idCerveja;
    @Column
    private String nome;
    @Column
    private TipoCerveja tipoCerveja;
    @Column
    private BigDecimal valor;
    @Column
    private Integer quantidade;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idPedido", referencedColumnName = "idPedido")
    private Pedido pedido;

}

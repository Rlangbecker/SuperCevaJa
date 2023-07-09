package com.br.supercevaja.Super.CevaJa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer idUsuario;

    @Column
    private String nome;

    @Column
    private String sobrenome;

    @Column
    private String cpf;
    @Column
    private String senha;
    @Column
    private String username;
    @Column
    private LocalDate dataNascimento;

    @Column
    private Boolean ativo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    private List<Pedido> pedidos;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "idLogin")
    private Login login;

}

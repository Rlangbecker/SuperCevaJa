package com.br.supercevaja.Super.CevaJa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Login {

    @Id
    @Column(name="ID_LOGIN")
    private Integer idLogin;

    @OneToOne(mappedBy = "login", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Usuario usuario;

    @Column
    private String senha;
}

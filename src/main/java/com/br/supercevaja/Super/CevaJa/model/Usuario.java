package com.br.supercevaja.Super.CevaJa.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Usuario implements UserDetails {

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

    @ManyToOne
    @JoinColumn(name = "ID_CARGO", referencedColumnName = "ID_CARGO")
    private Cargo cargo;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<Cargo> cargos = new HashSet<>();
        cargos.add(cargo);
        return cargos;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

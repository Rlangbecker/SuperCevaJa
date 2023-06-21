package com.br.supercevaja.Super.CevaJa.repository;

import com.br.supercevaja.Super.CevaJa.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByUsername(String username);

}

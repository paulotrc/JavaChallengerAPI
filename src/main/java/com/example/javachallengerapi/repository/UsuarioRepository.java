package com.example.javachallengerapi.repository;

import com.example.javachallengerapi.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "Select u from Usuario u where u.login = ?1 or u.nome = ?2 or u.email = ?3 ")
    Usuario usuarioExiste(String login, String nome, String email);

    @Query(value = "Select u from Usuario u where u.email = ?3 ")
    Usuario emailJaCadastrado(String email);
}

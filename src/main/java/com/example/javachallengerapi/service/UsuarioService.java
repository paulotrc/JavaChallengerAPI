package com.example.javachallengerapi.service;

import com.example.javachallengerapi.dto.TelefoneDto;
import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.UsuarioDuplicadoException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private static final String MSG_USUARIO_DUPLICADO_EXCEPTION = "Usuário com o login : %s ou Nome : %s ou email: %s já cadastrado.";

    public Optional<Usuario> findById(long id){
        Optional<Usuario> user = repository.findById(id);
        return user;
    };

    public UsuarioDtoResponse criarUsuario(UsuarioDto usuarioDto) throws UsuarioDuplicadoException, NoSuchAlgorithmException {
        Usuario usuario = null;
        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse();
        if(repository.usuarioExiste(usuarioDto.getLogin(), usuarioDto.getNome(), usuarioDto.getEmail()) == null){
            usuario = new Usuario(usuarioDto);
            repository.save(usuario);
            atualizaDadosUsuarioDto(usuarioDtoResponse, usuario);
        }else{
            throw new UsuarioDuplicadoException(String.format(MSG_USUARIO_DUPLICADO_EXCEPTION, usuarioDto.getLogin(), usuarioDto.getNome(), usuarioDto.getEmail()));
        }
        return usuarioDtoResponse;
    }

    private void atualizaDadosUsuarioDto(UsuarioDtoResponse usuarioDto, Usuario usuario){
        usuarioDto.setId(usuario.getId());
        usuarioDto.setLogin(usuario.getLogin());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setCreated(usuario.getCreated());
        usuarioDto.setLastLogin(usuario.getLastLogin());
        usuarioDto.setModified(usuario.getModified());
        usuarioDto.setToken(usuario.getToken());
        usuarioDto.setTelefoneDtoList(TelefoneDto.from(usuario.getTelefoneUsuarioList()));
    }
}

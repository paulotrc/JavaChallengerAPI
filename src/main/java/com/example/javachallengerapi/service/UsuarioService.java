package com.example.javachallengerapi.service;

import com.example.javachallengerapi.dto.TelefoneDto;
import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.JWTValidationException;
import com.example.javachallengerapi.exception.UsuarioDuplicadoException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.repository.UsuarioRepository;
import com.example.javachallengerapi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    private static final String MSG_USUARIO_DUPLICADO_EXCEPTION = "Usuário com email: %s já cadastrado.";

    public Optional<Usuario> findById(long id){
        Optional<Usuario> user = repository.findById(id);
        return user;
    };

    public UsuarioDtoResponse criarUsuario(UsuarioDto usuarioDto) throws UsuarioDuplicadoException, NoSuchAlgorithmException, JWTValidationException {
        Usuario usuario = null;
        UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse();
        if(repository.emailJaCadastrado(usuarioDto.getEmail()) == null){
            usuario = new Usuario(usuarioDto);
            String token = JWTUtil.gerarJWTUsuario(usuario);
            usuario.setToken(token);
            repository.save(usuario);
            atualizaDadosUsuarioDto(usuarioDtoResponse, usuario);
        }else{
            throw new UsuarioDuplicadoException(String.format(MSG_USUARIO_DUPLICADO_EXCEPTION, usuarioDto.getEmail()));
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

package com.example.javachallengerapi.service;

import com.example.javachallengerapi.dto.TelefoneDto;
import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.JWTValidationException;
import com.example.javachallengerapi.exception.UsuarioDuplicadoException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.repository.UsuarioRepository;
import com.example.javachallengerapi.util.JWTUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioService {

    @Inject
    private UsuarioRepository repository;

    private static final String MSG_USUARIO_DUPLICADO_EXCEPTION = "Usuário com email: %s já cadastrado.";

    /**
     * Consulta usuário pod ID
     * @param id identificador do usuário
     * @return Optional objeto de response de usuário
     */
    public Optional<Usuario> findById(long id){
        Optional<Usuario> user = repository.findById(id);
        if(user.get().getToken() != null){
            user.get().setToken(Base64.getDecoder().decode(user.get().getToken()));
        }
        return user;
    };

    /**
     * Cria novo registro de usuário
     * @param usuarioDto dados para criação do usuário
     * @return UsuarioDtoResponse objeto de response de usuário
     */
    public UsuarioDtoResponse criarUsuario(UsuarioDto usuarioDto) throws UsuarioDuplicadoException, NoSuchAlgorithmException, JWTValidationException, UnsupportedEncodingException {
        Usuario usuario = null;
        UsuarioDtoResponse usuarioDtoResponse = null;
        if(repository.consultaUsuarioPorEmail(usuarioDto.getEmail()) == null){
            usuario = new Usuario(usuarioDto);
            String token = JWTUtil.gerarJWTUsuario(usuario);
            usuario.setToken(Base64.getEncoder().encode(token.getBytes(StandardCharsets.UTF_8)));
            repository.save(usuario);
            usuarioDtoResponse = new UsuarioDtoResponse();
            atualizaDadosUsuarioDto(usuarioDtoResponse, usuario);
        }else{
            throw new UsuarioDuplicadoException(String.format(MSG_USUARIO_DUPLICADO_EXCEPTION, usuarioDto.getEmail()));
        }
        return usuarioDtoResponse;
    }

    protected void atualizaUsuario(Usuario usuario) throws JWTValidationException {
        usuario.setToken(Base64.getEncoder().encode(JWTUtil.gerarJWTUsuario(usuario).getBytes(StandardCharsets.UTF_8)));
        usuario.setLastLogin(new Date());
        usuario.setModified(new Date());
        repository.save(usuario);
    }

    protected void atualizaDadosUsuarioDto(UsuarioDtoResponse usuarioDto, Usuario usuario) throws UnsupportedEncodingException {
        usuarioDto.setId(usuario.getId());
        usuarioDto.setLogin(usuario.getLogin());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setCreated(usuario.getCreated());
        usuarioDto.setLastLogin(usuario.getLastLogin());
        usuarioDto.setModified(usuario.getModified());
        try {
            usuarioDto.setToken(usuario.getToken() != null ? new String(Base64.getDecoder().decode(usuario.getToken()), StandardCharsets.UTF_8) : "");
        }catch (IllegalArgumentException e){
            usuarioDto.setToken(usuario.getToken() != null ? new String(usuario.getToken(), StandardCharsets.UTF_8) : "");
        }
        usuarioDto.setTelefoneDtoList(TelefoneDto.from(usuario.getTelefoneUsuarioList()));
    }
}

package com.example.javachallengerapi.service;

import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.JWTValidationException;
import com.example.javachallengerapi.exception.NaoAutorizadoException;
import com.example.javachallengerapi.exception.SessaoInvalidaException;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.repository.UsuarioRepository;
import com.example.javachallengerapi.util.JWTUtil;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Optional;

@Service
public class PerfilService {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioRepository repository;


    public UsuarioDtoResponse consultaUsuarioPorId(String token, long id) throws NaoAutorizadoException, UsuarioNotFoundException, JWTValidationException, SessaoInvalidaException, UnsupportedEncodingException {
        if(token == null || token.isEmpty()){
            throw new NaoAutorizadoException("Não autorizado");
        }else {
            token = token.replace("Bearer ", "");
            Optional<Usuario> usuario = usuarioService.findById(id);
            if(!usuario.isPresent()){
                throw new UsuarioNotFoundException("Usuário não encontrado");
            }else{
                verificaValidadeTokenUsuario(token);
                verificaTokenUsuario(token, usuario.get());
                verificaSessaoUsuario(usuario);
                UsuarioDtoResponse usuarioDtoResponse = new UsuarioDtoResponse();
                usuarioService.atualizaDadosUsuarioDto(usuarioDtoResponse, usuario.get());
                return usuarioDtoResponse;
            }
        }
    }

    private void verificaSessaoUsuario(Optional<Usuario> usuario) throws SessaoInvalidaException {
        LocalDateTime dataHoraAgora = LocalDateTime.now();
        LocalDateTime ultimoLogin = usuario.get().getLastLogin().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        if(ultimoLogin.isBefore(dataHoraAgora.minusMinutes(30))){
            throw new SessaoInvalidaException("Sessão inválida");
        }
    }

    private void verificaTokenUsuario(String token, Usuario usuario) throws JWTValidationException, NaoAutorizadoException {
        if(!token.equals(new String(usuario.getToken()))){
            throw new NaoAutorizadoException("Não autorizado");
        }
    }

    private void verificaValidadeTokenUsuario(String token) throws JWTValidationException {
        JWTUtil.validarTokenJWT(token);
    }
}

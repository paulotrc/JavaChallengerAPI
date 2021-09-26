package com.example.javachallengerapi.service;

import com.example.javachallengerapi.dto.LoginDto;
import com.example.javachallengerapi.dto.TelefoneDto;
import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.JWTValidationException;
import com.example.javachallengerapi.exception.UsuarioDuplicadoException;
import com.example.javachallengerapi.exception.UsuarioInvalidoException;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.repository.UsuarioRepository;
import com.example.javachallengerapi.util.HashGen;
import com.example.javachallengerapi.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

@Service
public class LoginService {

    @Inject
    private UsuarioService usuarioService;

    @Inject
    private UsuarioRepository repository;

    private static final String MSG_USUARIO_INVALIDO_EXCEPTION = "Usuário e/ou senha inválidos";

    public UsuarioDtoResponse efetuaLogin(LoginDto loginDto) throws UsuarioInvalidoException, UsuarioNotFoundException, NoSuchAlgorithmException, JWTValidationException {
        UsuarioDtoResponse usuarioDtoResponse = null;
        Usuario usuario = repository.consultaUsuarioPorEmail(loginDto.getEmail());
        if(usuario == null){
            throw new UsuarioNotFoundException(MSG_USUARIO_INVALIDO_EXCEPTION);
        }else if(usuario != null && !senhaUsuarioValida(loginDto, usuario)){
            throw new UsuarioInvalidoException(MSG_USUARIO_INVALIDO_EXCEPTION);
        }else{
            usuarioDtoResponse = new UsuarioDtoResponse();
            usuario.setToken(JWTUtil.gerarJWTUsuario(usuario));
            usuarioService.atualizaDadosUsuarioDto(usuarioDtoResponse, usuario);
        }
        return usuarioDtoResponse;
    }

    private boolean senhaUsuarioValida(LoginDto loginDto, Usuario usuario) throws NoSuchAlgorithmException {
        String senhaLogin = HashGen.generateHash(loginDto.getSenha());

        if(senhaLogin.equals(usuario.getSenha())){
            return true;
        }else{
            return false;
        }
    }
}

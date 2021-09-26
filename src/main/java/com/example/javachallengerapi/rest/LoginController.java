package com.example.javachallengerapi.rest;


import com.example.javachallengerapi.dto.LoginDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.UsuarioDuplicadoException;
import com.example.javachallengerapi.exception.UsuarioInvalidoException;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.opendocs.LoginApiDocs;
import com.example.javachallengerapi.service.LoginService;
import com.example.javachallengerapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginApiDocs {

    @Autowired
    private LoginService service;

    @Override
    @PostMapping
    public ResponseEntity<? extends Serializable> login(LoginDto loginDto) {
        try {
            UsuarioDtoResponse usuarioResponse = service.efetuaLogin(loginDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDtoResponse.from(new Usuario(usuarioResponse)));
        }catch (UsuarioNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e);
        }catch (UsuarioInvalidoException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e);
        }catch (Exception e){
            UsuarioInvalidoException usuarioInvalidoException = new UsuarioInvalidoException(HttpStatus.BAD_REQUEST.toString(), e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuarioInvalidoException);
        }
    }
}
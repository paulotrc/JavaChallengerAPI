package com.example.javachallengerapi.rest;


import com.example.javachallengerapi.dto.LoginDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.ApiException;
import com.example.javachallengerapi.exception.UsuarioInvalidoException;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.opendocs.LoginApiDocs;
import com.example.javachallengerapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.logging.Logger;

@RestController
@RequestMapping("/login")
public class LoginController implements LoginApiDocs {

    @Autowired
    private LoginService service;

    private Logger log = Logger.getLogger(LoginController.class.getSimpleName());

    @Override
    @PostMapping
    public ResponseEntity<? extends Serializable> login(@RequestBody @Valid LoginDto loginDto) {
        try {
            UsuarioDtoResponse usuarioResponse = service.efetuaLogin(loginDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDtoResponse.from(new Usuario(usuarioResponse)));
        }catch (UsuarioNotFoundException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiException.create(e.getLocalizedMessage()));
        }catch (UsuarioInvalidoException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiException.create(e.getLocalizedMessage()));
        }catch (Exception e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiException.create(e.getLocalizedMessage()));
        }
    }
}
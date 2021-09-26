package com.example.javachallengerapi.rest;


import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.*;
import com.example.javachallengerapi.opendocs.PerfilApiDocs;
import com.example.javachallengerapi.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.logging.Logger;

@RestController
@RequestMapping("/perfil")
public class PerfilController implements PerfilApiDocs {

    @Autowired
    private PerfilService service;

    private Logger log = Logger.getLogger(PerfilController.class.getSimpleName());

    @Override
    @PostMapping("/{id}")
    public ResponseEntity<? extends Serializable> consultaPerfil(HttpServletRequest request, @PathVariable long id) {
        try {
            String authToken = request.getHeader("Authorization");
            UsuarioDtoResponse usuarioDtoResponse = service.consultaUsuarioPorId(authToken, id);
            return ResponseEntity.status(HttpStatus.OK).body(usuarioDtoResponse);
        }catch (NaoAutorizadoException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiException.create(e.getLocalizedMessage()));
        }catch (UsuarioNotFoundException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiException.create(e.getLocalizedMessage()));
        }catch (JWTValidationException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiException.create(e.getLocalizedMessage()));
        }catch (SessaoInvalidaException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ApiException.create(e.getLocalizedMessage()));
        }catch (Exception e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiException.create(e.getLocalizedMessage()));
        }
    }
}
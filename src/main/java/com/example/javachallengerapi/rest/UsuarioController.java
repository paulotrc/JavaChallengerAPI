package com.example.javachallengerapi.rest;


import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
import com.example.javachallengerapi.exception.ExceptionApi;
import com.example.javachallengerapi.exception.UsuarioDuplicadoException;
import com.example.javachallengerapi.exception.UsuarioInvalidoException;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import com.example.javachallengerapi.model.Usuario;
import com.example.javachallengerapi.opendocs.UsuarioApiDocs;
import com.example.javachallengerapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioApiDocs {

    @Autowired
    private UsuarioService service;

    private Logger log = Logger.getLogger(UsuarioController.class.getSimpleName());

    @GetMapping("/{id}")
    public ResponseEntity<? extends Serializable> findById(@PathVariable long id) throws UsuarioNotFoundException {
        Optional<Usuario> user = service.findById(id);
        if(user.isPresent()){
            return new ResponseEntity<UsuarioDto>(UsuarioDto.from(user.get()), HttpStatus.OK);
        }else{
            log.severe("Usuario com o ID: ["+ id +"] não encontrado");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ExceptionApi.create("Usuario não encontrado"));
        }
    }

    @PostMapping("/cadastro")
    public ResponseEntity<? extends Serializable> cadastro(@RequestBody @Valid UsuarioDto usuarioDto) {

        try {
            UsuarioDtoResponse usuarioResponse = service.criarUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDtoResponse.from(new Usuario(usuarioResponse)));
        }catch (UsuarioDuplicadoException e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ExceptionApi.create(e.getLocalizedMessage()));
        }catch (Exception e){
            log.severe(e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ExceptionApi.create(e.getLocalizedMessage()));
        }
   }
}
package com.example.javachallengerapi.rest;


import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.dto.UsuarioDtoResponse;
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

@RestController
@RequestMapping("/usuario")
public class UsuarioController implements UsuarioApiDocs {

    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<? extends Serializable> findById(@PathVariable long id) throws UsuarioNotFoundException {
        Optional<Usuario> user = service.findById(id);
        user.get().getId();
        if(user.isPresent()){
            return new ResponseEntity<UsuarioDto>(UsuarioDto.from(user.get()), HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new UsuarioNotFoundException("Usuario não encontrado"));
    }

    @PostMapping("/cadastro")
    public ResponseEntity<? extends Serializable> cadastro( @RequestBody @Valid UsuarioDto usuarioDto) {

        try {
            UsuarioDtoResponse usuarioResponse = service.criarUsuario(usuarioDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioDtoResponse.from(new Usuario(usuarioResponse)));
        }catch (UsuarioDuplicadoException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e);
        }catch (Exception e){
            UsuarioInvalidoException usuarioInvalidoException = new UsuarioInvalidoException(HttpStatus.BAD_REQUEST.toString(), e.getLocalizedMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(usuarioInvalidoException);
        }
   }
}
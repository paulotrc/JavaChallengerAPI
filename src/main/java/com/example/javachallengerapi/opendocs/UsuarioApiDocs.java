package com.example.javachallengerapi.opendocs;

import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.Serializable;

@Tag(name = "Usuário", description = "Endpoint API de operações com o Usuário")
@OpenAPIDefinition(info =
    @Info(title = "Java Challenger API", version = "1.0", description = "Documentação da API Java Challenger v1.0")
)
public interface UsuarioApiDocs {

    @Operation(summary = "Buscar Usuário pelo ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário recuperado com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "ID do usuário inválido", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<? extends Serializable> findById(@PathVariable long id) throws UsuarioNotFoundException;

    @Operation(summary = "Cadastrar novo usuário.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Dados do usuário inválidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<? extends Serializable> cadastro(UsuarioDto usuarioDto);

}

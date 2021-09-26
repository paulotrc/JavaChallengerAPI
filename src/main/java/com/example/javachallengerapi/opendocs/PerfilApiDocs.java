package com.example.javachallengerapi.opendocs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Tag(name = "Perfil", description = "Endpoint API de operações de Perfil")
public interface PerfilApiDocs extends OpenApiDef {

    @Operation(summary = "Operação de Login do Usuário com E-Mail e Senha previamente cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Usuário e/ou senha inválidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Não autorizado", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<? extends Serializable> consultaPerfil(String token, long id);

}

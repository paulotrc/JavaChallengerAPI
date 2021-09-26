package com.example.javachallengerapi.opendocs;

import com.example.javachallengerapi.dto.LoginDto;
import com.example.javachallengerapi.exception.UsuarioNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Tag(name = "Login", description = "Endpoint API de operações de Login")
public interface LoginApiDocs extends OpenApiDef{

    /**
     * Efetua o login do usuário
     * @param logindto dados para efetuar o login do usuário
     * @return ResponseEntity objeto de response da API
     */
    @Operation(summary = "Operação de Login do Usuário com E-Mail e Senha previamente cadastradas.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login efetuado com sucesso.", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "Usuário e/ou senha inválidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "401", description = "Usuário e/ou senha inválidos", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado.", content = @Content(mediaType = "application/json"))
    })
    ResponseEntity<? extends Serializable> login(LoginDto logindto) throws UsuarioNotFoundException;

}

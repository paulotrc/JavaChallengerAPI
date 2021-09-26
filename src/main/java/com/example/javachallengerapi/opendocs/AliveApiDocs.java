package com.example.javachallengerapi.opendocs;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Tag(name = "Alive", description = "Endpoint API para verificação se a API está executando.")
public interface AliveApiDocs extends OpenApiDef {

    /**
     * Verifica se a API está ativa
     * @return ResponseEntity objeto de response da API
     */
    @Operation(summary = "Verifica se a API está ativa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Alive", content = {
                    @Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Conteúdo não encontrado", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<? extends Serializable> isAlive();


}

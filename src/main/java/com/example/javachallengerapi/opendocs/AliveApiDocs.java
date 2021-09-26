package com.example.javachallengerapi.opendocs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

@Tag(name = "Alive", description = "Endpoint API para verificação se a API está executando.")
@OpenAPIDefinition(info =
    @Info(title = "Java Challenger API", version = "1.0", description = "Documentação da API Java Challenger v1.0")
)

public interface AliveApiDocs {

    @Operation(summary = "Verifica se a API está ativa.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get Alive", content = {
                    @Content(mediaType = "application/json")}),
            //          @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Foo.class)))}),
            @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "Conteúdo não encontrado", content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<? extends Serializable> isAlive();


}

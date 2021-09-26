package com.example.javachallengerapi.rest;


import com.example.javachallengerapi.dto.AliveDto;
import com.example.javachallengerapi.opendocs.AliveApiDocs;
import io.swagger.v3.core.util.Json;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
@RequestMapping("/alive")
public class AliveController implements AliveApiDocs {

    private final AliveDto resposta = new AliveDto("API ativa!");

    @GetMapping
    public ResponseEntity<? extends Serializable> isAlive() {
        return ResponseEntity.ok().body(resposta);
    }
}
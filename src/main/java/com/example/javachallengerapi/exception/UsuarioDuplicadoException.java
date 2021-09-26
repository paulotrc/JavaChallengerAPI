package com.example.javachallengerapi.exception;

import lombok.Data;

@Data
public class UsuarioDuplicadoException extends Exception {

    private String codigo;
    private String errorMessage;

    public UsuarioDuplicadoException(String errorMessage) {
        super(errorMessage);
    }
    public UsuarioDuplicadoException(String codigo, String errorMessage) {
        super(errorMessage);
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

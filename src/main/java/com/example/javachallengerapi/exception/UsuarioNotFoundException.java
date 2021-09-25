package com.example.javachallengerapi.exception;

import lombok.Data;

@Data
public class UsuarioNotFoundException extends Exception {

    private String codigo;
    private String errorMessage;

    public UsuarioNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public UsuarioNotFoundException(String codigo, String errorMessage) {
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

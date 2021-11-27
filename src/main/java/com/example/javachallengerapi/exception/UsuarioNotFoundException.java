package com.example.javachallengerapi.exception;

import lombok.Getter;
import lombok.Setter;

public class UsuarioNotFoundException extends Exception {

    private String codigo;
    private String errorMessage;

    public UsuarioNotFoundException(String errorMessage) {
        super(errorMessage);
    }
    public UsuarioNotFoundException(String codigo, String errorMessage) {
        super(errorMessage);
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

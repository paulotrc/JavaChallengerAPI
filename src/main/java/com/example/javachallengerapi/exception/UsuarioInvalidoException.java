package com.example.javachallengerapi.exception;

import lombok.Data;

@Data
public class UsuarioInvalidoException extends Exception {

    private String codigo;
    private String errorMessage;

    public UsuarioInvalidoException(String errorMessage) {
        super(errorMessage);
    }
    public UsuarioInvalidoException(String codigo, String errorMessage) {
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

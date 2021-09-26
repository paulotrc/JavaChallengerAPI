package com.example.javachallengerapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UsuarioInvalidoException extends Exception {

    private String codigo;
    private String errorMessage;

    public UsuarioInvalidoException(String errorMessage) {
        super(errorMessage);
    }
    public UsuarioInvalidoException(String codigo, String errorMessage) {
        super(errorMessage);
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

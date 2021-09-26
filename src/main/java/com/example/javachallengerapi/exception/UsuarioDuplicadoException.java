package com.example.javachallengerapi.exception;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
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

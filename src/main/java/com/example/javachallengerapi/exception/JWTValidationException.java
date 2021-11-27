package com.example.javachallengerapi.exception;

import lombok.Getter;
import lombok.Setter;

public class JWTValidationException extends Exception {

    private String codigo;
    private String errorMessage;

    public JWTValidationException(String errorMessage) {
        super(errorMessage);
    }
    public JWTValidationException(String codigo, String errorMessage) {
        super(errorMessage);
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

package com.example.javachallengerapi.exception;

import lombok.Data;

@Data
public class JWTValidationException extends Exception {

    private String codigo;
    private String errorMessage;

    public JWTValidationException(String errorMessage) {
        super(errorMessage);
    }
    public JWTValidationException(String codigo, String errorMessage) {
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

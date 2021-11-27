package com.example.javachallengerapi.exception;

public class SessaoInvalidaException extends Exception {

    private String codigo;
    private String errorMessage;

    public SessaoInvalidaException(String errorMessage) {
        super(errorMessage);
    }
    public SessaoInvalidaException(String codigo, String errorMessage) {
        super(errorMessage);
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

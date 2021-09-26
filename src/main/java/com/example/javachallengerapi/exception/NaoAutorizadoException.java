package com.example.javachallengerapi.exception;

import lombok.Data;

@Data
public class NaoAutorizadoException extends Exception {

    private String codigo;
    private String errorMessage;

    public NaoAutorizadoException(String errorMessage) {
        super(errorMessage);
    }
    public NaoAutorizadoException(String codigo, String errorMessage) {
        super(errorMessage);
        this.codigo = codigo;
        this.errorMessage = errorMessage;
    }
}

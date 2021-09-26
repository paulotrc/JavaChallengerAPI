package com.example.javachallengerapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class ExceptionApi implements Serializable {

    private String mensagem;

    public static ExceptionApi create(String localizedMessage) {
        ExceptionApi exceptionApi = new ExceptionApi(localizedMessage);
        return exceptionApi;
    }
}

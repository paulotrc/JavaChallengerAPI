package com.example.javachallengerapi.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
public class ApiException implements Serializable {

    private String mensagem;

    public static ApiException create(String localizedMessage) {
        ApiException apiException = new ApiException(localizedMessage);
        return apiException;
    }
}

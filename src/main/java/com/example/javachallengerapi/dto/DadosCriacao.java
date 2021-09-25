package com.example.javachallengerapi.dto;

import lombok.Data;

import java.util.Date;

@Data
public class DadosCriacao {

    private Date created;

    private Date modified;

    private Date lastLogin;

    private String token;



}

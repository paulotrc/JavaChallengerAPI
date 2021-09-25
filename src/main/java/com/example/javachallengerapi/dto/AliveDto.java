package com.example.javachallengerapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class AliveDto implements Serializable {

    private String status;

}

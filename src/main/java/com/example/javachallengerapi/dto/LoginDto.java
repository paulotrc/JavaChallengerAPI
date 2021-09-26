package com.example.javachallengerapi.dto;

import com.example.javachallengerapi.model.Usuario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class LoginDto implements Serializable {

    @Pattern(regexp = ".+@.+\\..+", message = "Por favor informar um endereço de e-mail válido")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 0, max = 20)
    private String senha;

}

package com.example.javachallengerapi.dto;

import com.example.javachallengerapi.model.Usuario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
public class UsuarioDto implements Serializable {

    private Long id;

    @NotBlank
    @Size(min = 0, max = 250)
    private String nome;

    @Pattern(regexp = ".+@.+\\..+", message = "Por favor informar um endereço de e-mail válido")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 0, max = 250)
    private String login;

    @NotBlank
    @Size(min = 0, max = 20)
    private String senha;

    private List<TelefoneDto> telefoneDtoList;

    public static UsuarioDto from(Usuario usuario) {
        UsuarioDto dto = new UsuarioDto();
        dto.setLogin(usuario.getLogin());
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefoneDtoList(TelefoneDto.from(usuario.getTelefoneUsuarioList()));
        return dto;
    }
}

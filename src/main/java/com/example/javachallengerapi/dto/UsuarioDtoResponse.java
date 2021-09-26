package com.example.javachallengerapi.dto;

import com.example.javachallengerapi.model.Usuario;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Data
public class UsuarioDtoResponse extends DadosCriacao implements Serializable {

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

    @Override
    public Date getCreated() {
        return super.getCreated();
    }

    @Override
    public Date getModified() {
        return super.getModified();
    }

    @Override
    public Date getLastLogin() {
        return super.getLastLogin();
    }

    @Override
    public String getToken() {
        return super.getToken();
    }

    public static UsuarioDtoResponse from(Usuario usuario) {
        UsuarioDtoResponse dto = new UsuarioDtoResponse();
        dto.setLogin(usuario.getLogin());
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setToken(usuario.getToken() != null ? new String(Base64.getDecoder().decode(usuario.getToken()), StandardCharsets.UTF_8) : "");
        dto.setEmail(usuario.getEmail());
        dto.setCreated(usuario.getCreated());
        dto.setModified(usuario.getModified());
        dto.setLastLogin(usuario.getLastLogin());
        dto.setTelefoneDtoList(TelefoneDto.from(usuario.getTelefoneUsuarioList()));
        return dto;
    }
}

package com.example.javachallengerapi.model;

import com.example.javachallengerapi.dto.TelefoneDto;
import com.example.javachallengerapi.dto.UsuarioDto;
import com.example.javachallengerapi.util.HashGen;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "TB_USUARIO")
@Data
@NoArgsConstructor
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "login")
    private String login;

    @Column(name = "senha")
    private String senha;

    @Column(name = "email")
    private String email;

    @Column(name = "ts_created")
    private Date created;

    @Column(name = "ts_modified")
    private Date modified;

    @Column(name = "ts_last_login")
    private Date lastLogin;

    @Lob
    @Column(name = "token")
    private byte[] token;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<TelefoneUsuario> telefoneUsuarioList;

    /**
     * Construtor para criação de usuário novo
     * @param usuarioDto
     */
    public Usuario(UsuarioDto usuarioDto) throws NoSuchAlgorithmException {
        this.login = usuarioDto.getLogin();
        this.nome = usuarioDto.getNome();
        this.senha = HashGen.generateHash(usuarioDto.getSenha());
        this.email = usuarioDto.getEmail();
        this.created = new Date();
        this.telefoneUsuarioList = new ArrayList<>();
        this.preencheTelefones(usuarioDto.getTelefoneDtoList());
    }

    private void preencheTelefones(List<TelefoneDto> telefoneDtoList) {
        if(this.telefoneUsuarioList == null){
            this.telefoneUsuarioList = new ArrayList<>();
        }
        if(telefoneDtoList != null){
            for (TelefoneDto dto: telefoneDtoList) {
                TelefoneUsuario telefoneUsuario = new TelefoneUsuario(dto);
                telefoneUsuario.setUsuario(this);
                telefoneUsuarioList.add(telefoneUsuario);
            }
        }
    }
}

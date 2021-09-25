package com.example.javachallengerapi.model;

import com.example.javachallengerapi.dto.TelefoneDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "TB_TELEFONE_USUARIO")
@Data
@NoArgsConstructor
public class TelefoneUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "numero")
    private long numero;

    @Column(name = "ddd")
    private int ddd;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public TelefoneUsuario(TelefoneDto dto) {
        this.ddd = dto.getDdd();
        this.numero = dto.getNumero();
    }
}

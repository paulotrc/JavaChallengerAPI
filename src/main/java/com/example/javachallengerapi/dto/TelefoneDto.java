package com.example.javachallengerapi.dto;

import com.example.javachallengerapi.model.TelefoneUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TelefoneDto {

    private long numero;

    private int ddd;

    public static TelefoneDto from(TelefoneUsuario telefoneUsuario) {
        TelefoneDto dto = new TelefoneDto();
            dto.ddd = telefoneUsuario.getDdd();
            dto.numero = telefoneUsuario.getNumero();
        return dto;
    }

    public static List<TelefoneDto> from(List<TelefoneUsuario> telefoneUsuarioList) {
        List<TelefoneDto> telefoneDtoList = new ArrayList<>();
        for (TelefoneUsuario tu : telefoneUsuarioList) {
            telefoneDtoList.add(TelefoneDto.from(tu));
        }
        return telefoneDtoList;
    }
}

package com.dbc.colabore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilDTO {
    private Integer idPerfil;
    private String nome;
    private String descricao;

}

package com.dbc.colabore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public enum TipoUsuario {
    Campanha(0),
    Colaborador(1);

    private Integer tipo;
}

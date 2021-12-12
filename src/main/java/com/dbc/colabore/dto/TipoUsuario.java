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
    Criador(1),
    Colaborador(2);

    private Integer tipo;
}

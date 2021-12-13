package com.dbc.colabore.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CategoriaCreateDTO {

    @NotNull
    private Integer idCategoria;
}

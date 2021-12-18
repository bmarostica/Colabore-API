package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data

public class CategoriaCreateDTOComNome {

    @NotBlank
    @NotNull
    @ApiModelProperty(value = "Nome da categoria")
    private String nome;

}

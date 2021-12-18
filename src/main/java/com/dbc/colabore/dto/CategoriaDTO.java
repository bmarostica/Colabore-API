package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
public class CategoriaDTO extends CategoriaCreateDTO{

    @ApiModelProperty(value = "Id da categoria")
    private Integer idCategoria;
    @ApiModelProperty(value = "Nome da categoria")
    private String nome;


}

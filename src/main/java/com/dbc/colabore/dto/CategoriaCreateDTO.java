package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CategoriaCreateDTO {

    @ApiModelProperty(value = "Id da categoria")
    @NotNull
    @NotBlank
    private Integer idCategoria;

}

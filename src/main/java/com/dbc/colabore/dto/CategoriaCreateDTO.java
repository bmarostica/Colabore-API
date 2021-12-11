package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaCreateDTO {

    @NotBlank
    @ApiModelProperty(value = "Nome da Categoria")
    private String nome;
}

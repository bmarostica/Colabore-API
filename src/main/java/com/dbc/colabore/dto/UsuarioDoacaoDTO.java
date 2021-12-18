package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDoacaoDTO {

    @ApiModelProperty(value = "Id do usuário")
    private int idUsuario;

    @ApiModelProperty(value = "Nome do usuário")
    private String nome;

    @ApiModelProperty(value = "E-mail do usuário")
    private String email;

    @ApiModelProperty(value = "Valor total doado pelo usuário")
    private BigDecimal valorTotalDoado;
}

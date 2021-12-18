package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class DoacaoDTO {

    @ApiModelProperty(value = "Id da doação")
    private Integer idDoacao;

    @ApiModelProperty(value = "Valor da doação")
    private BigDecimal valor;

    @ApiModelProperty(value = "Id do usuário")
    private UsuarioDTO usuario;

    @ApiModelProperty(value = "Id da campanha")
    private CampanhaDTO campanhaDTO;
}

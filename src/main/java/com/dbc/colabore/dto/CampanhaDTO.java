package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class CampanhaDTO extends CampanhaCreateDTO{

    @ApiModelProperty(value = "Id da campanha")
    private Integer idCampanha;

    @ApiModelProperty(value = "Criador da campanha")
    private UsuarioDTO criadorCampanha;

    @ApiModelProperty(value = "Total arrecadado na campanha")
    private BigDecimal totalArrecadado;

    @ApiModelProperty(value = "Última alteração feita na campanha")
    private LocalDateTime ultimaAlteracao;

    @ApiModelProperty(value = "Campanha se encontra aberta ou fechada? true = aberta, false = fechada")
    private Boolean statusCampanha;
}

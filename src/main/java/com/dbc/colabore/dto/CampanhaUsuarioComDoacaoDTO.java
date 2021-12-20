package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class CampanhaUsuarioComDoacaoDTO extends CampanhaCreateDTO{

    @ApiModelProperty(value = "Id da campanha")
    private Integer idCampanha;

    @ApiModelProperty(value = "Criador da campanha")
    private UsuarioDTO criadorDaCampanha;

    @ApiModelProperty(value = "Total arrecadado na campanha")
    private BigDecimal totalArrecadado;

    @ApiModelProperty(value = "Última alteração feita na campanha")
    private LocalDateTime ultimaAlteracao;

    @ApiModelProperty(value = "Campanha se encontra aberta ou fechada? true = aberta, false = fechada")
    private Boolean statusCampanha;

    @ApiModelProperty(value = "Meta se está atingida true = sim, false= não")
    private Boolean metaAtingida;

    @ApiModelProperty(value = "Mostra o valor total doado na campanha pelo usuário")
    private UsuarioDoacaoDTO usuarioDoacaoDTO;
}

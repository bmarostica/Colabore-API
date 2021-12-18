package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class DoacaoCreateDTO {
    @NotNull
    @ApiModelProperty(value = "Valor da doação")
    private BigDecimal valor;
    @NotNull
    @ApiModelProperty(value = "Id da campanha")
    private Integer idCampanha;
}

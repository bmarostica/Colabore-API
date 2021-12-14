package com.dbc.colabore.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DoacaoCreateDTO {
    @NotNull
    private BigDecimal valor;
    @NotNull
    private Integer idCampanha;
}

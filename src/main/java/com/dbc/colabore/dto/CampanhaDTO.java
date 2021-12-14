package com.dbc.colabore.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class CampanhaDTO extends CampanhaCreateDTO{

    private Integer idCampanha;
    private UsuarioDTO criadorCampanha;
    private BigDecimal totalArrecadado;
    private LocalDateTime ultimaAlteracao;
    private Boolean statusCampanha;
}

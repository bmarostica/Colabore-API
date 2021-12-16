package com.dbc.colabore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class DoacaoDTO {

    private Integer idDoacao;
    private BigDecimal valor;
    private UsuarioDTO usuario;
    private CampanhaDTO campanhaDTO;
}

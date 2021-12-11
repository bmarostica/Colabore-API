package com.dbc.colabore.dto;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaDTO extends CampanhaCreateDTO{

    private Integer idCampanha;
    private UsuarioDTO criadorCampanha;
    private BigDecimal totalArrecadado;
    private List<UsuarioDTO> usuariosContribuidores;
    private LocalDateTime ultimaAlteracao;
    private Boolean statusCampanha;

}

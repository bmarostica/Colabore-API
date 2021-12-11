package com.dbc.colabore.dto;

import com.dbc.colabore.entity.UsuarioEntity;
import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaDTO extends CampanhaCreateDTO{

    private Integer idCampanha;
    private BigDecimal totalArrecadado;
    private List<UsuarioEntity> usuariosContribuidores;
    private LocalDateTime ultimaAlteracao;





}

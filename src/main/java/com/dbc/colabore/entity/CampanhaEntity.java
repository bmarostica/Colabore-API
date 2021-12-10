package com.dbc.colabore.entity;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class CampanhaEntity {

    private Integer idCampanha;
    private String foto;
    private String tituloCampanha;
    private BigDecimal metaArrecadacao;
    private BigDecimal totalArrecadado;
    private List<String> tagsCategoria;
    private LocalDateTime ultimaAlteracao;
    private StatusCampanha statusCampanha;
    private String descricaoCampanha;
    private UsuarioEntity criadorCampanha;
    private List<UsuarioEntity> usuariosContribuidores;



}

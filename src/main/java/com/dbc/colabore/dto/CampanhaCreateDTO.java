package com.dbc.colabore.dto;

import com.dbc.colabore.entity.StatusCampanha;
import com.dbc.colabore.entity.UsuarioEntity;

import java.math.BigDecimal;
import java.util.List;

public class CampanhaCreateDTO {

    private String foto;
    private String tituloCampanha;
    private BigDecimal metaArrecadacao;
    private List<String> tagsCategoria;
    private StatusCampanha statusCampanha;
    private String descricaoCampanha;
    private UsuarioEntity criadorCampanha;

}

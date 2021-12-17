package com.dbc.colabore.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CampanhaDetalheDTO{

    private Integer idCampanha;
    private String tituloCampanha;
    private String descricaoCampanha;
    private BigDecimal metaArrecadacao;
    private LocalDate dataLimiteContribuicao;
    private Boolean concluiCampanhaAutomaticamente;

    private Boolean criadorCampanha;
    private Set<CategoriaDTO> tagsCategoria;
    private Set<UsuarioDoacaoDTO> usuarioDoacaoDTOS;
}

package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CampanhaDetalheDTO{

    @ApiModelProperty(value = "Id da campanha")
    private Integer idCampanha;

    @ApiModelProperty(value = "Título da campanha")
    private String tituloCampanha;

    @ApiModelProperty(value = "Descrição da campanha")
    private String descricaoCampanha;

    @ApiModelProperty(value = "Meta de arrecadação da campanha")
    private BigDecimal metaArrecadacao;

    @ApiModelProperty(value = "Data limite de contribuição")
    private LocalDate dataLimiteContribuicao;

    @ApiModelProperty(value = "Define se a campanha deve ser encerrada automaticamente ao atingir a meta TRUE = SIM, FALSE = NÃO")
    private Boolean concluiCampanhaAutomaticamente;

    @ApiModelProperty(value = "Criador da campanha")
    private Boolean criadorCampanha;

    @ApiModelProperty(value = "Categorias da campanha")
    private Set<CategoriaDTO> tagsCategoria;

    @ApiModelProperty(value = "Usuário que realizaram doação para a campanha")
    private Set<UsuarioDoacaoDTO> usuarioDoacaoDTOS;
}

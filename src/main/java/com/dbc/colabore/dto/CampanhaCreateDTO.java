package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Data
public class CampanhaCreateDTO {

    @NotBlank
    @ApiModelProperty(value = "Título da Campanha")
    private String tituloCampanha;

    @NotNull
    @ApiModelProperty(value = "Meta de Arrecadação")
    private BigDecimal metaArrecadacao;

    @NotBlank
    @ApiModelProperty(value = "Descrição da Campanha")
    private String descricaoCampanha;

    @NotNull
    @ApiModelProperty(value = "Categorias da Campanha")
    private Set<CategoriaCreateDTO> categorias;

    @NotBlank
    @ApiModelProperty(value = "Foto da Campanha")
    private byte[] foto;

    @Future
    @NotNull
    @ApiModelProperty(value = "Encerramento da campanha")
    private LocalDate dataLimiteContribuicao;


    @NotNull
    @ApiModelProperty(value = "Define se qa campanha deve ser encerrada automaticamente ao atingir a meta")
    private Boolean concluiCampanhaAutomaticamente;

}

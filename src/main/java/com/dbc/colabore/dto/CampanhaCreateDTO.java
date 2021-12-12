package com.dbc.colabore.dto;

import com.dbc.colabore.entity.CategoriaEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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

    @NotBlank
    @ApiModelProperty(value = "Foto da Campanha")
    private String foto;

    @Future
    @NotNull
    @ApiModelProperty(value = "Encerramento da campanha")
    private LocalDate dataLimiteContribuicao;

    @NotNull
    @ApiModelProperty(value = "Define se qa campanha deve ser encerrada automaticamente ao atingir a meta")
    private Boolean concluiCampanhaAutomaticamente;

}

package com.dbc.colabore.dto;

import com.dbc.colabore.entity.StatusCampanha;
import com.dbc.colabore.entity.UsuarioEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampanhaCreateDTO {

    @NotBlank
    @ApiModelProperty(value = "Foto da Campanha")
    private String foto;

    @NotBlank
    @ApiModelProperty(value = "Título da Campanha")
    private String tituloCampanha;

    @NotNull
    @ApiModelProperty(value = "Meta de Arrecadação")
    private BigDecimal metaArrecadacao;

    @NotBlank
    @ApiModelProperty(value = "Categorias")
    private List<String> tagsCategoria;

    @NotNull
    @ApiModelProperty(value = "Status da Campanha")
    private StatusCampanha statusCampanha;

    @NotBlank
    @ApiModelProperty(value = "Descrição da Campanha")
    private String descricaoCampanha;

    @NotNull
    @ApiModelProperty(value = "Criador da Campanha")
    private UsuarioEntity criadorCampanha;

}

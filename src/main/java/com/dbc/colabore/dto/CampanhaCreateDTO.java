package com.dbc.colabore.dto;

import com.dbc.colabore.entity.CategoriaEntity;
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

    @NotNull
    @ApiModelProperty(value = "Categorias")
    private List<CategoriaDTO> tagsCategoria;

}

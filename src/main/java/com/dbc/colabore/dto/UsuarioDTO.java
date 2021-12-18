package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioDTO {

    @NotNull(message = "Não pode ser null")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value= "id usuario")
    private Integer idUsuario;


    @NotNull(message = "Não pode ser null")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value= "Nome do usuario")
    private String nome;

    @NotNull(message = "Não pode ser null")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value= "E-mail")
    private String email;

}

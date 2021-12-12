package com.dbc.colabore.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UsuarioCreateDTO {

    @NotNull(message = "Não pode ser null")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value= "Nome do usuario")
    private String nome;

    @NotNull(message = "Não pode ser null")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value= "E-mail")
    private String email;

    @NotNull(message = "Não pode ser null")
    @NotBlank(message = "Não pode estar em branco")
    @ApiModelProperty(value= "Senha")
    private String senha;

    private String fotoPerfil;


    private List<Integer> perfis;
}

package com.dbc.colabore.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilCreateDTO {
    @NotEmpty
    @NotBlank
    private String login;
    @NotEmpty
    @NotBlank
    private String senha;
    @NotEmpty
    private List<Integer> regras;
}

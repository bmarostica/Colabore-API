package com.dbc.colabore.Controller;

import com.dbc.colabore.dto.UsuarioCreateDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/usuario")
@Validated
@Slf4j
@RequiredArgsConstructor
public class UsuarioController {

    @ApiOperation(value = "Cria um novo usuario")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario Criado com Sucesso"),
            @ApiResponse(code = 400, message = "Algum dado Inconsistente"),
            @ApiResponse(code = 500, message = "Foi Gerada uma Exceção"),
    })
    @PostMapping
    public UsuarioDTO create(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {

        return null;
    }

}

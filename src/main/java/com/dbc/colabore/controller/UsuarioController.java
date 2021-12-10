package com.dbc.colabore.controller;

import com.dbc.colabore.dto.UsuarioCreateDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.service.UsuarioService;
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
    private final UsuarioService usuarioService;

    @ApiOperation(value = "Cria um novo usuario")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario Criado com Sucesso"),
            @ApiResponse(code = 400, message = "Algum dado Inconsistente"),
            @ApiResponse(code = 500, message = "Foi Gerada uma Exceção"),
    })
    @PostMapping
    public UsuarioDTO create(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) {
        log.info("Criando usuario");
        UsuarioDTO usuarioDTO = usuarioService.create(usuarioCreateDTO);
        log.info("Usuario criado com sucesso");
        return usuarioDTO;
    }

}

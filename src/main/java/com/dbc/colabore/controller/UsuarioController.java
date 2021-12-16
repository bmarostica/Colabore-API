package com.dbc.colabore.controller;

import com.dbc.colabore.dto.UsuarioCreateDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.UsuarioService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public UsuarioDTO create(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException {
        log.info("Criando usuario");
        UsuarioDTO usuarioDTO = usuarioService.create(usuarioCreateDTO);
        log.info("Usuario criado com sucesso");
        return usuarioDTO;
    }

    @GetMapping
    public UsuarioDTO getUsuarioLogado() throws RegraDeNegocioException{
       return usuarioService.getUsuarioLogado();
    }

    @ApiOperation(value = "Atualiza o usuario logado")
    @ApiResponses(value ={
            @ApiResponse(code = 200, message = "Usuario atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Algum dado inconsistente"),
            @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
    })
    @PutMapping
    public UsuarioDTO update(@RequestBody @Valid UsuarioCreateDTO usuarioCreateDTO) throws RegraDeNegocioException{
        log.info("Atualizar usuario");
        UsuarioDTO usuarioDTO = usuarioService.update(usuarioCreateDTO);
        log.info("Usuario atualizado");
        return usuarioDTO;
    }


}

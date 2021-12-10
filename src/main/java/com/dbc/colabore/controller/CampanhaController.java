package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/campanha")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CampanhaController {


    @ApiOperation("Cria uma nova campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha criada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PostMapping
    public CampanhaDTO create(@RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }


    @ApiOperation("Atualiza uma campanha existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/{id}")
    public CampanhaDTO update(@PathVariable("id") Integer id, @RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }

    @ApiOperation("Atualiza uma foto de capa existente através do registro da foto.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/{foto}")
    public CampanhaDTO updateFotoDeCapa(@PathVariable("foto") String foto, @RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }

//    @ApiOperation("Mostra uma lista com todas as campanhas.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
//            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
//    })
//    @GetMapping
//    public List<CampanhaDTO> list(){
//        return null;
//    }

//    @ApiOperation("Retorna uma lista de campanhas concluídas.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
//            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
//            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
//    })
//    @GetMapping("/list-por-campanhas-concluidas")
//    public List<CampanhaDTO> listPorCampanhasConcluidas(){
//        return null;
//    }

    @ApiOperation("Deleta uma campanha existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha deletada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{id}")
    public CampanhaDTO delete(@PathVariable("id") Integer id){
        return null;
    }

    //TODO verificar se esta correto o metodo
    @ApiOperation("Deleta uma foto de capa existente através do registro da foto.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Livro deletado com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{foto}")
    public CampanhaDTO deleteFotoDeCapa(@PathVariable("foto") String foto){
        return null;
    }

}

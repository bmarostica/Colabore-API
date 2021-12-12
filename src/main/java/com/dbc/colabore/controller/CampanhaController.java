package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.CampanhaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/campanha")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CampanhaController {

    private final CampanhaService campanhaService;


    @ApiOperation("Cria uma nova campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha criada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PostMapping
    public CampanhaDTO create(@RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return campanhaService.create(campanhaCreateDTO);
    }

    @ApiOperation("Mostra uma lista com todas as campanhas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping
    public List<CampanhaDTO> list(){
        return campanhaService.list();
    }

//    @ApiOperation("Retorna uma lista de campanhas concluídas.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
//            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
//            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
//    })
//    @GetMapping("/list-por-campanhas-concluidas")
//    public List<CampanhaDTO> listPorCampanhasConcluidas(){
//        return campanhaService.findByCampanhasConcluidas();
//    }

    @ApiOperation("Altera o status da campanha de ativo para inativo.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Status alterado com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/altera-o-status-de-uma-campanha")
    public void alteraStatusDaCampanha(Integer id) throws RegraDeNegocioException{
        campanhaService.alteraStatusDaCampanha(id);
    }

//    @ApiOperation("Realiza a doação de um valor para a campanha.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Doação realizada com sucesso!"),
//            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
//            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
//    })
//    @PutMapping("/realiza-a-doacao-de-um-valor")
//    public void doacao(Integer id, BigDecimal valorDoado) throws RegraDeNegocioException{
//        campanhaService.doacao(id, valorDoado);
//    }

    @ApiOperation("Mostra uma lista com todas as campanhas concluídas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping("/lista-as-campanhas-concluidas")
    public List<CampanhaDTO> findByCampanhasConcluidas(){
        return campanhaService.findByCampanhasConcluidas();
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

    //TODO verificar método
//    public List<CampanhaDTO> listAsCampanhasQueOUsuarioColaborou(CampanhaCreateDTO campanhaCreateDTO, UsuarioEntity id);

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
            @ApiResponse(code = 200, message = "Foto deletada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{foto}")
    public CampanhaDTO deleteFotoDeCapa(@PathVariable("foto") String foto){
        return null;
    }




}

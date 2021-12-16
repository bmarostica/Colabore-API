package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.CampanhaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
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

        CampanhaDTO campanhaDTO = campanhaService.create(campanhaCreateDTO);
        return campanhaDTO;
    }

    @ApiOperation("Mostra uma lista com todas as campanhas abertas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping
    public List<CampanhaDTO> list(){
        return campanhaService.list();
    }


    @ApiOperation("Mostra uma lista das campanhas abertas criadas pelo usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping("/lista-as-campanhas-criadas-pelo-usuario-logado")
    public List<CampanhaDTO> findByCampanhasCriadasPeloUsuarioQueEstaLogado() throws RegraDeNegocioException {
        return campanhaService.findByCampanhasCriadasPeloUsuarioQueEstaLogado();
    }

    @ApiOperation("Deleta uma campanha existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha deletada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        campanhaService.delete(id);
    }

    @ApiOperation("Filtra as campanhas conforme meta se foi atingida ou não")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @GetMapping("/filtra-por-meta-atingida-ou-não-atingida")
    public List<CampanhaDTO> findByMetaAtingidaOuNaoAtingida(@RequestParam (required = false) String meta) throws RegraDeNegocioException {
        return campanhaService.findByMetaAtingidaOuNaoAtingida(meta);
    }

    @ApiOperation("Atualiza uma campanha existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/{id}")
    public CampanhaDTO update(@PathVariable("id") Integer id, @RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        return campanhaService.update(id, campanhaCreateDTO);
    }



}

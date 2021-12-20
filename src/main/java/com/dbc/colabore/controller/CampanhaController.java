package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.dto.CampanhaDetalheDTO;
import com.dbc.colabore.dto.CampanhaUsuarioComDoacaoDTO;
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
    public CampanhaDTO create(@RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        log.info("Criando campanha...");
        CampanhaDTO campanhaDTO = campanhaService.create(campanhaCreateDTO);
        log.info("Campanha criada com sucesso!");

        return campanhaDTO;
    }

    @ApiOperation("Mostra uma lista com todas as campanhas abertas.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping
    public List<CampanhaDTO> list() {
        log.info("Buscando campanhas...");
        List<CampanhaDTO> campanhaDTO = campanhaService.list();
        log.info("Campanhas localizadas com sucesso!");

        return campanhaDTO;
    }


    @ApiOperation("Mostra uma lista das campanhas abertas criadas pelo usuário.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping("/lista-as-campanhas-criadas-pelo-usuario-logado")
    public List<CampanhaDTO> findByCampanhasCriadasPeloUsuarioQueEstaLogado() {
        log.info("Buscando campanhas...");
        List<CampanhaDTO> campanhaDTO = campanhaService.findByCampanhasCriadasPeloUsuarioQueEstaLogado();
        log.info("Campanhas localizadas com sucesso!");

        return campanhaDTO;
    }

    @ApiOperation("Deleta uma campanha existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha deletada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Deletando campanha...");
        campanhaService.delete(id);
        log.info("Campanha deletada com sucesso!");
    }

    @ApiOperation("Filtra as campanhas conforme meta se foi atingida ou não")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @GetMapping("/filtra-por-meta-atingida-ou-não-atingida")
    public List<CampanhaDTO> findByMetaAtingidaOuNaoAtingida(@RequestParam(required = false) String meta) {
        log.info("Filtrando campanhas...");
        List<CampanhaDTO> campanhaDTO = campanhaService.findByMetaAtingidaOuNaoAtingida(meta);
        log.info("Campanhas filtradas com sucesso!");

        return campanhaDTO;
    }

    @ApiOperation("Atualiza uma campanha existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Campanha atualizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/{id}")
    public CampanhaDTO update(@PathVariable("id") Integer id, @RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        log.info("Atualizando campanha...");
        CampanhaDTO campanhaDTO = campanhaService.update(id, campanhaCreateDTO);
        log.info("Campanha atualizada com sucesso!");

        return campanhaDTO;

    }

    @ApiOperation("Recupera detalhes da campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @GetMapping("/{id}")
    public CampanhaDetalheDTO getPorId(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Buscando campanha...");
        CampanhaDetalheDTO campanhaDetalheDTO = campanhaService.getById(id);
        log.info("Campanhas localizada com sucesso!");

        return campanhaDetalheDTO;
    }


    @ApiOperation("Recupera campanhas que usuario contribuiu.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Busca realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @GetMapping("/minhas-contribuicoes")
    public List<CampanhaUsuarioComDoacaoDTO> getMinhasContribuicoes() {
        log.info("Filtrando campanhas...");
        List<CampanhaUsuarioComDoacaoDTO> campanhaUsuarioComDoacaoDTO = campanhaService.findByContribuicoesPeloUsuarioQueEstaLogado();
        log.info("Campanhas filtradas com sucesso!");

        return campanhaUsuarioComDoacaoDTO;
    }

}

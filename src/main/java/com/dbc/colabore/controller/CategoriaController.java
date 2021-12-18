package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CategoriaCreateDTOComNome;
import com.dbc.colabore.dto.CategoriaDTO;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.CategoriaService;
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
@RequestMapping("/categoria")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CategoriaController {

    private final CategoriaService categoriaService;

    @ApiOperation("Cria uma nova categoria.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categoria criada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PostMapping
    public CategoriaDTO create(@RequestBody @Valid CategoriaCreateDTOComNome categoriaCreateDTOComNome) throws RegraDeNegocioException {
        log.info("Criando categoria...");
        CategoriaDTO categoria = categoriaService.create(categoriaCreateDTOComNome);
        log.info("Categoria criada com sucesso!");

        return categoria;
    }

    @ApiOperation("Mostra uma lista com todas as categorias.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping
    public List<CategoriaDTO> list() {
        log.info("Buscando categorias...");
        List<CategoriaDTO> categoriaDTOS = categoriaService.list();
        log.info("Categorias localizadas com sucesso!");
        return categoriaDTOS;
    }

    @ApiOperation("Deleta uma categoria existente através do id.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Categoria deletada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) throws RegraDeNegocioException {
        log.info("Deletando categoria...");
        categoriaService.delete(id);
        log.info("Categoria deletada com sucesso!");
    }

    @ApiOperation("Mostra uma lista com todas as categorias cadastradas na campanha informada.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lista gerada com sucesso!"),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada")
    })
    @GetMapping("/{idCampanha}")
    public List<CategoriaDTO> listAsCategoriasExistentesNaCampanha(@PathVariable("idCampanha") Integer idCampanha) {
        log.info("Buscando categorias...");
        List<CategoriaDTO> categoriaDTOS = categoriaService.listAsCategoriasExistentesNaCampanha(idCampanha);
        log.info("Categorias localizadas com sucesso!");
        return categoriaDTOS;
    }


}

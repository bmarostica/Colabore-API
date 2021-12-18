package com.dbc.colabore.controller;

import com.dbc.colabore.dto.DoacaoCreateDTO;
import com.dbc.colabore.dto.DoacaoDTO;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.DoacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/doacao")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DoacaoController {

    private final DoacaoService doacaoService;

    @ApiOperation("Realiza a doação de um valor para a campanha.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Doação realizada com sucesso!"),
            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
    })
    @PutMapping("/realiza-a-doacao-de-um-valor")
    public DoacaoDTO doacao(@RequestParam("Id da Campanha") Integer idCampanha,
                            @RequestParam("Valor da doação") BigDecimal doação) throws RegraDeNegocioException {
        log.info("Realizando doação...");
        DoacaoCreateDTO doacaoCreateDTO = new DoacaoCreateDTO();
        doacaoCreateDTO.setIdCampanha(idCampanha);
        doacaoCreateDTO.setValor(doação);
        DoacaoDTO dto = doacaoService.doacao(doacaoCreateDTO);
        log.info("Doação realizada com sucesso!");

        return dto;
    }
}

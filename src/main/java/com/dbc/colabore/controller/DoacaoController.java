package com.dbc.colabore.controller;

import com.dbc.colabore.dto.DoacaoCreateDTO;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.DoacaoService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/doacao")
@RequiredArgsConstructor
@Slf4j
@Validated
public class DoacaoController {

    private final DoacaoService doacaoService;

//    @ApiOperation("Realiza a doação de um valor para a campanha.")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "Doação realizada com sucesso!"),
//            @ApiResponse(code = 400, message = "Erro, informação inconsistente."),
//            @ApiResponse(code = 500, message = "Erro interno, exceção gerada.")
//    })
//    @PutMapping("/realiza-a-doacao-de-um-valor")
//    public void doacao(@RequestBody @Valid DoacaoCreateDTO doacaoCreateDTO) throws RegraDeNegocioException {
//        doacaoService.doacao(doacaoCreateDTO);
//    }
}

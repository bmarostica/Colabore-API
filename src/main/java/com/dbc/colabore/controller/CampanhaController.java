package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/campanha")
@RequiredArgsConstructor
@Slf4j
@Validated
public class CampanhaController {

    public CampanhaDTO create(@RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }




}

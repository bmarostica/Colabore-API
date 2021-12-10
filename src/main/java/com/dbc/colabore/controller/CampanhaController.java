package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
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


    @PostMapping
    public CampanhaDTO create(@RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }

    @PutMapping("/{id}")
    public CampanhaDTO update(@PathVariable("id") Integer id, @RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }

    @GetMapping
    public List<CampanhaDTO> list(){
        return null;
    }

    @DeleteMapping("/{id}")
    public CampanhaDTO delete(@PathVariable("id") Integer id){
        return null;
    }

    @GetMapping("list-por-campanhas-concluidas")
    public List<CampanhaDTO> listPorCampanhasConcluidas(){
        return null;
    }

    @PutMapping("/{foto}")
    public CampanhaDTO updateFotoDeCapa(@PathVariable("foto") String foto, @RequestBody @Valid CampanhaCreateDTO campanhaCreateDTO){
        return null;
    }

    @DeleteMapping("/{foto}")
    public CampanhaDTO deleteFotoDeCapa(@PathVariable("foto") String foto){
        return null;
    }








}

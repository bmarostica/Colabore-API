package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.CampanhaService;
import com.dbc.colabore.service.FotoCampanhaService;
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


@RestController
@RequestMapping("/foto-campanha")
@Validated
@Slf4j
@RequiredArgsConstructor
public class FotoCampanhaController {
    private final FotoCampanhaService fotoCampanhaService;


    @PostMapping("/uploadFotoCampanha")
    public CampanhaDTO uploadFile(@RequestPart("file") MultipartFile file, Integer idCampanha) throws RegraDeNegocioException {
        CampanhaDTO campanhaDTO = fotoCampanhaService.salvarFotoCampanha(file, idCampanha);

        return campanhaDTO;
    }

    @GetMapping("/downloadFotoCampanha/{idCampanha}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int idCampanha) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = fotoCampanhaService.findByIdCampanha(idCampanha);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(campanhaEntity.getFotoCampanha().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + campanhaEntity.getIdUsuario() + "\"")
                .body(new ByteArrayResource(campanhaEntity.getFotoCampanha().getFoto()));
    }



}

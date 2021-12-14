package com.dbc.colabore.controller;

import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.FileStorageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@RestController
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    private FileStorageService fileStorageService;


    @PostMapping("/uploadFotoPerfil")
    public UsuarioDTO uploadFile(@RequestParam("file") MultipartFile file, Integer idUsuario) throws RegraDeNegocioException {
        UsuarioDTO usuarioDTO = fileStorageService.storeFileUsuario(file, idUsuario);

        return usuarioDTO;
    }

    @GetMapping("/downloadFotoPerfil/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResourceUsuario(fileName);


        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Não foi possível determinar o tipo de arquivo.");
        }


        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    @PostMapping("/uploadFotoCampanha")
    public CampanhaDTO uploaCampanha(@RequestParam("file") MultipartFile file, Integer idCampanha) throws RegraDeNegocioException {
        CampanhaDTO campanhaDTO = fileStorageService.storeFileCampanha(file, idCampanha);

        return campanhaDTO;
    }


    @GetMapping("/downloadFotoCapanha/{fileName:.+}")
    public ResponseEntity<Resource> downloadFotoCampanha(@PathVariable String fileName, HttpServletRequest request) {

        Resource resource = fileStorageService.loadFileAsResourceCampanha(fileName);


        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("\n" + "Não foi possível determinar o tipo de arquivo.");
        }


        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}

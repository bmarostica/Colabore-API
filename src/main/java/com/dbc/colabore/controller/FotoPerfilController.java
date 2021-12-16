package com.dbc.colabore.controller;

import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.service.FotoPerfilService;
import com.dbc.colabore.service.UsuarioService;
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
@RequestMapping("/foto-perfil")
@Validated
@Slf4j
@RequiredArgsConstructor
public class FotoPerfilController {
    private final FotoPerfilService fotoPerfilService;

    @PostMapping("/uploadFotoPerfil")
    public UsuarioDTO uploadFile(@RequestPart("file") MultipartFile file, Integer idUsuario) throws RegraDeNegocioException {
        UsuarioDTO usuarioDTO = fotoPerfilService.salvarFotoPerfil(file, idUsuario);

        return usuarioDTO;
    }

    @GetMapping("/downloadFotoPerfil/{idUsuario}")
    public ResponseEntity<Resource> downloadFile(@PathVariable int idUsuario) throws RegraDeNegocioException {
        UsuarioEntity usuarioEntity = fotoPerfilService.findByIdUsuario(idUsuario);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(usuarioEntity.getFotoPerfil().getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + usuarioEntity.getIdUsuario() + "\"")
                .body(new ByteArrayResource(usuarioEntity.getFotoPerfil().getFotoPerfil()));
    }
}

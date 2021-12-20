package com.dbc.colabore.service;


import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.FotoPerfilEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.FileStorageException;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.FotoPerfilRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FotoPerfilService {
    private final UsuarioService usuarioService;
    private final FotoPerfilRepository fotoPerfilRepository;

    public UsuarioDTO salvarFotoPerfil(MultipartFile file, int idUsuario) throws RegraDeNegocioException {
        UsuarioEntity usuarioEntity= usuarioService.findById(idUsuario);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {


            if(fileName.contains("..")) {
                throw new FileStorageException("Desculpe! O nome do arquivo contém uma sequência de caminho inválida " + fileName);
            }
            FotoPerfilEntity fotoPerfil = usuarioEntity.getFotoPerfil();
            if(fotoPerfil == null){
                fotoPerfil = new FotoPerfilEntity();
            }
            fotoPerfil.setFileType(file.getContentType());
            fotoPerfil.setFotoPerfil(file.getBytes());

            fotoPerfil = fotoPerfilRepository.save(fotoPerfil);

            usuarioEntity.setFotoPerfil(fotoPerfil);

            UsuarioDTO usuarioDTO = usuarioService.saveEntity(usuarioEntity);

            return usuarioDTO;
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }


    public UsuarioEntity findByIdUsuario(int idUsuario) throws RegraDeNegocioException {
        UsuarioEntity usuarioEntity= usuarioService.findById(idUsuario);
        if(usuarioEntity.getFotoPerfil() == null){
            new RegraDeNegocioException("Foto de perfil não Localizada");
        }
        return usuarioEntity;
    }
}

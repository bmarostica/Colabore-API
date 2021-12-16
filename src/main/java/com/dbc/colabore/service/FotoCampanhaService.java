package com.dbc.colabore.service;

import com.dbc.colabore.controller.UsuarioController;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.FotoCampanhaEntity;
import com.dbc.colabore.entity.FotoPerfilEntity;
import com.dbc.colabore.exception.FileStorageException;
import com.dbc.colabore.exception.RegraDeNegocioException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class FotoCampanhaService {
    private final CampanhaService campanhaService;

    public CampanhaDTO salvarFotoCampanha(MultipartFile file, int idCampanha) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity= campanhaService.findById(idCampanha);

        String fileName = org.springframework.util.StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            FotoCampanhaEntity fotoCampanha = campanhaEntity.getFotoCampanha();
            if(fotoCampanha == null){
                fotoCampanha = new FotoCampanhaEntity();
            }
            fotoCampanha.setFileType(file.getContentType());
            fotoCampanha.setFoto(file.getBytes());

            campanhaEntity.setFotoCampanha(fotoCampanha);

            CampanhaDTO campanhaDTO = campanhaService.saveEntity(campanhaEntity);

            return campanhaDTO;
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }

    public CampanhaEntity findByIdCampanha(int idCampanha) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = campanhaService.findById(idCampanha);

        if(campanhaEntity.getFotoCampanha() == null){
            new RegraDeNegocioException("Foto Campanha não Localizada");
        }
        return campanhaEntity;
    }
}

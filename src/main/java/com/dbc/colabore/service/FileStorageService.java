package com.dbc.colabore.service;

import com.dbc.colabore.config.FileStorageProperties;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.FileStorageException;
import com.dbc.colabore.exception.MyFileNotFoundException;
import com.dbc.colabore.exception.RegraDeNegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {
    private final Path fileStorageLocationCampanha;
    private final Path fileStorageLocationUsuario;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CampanhaService campanhaService;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocationCampanha = Paths.get(fileStorageProperties.getUploadDirCampanha())
                .toAbsolutePath().normalize();
        this.fileStorageLocationUsuario = Paths.get(fileStorageProperties.getUploadDirUsuario())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocationCampanha);
            Files.createDirectories(this.fileStorageLocationUsuario);
        } catch (Exception ex) {
            throw new FileStorageException("Não foi possível criar o diretório onde os arquivos carregados serão armazenados.", ex);
        }
    }


    public UsuarioDTO storeFileUsuario(MultipartFile file, int idUsuario) throws RegraDeNegocioException {
        UsuarioEntity usuarioEntity= usuarioService.findById(idUsuario);



        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Desculpe! O nome do arquivo contém uma sequência de caminho inválida " + fileName);
            }


            Path targetLocation = this.fileStorageLocationUsuario.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFotoPerfil/")
                    .path(fileName)
                    .toUriString();

            usuarioEntity.setFotoPerfil(fileDownloadUri);

            UsuarioDTO usuarioDTO = usuarioService.saveEntity(usuarioEntity);

            return usuarioDTO;
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }

    public Resource loadFileAsResourceUsuario(String fileName) {
        try {
            Path filePath = this.fileStorageLocationUsuario.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Arquivo não encontrado " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("Arquivo não encontrado " + fileName, ex);
        }
    }

    public CampanhaDTO storeFileCampanha(MultipartFile file, int idCampanha ) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity= campanhaService.findById(idCampanha);



        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            if(fileName.contains("..")) {
                throw new FileStorageException("Desculpe! O nome do arquivo contém uma sequência de caminho inválida " + fileName);
            }


            Path targetLocation = this.fileStorageLocationCampanha.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/downloadFotoCapanha/")
                    .path(fileName)
                    .toUriString();

            campanhaEntity.setFoto(fileDownloadUri);

            CampanhaDTO campanhaDTO = campanhaService.saveEntity(campanhaEntity);

            return campanhaDTO;
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }
    public Resource loadFileAsResourceCampanha(String fileName) {
        try {
            Path filePath = this.fileStorageLocationCampanha.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("Arquivo não encontrado " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("Arquivo não encontrado " + fileName, ex);
        }
    }
}

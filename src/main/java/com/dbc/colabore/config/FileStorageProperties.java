package com.dbc.colabore.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {

//    @Value("${file.upload-dir-cammpanha}")
//    private String uploadDirCampanha;
//
//    @Value("${file.upload-dir-usuario}")
//    private String uploadDirUsuario;
//
//    public String getUploadDirCampanha() {
//        return uploadDirCampanha;
//    }
//
//    public void setUploadDirCampanha(String uploadDirCampanha) {
//        this.uploadDirCampanha = uploadDirCampanha;
//    }
//
//
//    public String getUploadDirUsuario() {
//        return uploadDirUsuario;
//    }
//
//    public void setUploadDirUsuario(String uploadDirUsuario) {
//        this.uploadDirUsuario = uploadDirUsuario;
//    }
}

package com.dbc.colabore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "FOTO_PERFIL")
public class FotoPerfilEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_foto_perfil")
    private Integer idFotoPerfil;

    @Column(name = "foto_perfil")
    private byte[] fotoPerfil;

    @Column(name = "file_type")
    private String fileType;
}

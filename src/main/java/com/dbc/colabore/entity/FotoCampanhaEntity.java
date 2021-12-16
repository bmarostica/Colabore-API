package com.dbc.colabore.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "FOTO_CAMPANHA")
public class FotoCampanhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FOTO_CAMPANHA")
    private Integer idFotoCampanaha;

    @Column(name = "FOTO")
    private byte[] foto;

    @Column(name = "file_type")
    private String fileType;
}

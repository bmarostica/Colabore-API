package com.dbc.colabore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "CATEGORIA")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private String idCategoria;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_campanha", referencedColumnName = "id_campanha")
    private CampanhaEntity campanhaEntity;
}

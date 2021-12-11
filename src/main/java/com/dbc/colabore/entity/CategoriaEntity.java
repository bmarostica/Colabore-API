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
    private Integer idCategoria;

    @Column(name = "NOME")
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(
            name = "CAMPANHA_CATEGORIA",
            joinColumns = @JoinColumn(name = "id_campanha"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private CampanhaEntity campanhaEntity;
}

package com.dbc.colabore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "DOACAO")
public class DoacaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_DOACAO")
    private Integer idDoacao;

    @Column(name = "VALOR_DOACAO")
    private String valor;

    @ManyToOne
    @JoinColumn(name="id_usuario", nullable=false)
    private UsuarioEntity usuarioEntity;

    @ManyToOne
    @JoinColumn(name="id_campanha", nullable=false)
    private CampanhaEntity campanhaEntity;
}

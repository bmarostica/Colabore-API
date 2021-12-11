package com.dbc.colabore.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity(name = "CAMPANHA")
public class CampanhaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CAMPANHA")
    private Integer idCampanha;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", referencedColumnName = "id_usuario")
    private UsuarioEntity idUsuario;

    @Column(name = "FOTO")
    private String foto;

    @Column(name = "TITULO_CAMPANHA")
    private String tituloCampanha;

    @Column(name = "META_ARRECADACAO")
    private BigDecimal metaArrecadacao;

    @Column(name = "TOTAL_ARRECADADO")
    private BigDecimal totalArrecadado;

    @OneToMany(mappedBy = "campanhaEntity", fetch = FetchType.LAZY)
    private Set<CategoriaEntity> tagsCategoria;

    @Column(name = "ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao;

    @Column(name = "STATUS_CAMPANHA")
    private Boolean statusCampanha;

    @Column(name = "DESCRICAO_CAMPANHA")
    private String descricaoCampanha;

    @ManyToMany
    @JoinTable(
            name = "USUARIO_CAMPANHA",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_campanha")
    )
    private Set<UsuarioEntity> usuariosContribuidores;

}

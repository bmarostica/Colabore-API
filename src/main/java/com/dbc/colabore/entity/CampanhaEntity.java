package com.dbc.colabore.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    @Column(name = "CONCLUI_AUTOMATICAMENTE_CAMPANHA")
    private Boolean concluiCampanhaAutomaticamente;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "CAMPANHA_CATEGORIA",
            joinColumns = @JoinColumn(name = "id_campanha"),
            inverseJoinColumns = @JoinColumn(name = "id_categoria")
    )
    private Set<CategoriaEntity> tagsCategoria;

    @Column(name = "ULTIMA_ALTERACAO")
    private LocalDateTime ultimaAlteracao;

    @Column(name = "STATUS_CAMPANHA")
    private Boolean statusCampanha;

    @Column(name = "DESCRICAO_CAMPANHA")
    private String descricaoCampanha;

    @Column(name = "DATA_LIMITE_ARRECADACAO")
    private LocalDate dataLimiteContribuicao;

    @OneToMany(mappedBy="campanhaEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoacaoEntity> doacoes;

}

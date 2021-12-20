//package com.dbc.colabore.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//import org.springframework.security.core.GrantedAuthority;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.List;
//
//@Getter
//@Setter
//@Entity(name = "REGRA")
//public class RegraEntity implements Serializable, GrantedAuthority {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "ID_REGRA")
//    private Integer idRegra;
//
//    @Column(name = "NOME")
//    private String nome;
//
//    @ManyToMany(mappedBy = "regras")
//    private List<PerfilEntity> perfils;
//
//}

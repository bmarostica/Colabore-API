package com.dbc.colabore.repository;

import com.dbc.colabore.entity.CampanhaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<CampanhaEntity, Integer> {

    @Query(value = "SELECT * " +
            "FROM CAMPANHA " +
            "WHERE STATUS_CAMPANHA = FALSE"
            , nativeQuery = true)
    List<CampanhaEntity> findByCampanhasConcluidas();

    @Query(value = "SELECT * " +
            "FROM CAMPANHA " +
            "WHERE ID_USUARIO = :idUsuario and STATUS_CAMPANHA = TRUE"
            , nativeQuery = true)
    List<CampanhaEntity> findByCampanhasCriadasPeloUsuarioLogado(Integer idUsuario);

    @Query(value = "SELECT * " +
            "FROM CAMPANHA " +
            "WHERE ID_USUARIO != :idUsuario and STATUS_CAMPANHA = TRUE"
            , nativeQuery = true)
    List<CampanhaEntity> findyByIdDeUsuarioDiferenteDoLogado(Integer idUsuario);

    @Query(value = "SELECT * " +
            "FROM CAMPANHA " +
            "WHERE TOTAL_ARRECADADO >= META_ARRECADACAO"
            , nativeQuery = true
    )
    List<CampanhaEntity> findByMetaAtingida();

    @Query(value = "SELECT * " +
            "FROM CAMPANHA " +
            "WHERE TOTAL_ARRECADADO < META_ARRECADACAO"
            , nativeQuery = true
    )
    List<CampanhaEntity> findByMetaNaoAtingida();


}
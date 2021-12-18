package com.dbc.colabore.repository;

import com.dbc.colabore.dto.CategoriaDTO;
import com.dbc.colabore.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

    @Query(value = "SELECT COUNT(*) " +
            "FROM CATEGORIA " +
            "WHERE NOME ILIKE :categoria "
            , nativeQuery = true
    )
    Integer findByNomeCategoriaIlike(String categoria);

    @Query(value = "SELECT C.* " +
            "FROM CATEGORIA C " +
            "JOIN CAMPANHA_CATEGORIA CC ON C.ID_CATEGORIA = CC.ID_CATEGORIA " +
            "WHERE CC.ID_CAMPANHA = :idCampanha"
            , nativeQuery = true
    )
    List<CategoriaEntity> listAsCategoriasExistentesNaCampanha(Integer idCampanha);
}

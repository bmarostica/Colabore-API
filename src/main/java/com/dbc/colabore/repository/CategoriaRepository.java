package com.dbc.colabore.repository;

import com.dbc.colabore.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Integer> {

    @Query(value = "SELECT COUNT(*) " +
            "FROM CATEGORIA " +
            "WHERE NOME ILIKE :categoria "
            , nativeQuery = true
    )
    Integer findByNomeCategoriaIlike(String categoria);
}

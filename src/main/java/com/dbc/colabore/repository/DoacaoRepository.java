package com.dbc.colabore.repository;

import com.dbc.colabore.entity.DoacaoEntity;
import com.dbc.colabore.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoEntity, Integer> {
}

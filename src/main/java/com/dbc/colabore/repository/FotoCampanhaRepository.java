package com.dbc.colabore.repository;

import com.dbc.colabore.entity.FotoCampanhaEntity;
import com.dbc.colabore.entity.FotoPerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoCampanhaRepository extends JpaRepository<FotoCampanhaEntity, Integer> {
}

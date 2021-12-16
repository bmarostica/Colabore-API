package com.dbc.colabore.repository;

import com.dbc.colabore.entity.FotoPerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoPerfilRepository  extends JpaRepository<FotoPerfilEntity, Integer> {
}

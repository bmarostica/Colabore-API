package com.dbc.colabore.repository;

import com.dbc.colabore.entity.PerfilEntity;
import com.dbc.colabore.entity.RegraEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Integer> {
}

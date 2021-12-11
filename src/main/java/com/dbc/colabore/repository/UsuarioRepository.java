package com.dbc.colabore.repository;

import com.dbc.colabore.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Integer> {
   // List<UsuarioEntity> findByLogin(String login);

}

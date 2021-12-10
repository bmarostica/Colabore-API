package com.dbc.colabore.repository;

import com.dbc.colabore.entity.PerfilEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilRepository extends JpaRepository<PerfilEntity, Integer> {
    Optional<PerfilEntity> findByLoginAndSenha(String login, String senha);
    Optional<PerfilEntity> findByLogin(String login);
}

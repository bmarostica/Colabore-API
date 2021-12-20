package com.dbc.colabore.repository;

import com.dbc.colabore.dto.UsuarioDoacaoDTO;
import com.dbc.colabore.entity.DoacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DoacaoRepository extends JpaRepository<DoacaoEntity, Integer> {

    @Query(value ="select  new com.dbc.colabore.dto.UsuarioDoacaoDTO( usuario.idUsuario , usuario.nome ,usuario.email, SUM(d.valor) ) " +
            "from DOACAO d" +
            " join d.usuarioEntity usuario  " +
            " join d.campanhaEntity campanha  " +
            "where campanha.idCampanha = :idCampanha " +
            "group by usuario.idUsuario , usuario.nome ,usuario.email")
    Set<UsuarioDoacaoDTO> findAcumuladoDoacaoUsuarioCampanha(Integer idCampanha);

    @Query(value ="select  new com.dbc.colabore.dto.UsuarioDoacaoDTO( usuario.idUsuario , usuario.nome ,usuario.email, SUM(d.valor) ) " +
            "from DOACAO d " +
            "join d.usuarioEntity usuario " +
            "join d.campanhaEntity campanha " +
            "where d.usuarioEntity.idUsuario = :idUsuario and campanha.idCampanha = :idCampanha " +
            "group by usuario.idUsuario , usuario.nome ,usuario.email")
    UsuarioDoacaoDTO getValorTotalDoadoPeloUsuarioNaCampanha(Integer idUsuario, Integer idCampanha);
}

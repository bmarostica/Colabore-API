package com.dbc.colabore.service;

import com.dbc.colabore.dto.PerfilCreateDTO;
import com.dbc.colabore.dto.PerfilDTO;
import com.dbc.colabore.entity.PerfilEntity;
import com.dbc.colabore.entity.RegraEntity;
import com.dbc.colabore.repository.PerfilRepository;
import com.dbc.colabore.repository.RegraRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PerfilService {
    private final PerfilRepository perfilRepository;
    private final RegraRepository regraRepository;

    public Optional<PerfilEntity> findByLogin(String login){
        return perfilRepository.findByLogin(login);
    }

    public PerfilDTO create(PerfilCreateDTO usuarioCreateDTO) {
        PerfilEntity entity = new PerfilEntity();
        entity.setLogin(usuarioCreateDTO.getLogin());
        entity.setSenha(new BCryptPasswordEncoder().encode(usuarioCreateDTO.getSenha()));
        entity.setRegras(
                usuarioCreateDTO.getRegras().stream()
                        .map(regraId -> regraRepository.findById(regraId)
                                .orElse(null))
                        .collect(Collectors.toList())
        );
        PerfilEntity save = perfilRepository.save(entity);
        return new PerfilDTO(save.getIdPerfil(), save.getUsername(), save.getRegras().stream().map(RegraEntity::getIdRegra).collect(Collectors.toList()));
    }




}

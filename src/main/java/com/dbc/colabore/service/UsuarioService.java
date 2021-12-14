package com.dbc.colabore.service;

import com.dbc.colabore.dto.PerfilDTO;
import com.dbc.colabore.dto.UsuarioCreateDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.PerfilEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.repository.PerfilRepository;
import com.dbc.colabore.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;

    public UsuarioDTO create(UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioEntity entity = objectMapper.convertValue(usuarioCreateDTO, UsuarioEntity.class);
        entity.setNome(usuarioCreateDTO.getNome());
        entity.setEmail(usuarioCreateDTO.getEmail());
        entity.setFotoPerfil(usuarioCreateDTO.getFotoPerfil());
        entity.setSenha(new BCryptPasswordEncoder().encode(usuarioCreateDTO.getSenha()));
        entity.setPerfil(
                usuarioCreateDTO.getPerfis().stream()
                        .map(perfilId -> perfilRepository.findById(perfilId)
                                .orElse(null))
                        .collect(Collectors.toList())
        );

        UsuarioEntity save = usuarioRepository.save(entity);
        UsuarioDTO usuarioDTO = objectMapper.convertValue(save, UsuarioDTO.class);
        usuarioDTO.setPerfis(save.getPerfil().stream().map(perfilEntity -> objectMapper.convertValue(perfilEntity, PerfilDTO.class)).collect(Collectors.toList()));
        return usuarioDTO;

    }

    public UsuarioDTO update(UsuarioCreateDTO usuarioCreateDTO) {
        int idUsuario = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        List<PerfilEntity> perfilEntityList = perfilRepository.findAllById(usuarioCreateDTO.getPerfis());
        UsuarioEntity usuarioEntity = objectMapper.convertValue(usuarioCreateDTO, UsuarioEntity.class);
        usuarioEntity.setIdUsuario(idUsuario);
        usuarioEntity.setSenha(new BCryptPasswordEncoder().encode(usuarioCreateDTO.getSenha()));
        usuarioEntity.setPerfil(perfilEntityList);
        UsuarioEntity usuarioAtt = usuarioRepository.save(usuarioEntity);
        UsuarioDTO dto = objectMapper.convertValue(usuarioAtt, UsuarioDTO.class);
        dto.setPerfis(usuarioAtt.getPerfil().stream().map(perfilEntity -> objectMapper.convertValue(perfilEntity, PerfilDTO.class)).collect(Collectors.toList()));

        return dto;
    }

    public Optional<UsuarioEntity> findByLogin(String login){return usuarioRepository.findByEmail(login);}

    public UsuarioDTO getUsuarioLogado(){
        int idUsuario = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        Optional<UsuarioEntity> usuarioEntity = usuarioRepository.findById(idUsuario);
        UsuarioDTO usuarioDTO =  objectMapper.convertValue(usuarioEntity, UsuarioDTO.class);
        // usuarioDTO.setPerfis(usuarioEntity.get().getPerfil().stream().map(perfilEntity -> objectMapper.convertValue(perfilEntity, PerfilDTO.class)).collect(Collectors.toList()));

        return usuarioDTO;
    }
}

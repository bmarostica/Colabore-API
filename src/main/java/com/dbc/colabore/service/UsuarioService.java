package com.dbc.colabore.service;

import com.dbc.colabore.dto.UsuarioCreateDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private final ObjectMapper objectMapper;
    private final UsuarioRepository usuarioRepository;



    public UsuarioDTO create(UsuarioCreateDTO usuarioCreateDTO) {
        UsuarioEntity entity = objectMapper.convertValue(usuarioCreateDTO, UsuarioEntity.class);
        entity.setNome(usuarioCreateDTO.getNome());
        entity.setEmail(usuarioCreateDTO.getEmail());
        entity.setFotoPerfil(usuarioCreateDTO.getFotoPerfil());
        entity.setSenha(new BCryptPasswordEncoder().encode(usuarioCreateDTO.getSenha()));
        UsuarioEntity save = usuarioRepository.save(entity);
        UsuarioDTO usuarioDTO = objectMapper.convertValue(save, UsuarioDTO.class);

        return usuarioDTO;

    }
    public Optional<UsuarioEntity>findByLogin(String login){return usuarioRepository.findByLogin(login);}
}

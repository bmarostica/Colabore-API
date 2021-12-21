package com.dbc.colabore.service;

import com.dbc.colabore.dto.*;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.DoacaoEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.DoacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final CampanhaRepository campanhaRepository;
    private final DoacaoRepository doacaoRepository;


    public DoacaoDTO doacao(DoacaoCreateDTO doacaoCreateDTO) throws RegraDeNegocioException {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();
        UsuarioEntity usuarioEntity = usuarioService.findById(recuperaUsuario.getIdUsuario());

        CampanhaEntity localizarCampanha = campanhaRepository.getById(doacaoCreateDTO.getIdCampanha());

        if(Objects.equals(recuperaUsuario.getIdUsuario(), localizarCampanha.getIdUsuario().getIdUsuario())){
            throw new RegraDeNegocioException("Você não pode doar para a campanha criada por si mesmo!!");
        }

        BigDecimal totalArrecadado = localizarCampanha.getTotalArrecadado();
        localizarCampanha.setTotalArrecadado(totalArrecadado.add(doacaoCreateDTO.getValor()));
        localizarCampanha.setUltimaAlteracao(LocalDateTime.now().minusHours(3));
        campanhaRepository.save(localizarCampanha);

        DoacaoEntity doacaoEntity = objectMapper.convertValue(doacaoCreateDTO, DoacaoEntity.class);
        doacaoEntity.setCampanhaEntity(localizarCampanha);
        doacaoEntity.setUsuarioEntity(usuarioEntity);

        DoacaoEntity doacaoRealizada = doacaoRepository.save(doacaoEntity);
        DoacaoDTO doacaoDTO = objectMapper.convertValue(doacaoRealizada, DoacaoDTO.class);

        CampanhaDTO campanhaDTO = objectMapper.convertValue(localizarCampanha, CampanhaDTO.class);
        campanhaDTO.setCriadorCampanha(usuarioService.getById(localizarCampanha.getIdUsuario().getIdUsuario()));
        campanhaDTO.setCategorias(localizarCampanha.getTagsCategoria().stream()
                .map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                .collect(Collectors.toSet()));


        doacaoDTO.setUsuario(objectMapper.convertValue(usuarioEntity, UsuarioDTO.class));
        doacaoDTO.setCampanhaDTO(campanhaDTO);

        return doacaoDTO;
    }

    public Set<UsuarioDoacaoDTO> getUsuarioDoacaoAcumuladoPorIdCampanha(Integer idCampanha){
        return  doacaoRepository.findAcumuladoDoacaoUsuarioCampanha(idCampanha);
    }

    public UsuarioDoacaoDTO getValorTotalDoadoPeloUsuarioNaCampanha(Integer idUsuario, Integer idCampanha){
        return  doacaoRepository.getValorTotalDoadoPeloUsuarioNaCampanha(idUsuario, idCampanha);
    }
}

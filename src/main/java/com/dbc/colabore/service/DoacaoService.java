package com.dbc.colabore.service;

import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.dto.DoacaoCreateDTO;
import com.dbc.colabore.dto.UsuarioDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.DoacaoEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.DoacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    private final ObjectMapper objectMapper;
    private final UsuarioService usuarioService;
    private final CampanhaRepository campanhaRepository;

    private final DoacaoRepository doacaoRepository;


    public BigDecimal doacao(DoacaoCreateDTO doacaoCreateDTO) {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();
        UsuarioEntity usuarioEntity = objectMapper.convertValue(recuperaUsuario, UsuarioEntity.class);

        CampanhaEntity localizarCampanha = campanhaRepository.getById(doacaoCreateDTO.getIdCampanha());
        CampanhaEntity campanhaEntity = objectMapper.convertValue(localizarCampanha, CampanhaEntity.class);

        BigDecimal totalArrecadado = campanhaEntity.getTotalArrecadado();
        campanhaEntity.setTotalArrecadado(totalArrecadado.add(doacaoCreateDTO.getValor()));
        campanhaEntity.setUltimaAlteracao(LocalDateTime.now().minusHours(3));

        CampanhaEntity salvarCampanha = campanhaRepository.save(campanhaEntity);
        CampanhaDTO campanhaDTO = objectMapper.convertValue(salvarCampanha, CampanhaDTO.class);



        return null;
    }
}

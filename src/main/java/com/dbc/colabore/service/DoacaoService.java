package com.dbc.colabore.service;

import com.dbc.colabore.dto.DoacaoCreateDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class DoacaoService {

    // Criar Service de doação
    public BigDecimal doacao(DoacaoCreateDTO doacaoCreateDTO) {
//        CampanhaEntity localizarCampanha = campanhaRepository.getById(doacaoCreateDTO.getIdCampanha());
//        CampanhaEntity campanhaEntity = objectMapper.convertValue(localizarCampanha, CampanhaEntity.class);
//        BigDecimal totalArrecadado = campanhaEntity.getTotalArrecadado();
//        campanhaEntity.setTotalArrecadado(totalArrecadado.add(valorDoado));
//        campanhaEntity.setUltimaAlteracao(LocalDateTime.now());
//        CampanhaEntity salvarCampanha = campanhaRepository.save(campanhaEntity);
//        CampanhaDTO campanhaDTO = objectMapper.convertValue(salvarCampanha, CampanhaDTO.class);
//        return campanhaDTO.getTotalArrecadado();
        return null;
    }
}

package com.dbc.colabore.service;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.dto.CategoriaDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    public CampanhaDTO create(CampanhaCreateDTO campanhaCreateDTO) {
        CampanhaEntity campanhaEntity = objectMapper.convertValue(campanhaCreateDTO, CampanhaEntity.class);
        campanhaEntity.setUltimaAlteracao(LocalDateTime.now());
        campanhaEntity.setStatusCampanha(true);
        CampanhaEntity campanhaCriada = campanhaRepository.save(campanhaEntity);
        CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaCriada, CampanhaDTO.class);

        return campanhaDTO;
    }

    public void alteraStatusDaCampanha(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);
        if(campanhaEntity.getTotalArrecadado().compareTo(campanhaEntity.getMetaArrecadacao()) >= 0 ){
            campanhaEntity.setStatusCampanha(false);
        }
        else
            throw new RegraDeNegocioException("A meta ainda não foi atingida");
        campanhaRepository.save(campanhaEntity);
    }

    public void doacao(Integer id, BigDecimal valorDoado) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);

        campanhaEntity.getTotalArrecadado().add(valorDoado);
        campanhaRepository.save(campanhaEntity);
    }

    public List<CampanhaDTO> list(){
        return campanhaRepository.findAll().stream()
                .map(categoria -> objectMapper.convertValue(categoria, CampanhaDTO.class))
                .collect(Collectors.toList());
    }

    public List<CampanhaDTO> findByCampanhasConcluidas(){
        return campanhaRepository.findByCampanhasConcluidas().stream()
                .map(campanhaEntity -> objectMapper.convertValue(campanhaEntity, CampanhaDTO.class))
                .collect(Collectors.toList());
    }
















    public CampanhaEntity findById(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = campanhaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Livro não localizado"));
        return campanhaEntity;
    }

//    public CampanhaDTO getById(Integer id) throws RegraDeNegocioException {
//        CampanhaEntity campanhaEntity = findById(id);
//        CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaEntity, CampanhaDTO.class);
//        return campanhaDTO;
//    }

}

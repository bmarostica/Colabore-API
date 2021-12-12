package com.dbc.colabore.service;

import com.dbc.colabore.dto.CampanhaCreateDTO;
import com.dbc.colabore.dto.CampanhaDTO;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
        if(campanhaEntity.getTotalArrecadado() == campanhaEntity.getMetaArrecadacao()){
            campanhaEntity.setStatusCampanha(false);
        }
        else
            throw new RegraDeNegocioException("A meta ainda não foi atingida");
        campanhaRepository.save(campanhaEntity);
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

    public CampanhaDTO update(int idCampanha, CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity =  findById(idCampanha);
        // Regra de negocio para validar se o usuario foi o criador da campanha
        verificaSeCriador(campanhaEntity);


        return null;
    }

    private void verificaSeCriador(CampanhaEntity campanhaEntity) throws RegraDeNegocioException {
        // Carrega o Id do usuario logado
        int idUsuario = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        if(campanhaEntity.getIdUsuario().getIdUsuario() != idUsuario){
            throw new RegraDeNegocioException("Você não é o criador da campanha!");
        }
    }

}

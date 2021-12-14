package com.dbc.colabore.service;

import com.dbc.colabore.dto.*;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.CategoriaEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.CategoriaRepository;
import com.dbc.colabore.repository.DoacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository campanhaRepository;
    private final DoacaoRepository doacaoRepository;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;
    private final ObjectMapper objectMapper;

    //funcionando
    public CampanhaDTO create(CampanhaCreateDTO campanhaCreateDTO) {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();
        UsuarioEntity usuarioEntity = objectMapper.convertValue(recuperaUsuario, UsuarioEntity.class);

        CampanhaEntity campanhaEntity = objectMapper.convertValue(campanhaCreateDTO, CampanhaEntity.class);

        Set<CategoriaEntity> categorias = campanhaCreateDTO.getCategorias().stream()
                .map(categoriaCreateDTO -> {
                    try {
                        return categoriaService.findById(categoriaCreateDTO.getIdCategoria());
                    } catch (RegraDeNegocioException e) {
                        e.printStackTrace();
                    }
                    return null;
                })
                .collect(Collectors.toSet());

        campanhaEntity.setIdUsuario(usuarioEntity);
        campanhaEntity.setUltimaAlteracao(LocalDateTime.now().minusHours(3));
        campanhaEntity.setStatusCampanha(true);
        campanhaEntity.setTagsCategoria(categorias);
        campanhaEntity.setTotalArrecadado(BigDecimal.ZERO);
        CampanhaEntity campanhaCriada = campanhaRepository.save(campanhaEntity);
        CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaCriada, CampanhaDTO.class);
        campanhaDTO.setCategorias(campanhaEntity.getTagsCategoria().stream()
                .map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                .collect(Collectors.toSet()));
        campanhaDTO.setCriadorCampanha(objectMapper.convertValue(usuarioEntity, UsuarioDTO.class));

        return campanhaDTO;
    }

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

    //funcionando
    public void alteraStatusDaCampanhaQuandoMetaAtingida(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);
        if (campanhaEntity.getTotalArrecadado().compareTo(campanhaEntity.getMetaArrecadacao()) >= 0) {
            campanhaEntity.setStatusCampanha(false);
        } else
            throw new RegraDeNegocioException("A meta ainda não foi atingida");
        campanhaRepository.save(campanhaEntity);
    }

    //funcionando
    public List<CampanhaDTO> findByCampanhasConcluidas() {
        return campanhaRepository.findByCampanhasConcluidas().stream()
                .map(this::mapeamentoEConversao)
                .collect(Collectors.toList());
    }


    //funcionando
    public List<CampanhaDTO> findByCampanhasCriadasPeloUsuarioLogado(Integer idUsuario) throws RegraDeNegocioException {
        int usuarioLogado = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());
        if (usuarioLogado != idUsuario) {
            throw new RegraDeNegocioException("Você não é o criador da campanha!");
        }

        return campanhaRepository.findByCampanhasCriadasPeloUsuarioLogado(idUsuario).stream()
                .map(this::mapeamentoEConversao)
                .collect(Collectors.toList());
    }


    //funcionando
    public List<CampanhaDTO> list() {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();

        return campanhaRepository.findyByIdDeUsuarioDiferenteDoLogado(recuperaUsuario.getIdUsuario()).stream()
                .map(this::mapeamentoEConversao)
                .collect(Collectors.toList());
    }







    public CampanhaDTO update(int idCampanha, CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(idCampanha);
        // Regra de negocio para validar se o usuario foi o criador da campanha
        verificaSeCriador(campanhaEntity);


        return null;
    }



    //funcionando
    public CampanhaDTO mapeamentoEConversao(CampanhaEntity campanhaEntity) {
        CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaEntity, CampanhaDTO.class);
        try {
            campanhaDTO.setCategorias(campanhaEntity.getTagsCategoria().stream()
                    .map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                    .collect(Collectors.toSet()));
            campanhaDTO.setCriadorCampanha(usuarioService.getById(campanhaEntity.getIdUsuario().getIdUsuario()));
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        }
        return campanhaDTO;
    }

    //funcionando
    private void verificaSeCriador(CampanhaEntity campanhaEntity) throws RegraDeNegocioException {
        // Carrega o Id do usuario logado
        int idUsuario = Integer.parseInt(SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString());

        if (campanhaEntity.getIdUsuario().getIdUsuario() != idUsuario) {
            throw new RegraDeNegocioException("Você não é o criador da campanha!");
        }
    }

    public CampanhaEntity findById(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = campanhaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Campanha não localizado"));
        return campanhaEntity;
    }

    public CampanhaDTO getById(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);
        CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaEntity, CampanhaDTO.class);
        return campanhaDTO;
    }

}

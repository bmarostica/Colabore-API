package com.dbc.colabore.service;

import com.dbc.colabore.dto.*;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.CategoriaEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.DoacaoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampanhaService {


    private final CampanhaRepository campanhaRepository;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;
    private final DoacaoService doacaoService;
    private final ObjectMapper objectMapper;

    public CampanhaDTO create(CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();
        UsuarioEntity usuarioEntity = objectMapper.convertValue(recuperaUsuario, UsuarioEntity.class);

        CampanhaEntity campanhaEntity = objectMapper.convertValue(campanhaCreateDTO, CampanhaEntity.class);

        if(campanhaCreateDTO.getMetaArrecadacao().compareTo(BigDecimal.ZERO) <= 0){
            throw new RegraDeNegocioException("Meta de arrecadação deve ser maior do que zero!");
        }

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


    public void alteraStatusDaCampanhaQuandoMetaAtingida() {
        campanhaRepository.findAll().stream()
                .forEach(campanhaEntity -> {
                    if (campanhaEntity.getConcluiCampanhaAutomaticamente() &&
                            campanhaEntity.getTotalArrecadado().compareTo(campanhaEntity.getMetaArrecadacao()) >= 0) {
                        campanhaEntity.setStatusCampanha(false);

                        campanhaRepository.save(campanhaEntity);
                    }
                });
    }


    public void alteraStatusDaCampanhaQuandoAtingeDataDeEncerramento() {
        campanhaRepository.findAll().stream()
                .forEach(campanhaEntity -> {
                    if (campanhaEntity.getDataLimiteContribuicao().equals(LocalDate.now())) {
                        campanhaEntity.setStatusCampanha(false);
                        campanhaRepository.save(campanhaEntity);
                    }
                });

    }


    public List<CampanhaDTO> findByMetaAtingidaOuNaoAtingida(String meta) {
        if (StringUtils.containsAnyIgnoreCase(meta, "atingida")) {
            return campanhaRepository.findByMetaAtingida().stream()
                    .map(this::mapeamentoEConversao)
                    .collect(Collectors.toList());
        } else {
            return campanhaRepository.findByMetaNaoAtingida().stream()
                    .map(this::mapeamentoEConversao)
                    .collect(Collectors.toList());
        }
    }


    public List<CampanhaDTO> findByCampanhasCriadasPeloUsuarioQueEstaLogado() {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();

        return campanhaRepository.findByCampanhasCriadasPeloUsuarioLogado(recuperaUsuario.getIdUsuario()).stream()
                .map(this::mapeamentoEConversao)
                .collect(Collectors.toList());
    }


    public List<CampanhaUsuarioComDoacaoDTO> findByContribuicoesPeloUsuarioQueEstaLogado() {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();

        return campanhaRepository.findByContribuicoesPeloUsuarioQueEstaLogado(recuperaUsuario.getIdUsuario()).stream()
                .map(campanhaEntity -> {
                    CampanhaUsuarioComDoacaoDTO campanhaUsuarioComDoacaoDTO = objectMapper.convertValue(campanhaEntity, CampanhaUsuarioComDoacaoDTO.class);
                    try {
                        campanhaUsuarioComDoacaoDTO.setCategorias(campanhaEntity.getTagsCategoria().stream()
                                .map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                                .collect(Collectors.toSet()));
                        campanhaUsuarioComDoacaoDTO.setCriadorDaCampanha(usuarioService.getById(campanhaEntity.getIdUsuario().getIdUsuario()));

                        campanhaUsuarioComDoacaoDTO.setUsuarioDoacaoDTO(doacaoService.getValorTotalDoadoPeloUsuarioNaCampanha(recuperaUsuario.getIdUsuario(), campanhaEntity.getIdCampanha()));

                        if(campanhaUsuarioComDoacaoDTO.getMetaArrecadacao().compareTo(campanhaUsuarioComDoacaoDTO.getTotalArrecadado()) <= 0 ) {
                            campanhaUsuarioComDoacaoDTO.setMetaAtingida(true);
                        }else{
                            campanhaUsuarioComDoacaoDTO.setMetaAtingida(false);
                        }
                    } catch (RegraDeNegocioException e) {
                        e.printStackTrace();
                    }
                    return campanhaUsuarioComDoacaoDTO;
                })
                .collect(Collectors.toList());
    }


    public List<CampanhaDTO> list() {
        UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();

        return campanhaRepository.findyByIdDeUsuarioDiferenteDoLogado(recuperaUsuario.getIdUsuario()).stream()
                .map(this::mapeamentoEConversao)
                .collect(Collectors.toList());
    }


    public CampanhaDTO update(Integer idCampanha, CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(idCampanha);
        verificaSeCriador(campanhaEntity);

        if (campanhaEntity.getTotalArrecadado().compareTo(campanhaEntity.getMetaArrecadacao()) >= 0) {
            throw new RegraDeNegocioException("Campanha já possui doações, não é possível modifica-lá!");
        } else {
            UsuarioDTO recuperaUsuario = usuarioService.getUsuarioLogado();
            UsuarioEntity usuarioEntity = objectMapper.convertValue(recuperaUsuario, UsuarioEntity.class);

            campanhaEntity = objectMapper.convertValue(campanhaCreateDTO, CampanhaEntity.class);

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

            campanhaEntity.setIdCampanha(idCampanha);
            campanhaEntity.setIdUsuario(usuarioEntity);
            campanhaEntity.setTagsCategoria(categorias);
            campanhaEntity.setStatusCampanha(true);
            campanhaEntity.setTotalArrecadado(BigDecimal.ZERO);
            campanhaEntity.setUltimaAlteracao(LocalDateTime.now().minusHours(3));

            CampanhaEntity campanhaCriada = campanhaRepository.save(campanhaEntity);
            CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaCriada, CampanhaDTO.class);
            campanhaDTO.setCategorias(campanhaEntity.getTagsCategoria().stream()
                    .map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                    .collect(Collectors.toSet()));
            campanhaDTO.setCriadorCampanha(objectMapper.convertValue(usuarioEntity, UsuarioDTO.class));

            return campanhaDTO;
        }
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);
        verificaSeCriador(campanhaEntity);

        if (campanhaEntity.getTotalArrecadado().compareTo(campanhaEntity.getMetaArrecadacao()) >= 0) {
            throw new RegraDeNegocioException("Campanha já possui doações, não é possível deleta-lá!");

        } else {
            campanhaRepository.delete(campanhaEntity);
        }
    }


    public CampanhaDTO mapeamentoEConversao(CampanhaEntity campanhaEntity) {
        CampanhaDTO campanhaDTO = objectMapper.convertValue(campanhaEntity, CampanhaDTO.class);
        try {
            campanhaDTO.setCategorias(campanhaEntity.getTagsCategoria().stream()
                    .map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                    .collect(Collectors.toSet()));
            campanhaDTO.setCriadorCampanha(usuarioService.getById(campanhaEntity.getIdUsuario().getIdUsuario()));

            if(campanhaDTO.getMetaArrecadacao().compareTo(campanhaDTO.getTotalArrecadado()) <= 0 ) {
                campanhaDTO.setMetaAtingida(true);
            }else{
                campanhaDTO.setMetaAtingida(false);
            }
        } catch (RegraDeNegocioException e) {
            e.printStackTrace();
        }
        return campanhaDTO;
    }

    private void verificaSeCriador(CampanhaEntity campanhaEntity) throws RegraDeNegocioException {
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

    public CampanhaDetalheDTO getById(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);

        UsuarioDTO usuarioDTO = usuarioService.getUsuarioLogado();

        CampanhaDetalheDTO campanhaDetalheDTO = objectMapper.convertValue(campanhaEntity, CampanhaDetalheDTO.class);

        campanhaDetalheDTO.setUsuarioDoacaoDTOS(doacaoService.getUsuarioDoacaoAcumuladoPorIdCampanha(id)
                .stream().map(usuarioDoacaoDTO -> {
                    if (usuarioDoacaoDTO.getIdUsuario() != usuarioDTO.getIdUsuario()) {
                        usuarioDoacaoDTO.setValorTotalDoado(null);
                    }
                    return usuarioDoacaoDTO;
                }).collect(Collectors.toSet()));
        campanhaDetalheDTO.setTagsCategoria(campanhaEntity.getTagsCategoria()
                .stream().map(categoriaEntity -> objectMapper.convertValue(categoriaEntity, CategoriaDTO.class))
                .collect(Collectors.toSet()));

        campanhaDetalheDTO.setCriadorCampanha(usuarioDTO.getIdUsuario() == campanhaEntity.getIdUsuario().getIdUsuario());


        return campanhaDetalheDTO;
    }

    public CampanhaDTO saveEntity(CampanhaEntity campanhaEntity) {
        CampanhaEntity campanha = campanhaRepository.save(campanhaEntity);

        return objectMapper.convertValue(campanha, CampanhaDTO.class);
    }


}

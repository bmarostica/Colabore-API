package com.dbc.colabore.service;

import com.dbc.colabore.dto.*;
import com.dbc.colabore.entity.CampanhaEntity;
import com.dbc.colabore.entity.CategoriaEntity;
import com.dbc.colabore.entity.UsuarioEntity;
import com.dbc.colabore.exception.FileStorageException;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CampanhaRepository;
import com.dbc.colabore.repository.CategoriaRepository;
import com.dbc.colabore.repository.DoacaoRepository;
import com.dbc.colabore.repository.UsuarioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;

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

    //funcionando
    public void alteraStatusDaCampanhaQuandoMetaAtingida(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);
        if (campanhaEntity.getTotalArrecadado().compareTo(campanhaEntity.getMetaArrecadacao()) >= 0) {
            campanhaEntity.setStatusCampanha(false);
        } else
            throw new RegraDeNegocioException("A meta ainda não foi atingida");
        campanhaRepository.save(campanhaEntity);
    }

//
//    public List<CampanhaDTO> findByCampanhasConcluidas() {
//        return campanhaRepository.findByCampanhasConcluidas().stream()
//                .map(this::mapeamentoEConversao)
//                .collect(Collectors.toList());
//    }

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


    //funcionando!?
    public CampanhaDTO update(Integer idCampanha, CampanhaCreateDTO campanhaCreateDTO) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(idCampanha);
        verificaSeCriador(campanhaEntity);

        if (campanhaEntity.getTotalArrecadado().compareTo(BigDecimal.ZERO) > 0) {
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

    //funcionando
    public void delete(Integer id) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity = findById(id);
        verificaSeCriador(campanhaEntity);

        campanhaRepository.delete(campanhaEntity);
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

    public CampanhaDTO saveEntity(CampanhaEntity campanhaEntity) {
        CampanhaEntity campanha = campanhaRepository.save(campanhaEntity);

        return objectMapper.convertValue(campanha, CampanhaDTO.class);
    }


    public CampanhaDTO salvarFotoCampanha(MultipartFile file, int idCampanha) throws RegraDeNegocioException {
        CampanhaEntity campanhaEntity= findById(idCampanha);

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {

            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            campanhaEntity.setFileType(file.getContentType());
            campanhaEntity.setFoto(file.getBytes());

            CampanhaDTO campanhaDTO = saveEntity(campanhaEntity);

            return campanhaDTO;
        } catch (IOException ex) {
            throw new FileStorageException("Não foi possível armazenar o arquivo " + fileName + ". Por favor, tente novamente!", ex);
        }
    }

}

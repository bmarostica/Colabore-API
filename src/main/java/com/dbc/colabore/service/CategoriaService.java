package com.dbc.colabore.service;

import com.dbc.colabore.dto.CategoriaCreateDTOComNome;
import com.dbc.colabore.dto.CategoriaDTO;
import com.dbc.colabore.entity.CategoriaEntity;
import com.dbc.colabore.exception.RegraDeNegocioException;
import com.dbc.colabore.repository.CategoriaRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    public CategoriaDTO create(CategoriaCreateDTOComNome categoriaCreateDTOComNome) throws RegraDeNegocioException {
        Integer buscaCategoria = categoriaRepository.findByNomeCategoriaIlike(categoriaCreateDTOComNome.getNome());

        if(buscaCategoria > 0 ){
            throw new RegraDeNegocioException("Essa categoria já existe!");
        }

        CategoriaEntity categoriaEntity = objectMapper.convertValue(categoriaCreateDTOComNome, CategoriaEntity.class);
        CategoriaEntity categoriaCriada = categoriaRepository.save(categoriaEntity);
        CategoriaDTO categoriaDTO = objectMapper.convertValue(categoriaCriada, CategoriaDTO.class);
        return categoriaDTO;
    }

    public List<CategoriaDTO> list() {
        return categoriaRepository.findAll().stream()
                .map(categoria -> objectMapper.convertValue(categoria, CategoriaDTO.class))
                .collect(Collectors.toList());
    }

    public CategoriaEntity findById(Integer id) throws RegraDeNegocioException {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(id)
                .orElseThrow(() -> new RegraDeNegocioException("Categoria não localizada!"));
        return categoriaEntity;
    }

    public void delete(Integer id) throws RegraDeNegocioException {
        CategoriaEntity categoriaEntity = findById(id);
        categoriaRepository.delete(categoriaEntity);
    }

    public List<CategoriaDTO> listAsCategoriasExistentesNaCampanha(Integer idCampanha) {
        return categoriaRepository.listAsCategoriasExistentesNaCampanha(idCampanha).stream()
                .map(categoria -> objectMapper.convertValue(categoria, CategoriaDTO.class))
                .collect(Collectors.toList());
    }


}

package br.com.fujideia.iesp.tecback.utils;

import br.com.fujideia.iesp.tecback.model.*;
import br.com.fujideia.iesp.tecback.model.dto.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

public class Converter {

    public static FilmeDTO convertToDTO(Filme filme) {
        return new FilmeDTO(
                filme.getId(),
                filme.getTitulo(),
                filme.getAnoLancamento(),
                filme.getDiretor() != null ? convertToDTO(filme.getDiretor()) : null,
                filme.getAtores() != null ?
                        filme.getAtores().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList(),
                filme.getGeneros() != null ?
                        filme.getGeneros().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList(),
                filme.getProdutores() != null ?
                        filme.getProdutores().stream()
                                .map(Converter::convertToDTO)
                                .collect(Collectors.toList()) : Collections.emptyList()
        );
    }

    public static DiretorDTO convertToDTO(Diretor diretor) {
        return new DiretorDTO(
                diretor.getId(),
                diretor.getNome()
        );
    }

    public static GeneroDTO convertToDTO(Genero genero) {
        return new GeneroDTO(
                genero.getId(),
                genero.getNome()
        );
    }

    public static AtorDTO convertToDTO(Ator ator) {
        return new AtorDTO(
                ator.getId(),
                ator.getNome()
        );
    }

    public static ProdutorDTO convertToDTO(Produtor produtor) {
        return new ProdutorDTO(
                produtor.getId(),
                produtor.getNome(),
                produtor.getIdade(),
                produtor.getNacionalidade()
        );
    }

    public static Filme convertToEntity(FilmeDTO filmeDTO) {
        Filme filme = new Filme();
        filme.setTitulo(filmeDTO.getTitulo());
        filme.setAnoLancamento(filmeDTO.getAnoLancamento());
        filme.setDiretor(filmeDTO.getDiretor() != null ? convertToEntity(filmeDTO.getDiretor()) : null);
        filme.setAtores(filmeDTO.getAtores() != null ?
                filmeDTO.getAtores().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        filme.setGeneros(filmeDTO.getGeneros() != null ?
                filmeDTO.getGeneros().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        filme.setProdutores(filmeDTO.getProdutores() != null ?
                filmeDTO.getProdutores().stream().map(Converter::convertToEntity).collect(Collectors.toList()) :
                new ArrayList<>());
        return filme;
    }

    public static Diretor convertToEntity(DiretorDTO directorDTO) {
        if (directorDTO == null) {
            return null;
        }
        Diretor director = new Diretor();
        director.setId(directorDTO.getId());
        director.setNome(directorDTO.getNome());
        return director;
    }

    public static Ator convertToEntity(AtorDTO actorDTO) {
        Ator actor = new Ator();
        actor.setId(actorDTO.getId());
        actor.setNome(actorDTO.getNome());
        return actor;
    }

    public static Genero convertToEntity(GeneroDTO generoDTO) {
        Genero genero = new Genero();
        genero.setId(generoDTO.getId());
        genero.setNome(generoDTO.getNome());
        return genero;
    }

    public static Produtor convertToEntity(ProdutorDTO producerDTO) {
        Produtor produtor = new Produtor();
        produtor.setId(producerDTO.getId());
        produtor.setNome(producerDTO.getNome());
        produtor.setIdade(producerDTO.getIdade());
        produtor.setNacionalidade(producerDTO.getNacionalidade());
        return produtor;
    }
}

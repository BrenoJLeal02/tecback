package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.model.dto.AtorDTO;
import br.com.fujideia.iesp.tecback.model.dto.DiretorDTO;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.model.dto.GeneroDTO;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FilmeService {

    private final FilmeRepository filmeRepository;

    public List<FilmeDTO> listarTodos() {
        return filmeRepository.findAll()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<FilmeDTO> buscarPorId(Long id) {
        return filmeRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public FilmeDTO criarFilme(FilmeDTO filmeDTO) {
        Filme filme = Converter.convertToEntity(filmeDTO);
        return Converter.convertToDTO(filmeRepository.save(filme));
    }

    public Optional<FilmeDTO> atualizarFilme(Long id, FilmeDTO filmeDTO) {
        return filmeRepository.findById(id).map(filme -> {
            filme.setTitulo(filmeDTO.getTitulo());
            filme.setAnoLancamento(filmeDTO.getAnoLancamento());
            filme.setDiretor(Converter.convertToEntity(filmeDTO.getDiretor()));
            filme.setAtores(filmeDTO.getAtores().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
            filme.setGeneros(filmeDTO.getGeneros().stream().map(Converter::convertToEntity).collect(Collectors.toList()));
            return Converter.convertToDTO(filmeRepository.save(filme));
        });
    }

    public boolean deletarFilme(Long id) {
        if (filmeRepository.existsById(id)) {
            filmeRepository.deleteById(id);
            return true;
        }
        return false;
    }




}

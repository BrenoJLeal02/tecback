package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.model.dto.AtorDTO;
import br.com.fujideia.iesp.tecback.model.dto.DiretorDTO;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.model.dto.GeneroDTO;
import br.com.fujideia.iesp.tecback.repository.AtorRepository;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtorService {

    private final AtorRepository atorRepository;

    public List<AtorDTO> listarTodos() {
        return atorRepository.findAll()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<AtorDTO> buscarPorId(Long id) {
        return atorRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public AtorDTO criarAtor(AtorDTO atorDTO) {
        Ator ator = Converter.convertToEntity(atorDTO);
        return Converter.convertToDTO(atorRepository.save(ator));
    }

    public Optional<AtorDTO> atualizarAtor(Long id, AtorDTO atorDTO) {
        return atorRepository.findById(id).map(ator -> {
            ator.setNome(atorDTO.getNome());
            ator.setId(atorDTO.getId());

            return Converter.convertToDTO(atorRepository.save(ator));
        });
    }

    public boolean deletarAtor(Long id) {
        if (atorRepository.existsById(id)) {
            atorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

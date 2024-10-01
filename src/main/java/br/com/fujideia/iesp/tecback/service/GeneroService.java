package br.com.fujideia.iesp.tecback.service;



import br.com.fujideia.iesp.tecback.model.Ator;
import br.com.fujideia.iesp.tecback.model.Diretor;
import br.com.fujideia.iesp.tecback.model.Genero;
import br.com.fujideia.iesp.tecback.model.dto.AtorDTO;
import br.com.fujideia.iesp.tecback.model.dto.DiretorDTO;
import br.com.fujideia.iesp.tecback.model.dto.GeneroDTO;
import br.com.fujideia.iesp.tecback.repository.GeneroRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GeneroService {
    private final GeneroRepository generoRepository;

    public List<GeneroDTO> listarTodos() {
        return generoRepository.findAll()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<GeneroDTO> buscarPorId(Long id){
        return generoRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public GeneroDTO criarGenero(GeneroDTO generoDTO){
        Genero genero = Converter.convertToEntity(generoDTO);
        return Converter.convertToDTO(generoRepository.save(genero));
    }

    public Optional<GeneroDTO> atualizarGenero(Long id, GeneroDTO generoDTO){
        return  generoRepository.findById(id).map(genero -> {
            genero.setNome(generoDTO.getNome());
            genero.setId(generoDTO.getId());
            return Converter.convertToDTO(generoRepository.save(genero));
        });
    }

    public boolean deletarGenero(Long id){
        if(generoRepository.existsById(id)){
            generoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

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
import br.com.fujideia.iesp.tecback.repository.DiretorRepository;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiretorService {

    private final DiretorRepository diretorRepository;

    public List<DiretorDTO> listarTodos() {
        return diretorRepository.findAll()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<DiretorDTO> buscarPorId(Long id) {
        return diretorRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public DiretorDTO criarDiretor(DiretorDTO diretorDTO) {
        Diretor diretor = Converter.convertToEntity(diretorDTO);
        return Converter.convertToDTO(diretorRepository.save(diretor));
    }

    public Optional<DiretorDTO> atualizarDiretor(Long id, DiretorDTO diretorDTO) {
        return diretorRepository.findById(id).map(diretor -> {
            diretor.setNome(diretorDTO.getNome());
            diretor.setId(diretorDTO.getId());

            return Converter.convertToDTO(diretorRepository.save(diretor));
        });
    }

    public boolean deletarDiretor(Long id) {
        if (diretorRepository.existsById(id)) {
            diretorRepository.deleteById(id);
            return true;
        }
        return false;
    }

}

package br.com.fujideia.iesp.tecback.service;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.Produtor;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.repository.FilmeRepository;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import br.com.fujideia.iesp.tecback.utils.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProdutorService {
    private final ProdutorRepository produtorRepository;
    private final FilmeRepository filmeRepository;

    public List<ProdutorDTO> listarTodos(){
        return produtorRepository.findAll()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }


    public List<FilmeDTO> ListaFilmesProduzidos(Long producerId) {
        Produtor produtor = produtorRepository.findById(producerId)
                .orElseThrow(() -> new RuntimeException("Producer not found"));
        return produtor.getFilmesProduzidos()
                .stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ProdutorDTO> buscarPorId(Long id){
        return produtorRepository.findById(id)
                .map(Converter::convertToDTO);
    }

    public List<ProdutorDTO> buscarPorNome(String nome) {
        List<Produtor> produtor = produtorRepository.findByName(nome);
        return produtor.stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }


    public ProdutorDTO criarProdutor(ProdutorDTO produtorDTO){
        Produtor produtor = Converter.convertToEntity(produtorDTO);
        return Converter.convertToDTO(produtorRepository.save(produtor));
    }

    public Optional<ProdutorDTO> atualizarProdutor(Long id, ProdutorDTO produtorDTO){
        return  produtorRepository.findById(id).map(produtor ->{
            produtor.setId(produtorDTO.getId());
            produtor.setNome(produtorDTO.getNome());
            produtor.setIdade(produtorDTO.getIdade());
            produtor.setNacionalidade(produtorDTO.getNacionalidade());
            return Converter.convertToDTO(produtorRepository.save(produtor));
        });
    }
    public ProdutorDTO adicionarFilmeProdutor(Long produtorId, Long filmeId) {
        Produtor produtor = produtorRepository.findById(produtorId)
                .orElseThrow(() -> new RuntimeException("Producer not found"));
        Filme filme = filmeRepository.findById(filmeId)
                .orElseThrow(() -> new RuntimeException("Film not found"));

        if (!produtor.getFilmesProduzidos().contains(filme)) {
            produtor.getFilmesProduzidos().add(filme);
            produtorRepository.save(produtor);
        }
        return Converter.convertToDTO(produtor);
    }

    public boolean deletarProdutor(Long id){
        if(produtorRepository.existsById(id)){
            produtorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

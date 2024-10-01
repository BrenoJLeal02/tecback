package br.com.fujideia.iesp.tecback.controller;

import br.com.fujideia.iesp.tecback.model.Filme;
import br.com.fujideia.iesp.tecback.model.dto.FilmeDTO;
import br.com.fujideia.iesp.tecback.model.dto.ProdutorDTO;
import br.com.fujideia.iesp.tecback.repository.ProdutorRepository;
import br.com.fujideia.iesp.tecback.service.ProdutorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/produtores")
@RequiredArgsConstructor
@Slf4j
public class ProdutorController {
    private final ProdutorService produtorService;
    private final ProdutorRepository produtorRepository;


    @GetMapping
    public ResponseEntity<List<ProdutorDTO>> listarTodos(){
        log.info("Chamando listarTodos no ProdutorController");
        List<ProdutorDTO> produtores = produtorService.listarTodos();
        return ResponseEntity.ok(produtores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutorDTO> buscarPorId(@PathVariable Long id){
        log.info("Chamando buscarPorId no ProdutorController: {}", id);
        Optional<ProdutorDTO> produtor = produtorService.buscarPorId(id);
        return produtor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/busca")
    public ResponseEntity<List<ProdutorDTO>> buscarPorNome(@RequestParam String nome) {
        log.info("Chamando buscarPorNome no ProducerController para encontrar produtores com o nome: {}", nome);
        List<ProdutorDTO> produtorNome = produtorService.buscarPorNome(nome);
        return ResponseEntity.ok(produtorNome);
    }
    @GetMapping("/{produtorId}/filmes")
    public ResponseEntity<List<FilmeDTO>> ListaFilmesProduzidos(@PathVariable Long produtorId) {
        log.info("Chamando ListaFilmesProduzidos no ProducerController com o id: {}", produtorId);
        List<FilmeDTO> filmes = produtorService.ListaFilmesProduzidos(produtorId);
        return ResponseEntity.ok(filmes);
    }
    @PostMapping("/{produtorId}/filmes/{filmeId}")
    public ResponseEntity<ProdutorDTO> adicionarFilmeProdutor(@PathVariable Long produtorId, @PathVariable Long filmeId) {
        log.info("Chamando adicionarFilmeProdutor no ProducerController com producer_id: {} e film_id: {}", produtorId, filmeId);
        ProdutorDTO updatedProducer = produtorService.adicionarFilmeProdutor(produtorId, filmeId);
        return ResponseEntity.ok(updatedProducer);
    }

    @PostMapping
    public ResponseEntity<ProdutorDTO> criarProdutor(@RequestBody ProdutorDTO produtorDTO){
        log.info("Chamando o criarProdutor no ProdutorController: {}", produtorDTO);
        ProdutorDTO produtorCriado = produtorService.criarProdutor(produtorDTO);
        return ResponseEntity.ok(produtorCriado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProdutorDTO> atualizarProdutor(@PathVariable Long id, @RequestBody ProdutorDTO produtorDTO){
        log.info("Chamando atualizarProdutor no ProdutorController, id: {} dados: {}", id, produtorDTO);
        Optional<ProdutorDTO> atualizarProdutor = produtorService.atualizarProdutor(id, produtorDTO);
        return atualizarProdutor.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutorDTO> deletarProdutor(@PathVariable Long id){
        boolean deletado = produtorService.deletarProdutor(id);
        if(deletado){
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}

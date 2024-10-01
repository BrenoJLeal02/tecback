package br.com.fujideia.iesp.tecback.controller;


import br.com.fujideia.iesp.tecback.model.dto.GeneroDTO;
import br.com.fujideia.iesp.tecback.service.GeneroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/generos")
@RequiredArgsConstructor
@Slf4j
public class GeneroController {

    private final GeneroService generoService;

    @GetMapping
    public ResponseEntity<List<GeneroDTO>> listarTodos(){
        log.info("Chamar listarTodos no GeneroController");
        List<GeneroDTO> generos = generoService.listarTodos();
        return ResponseEntity.ok(generos);
    }

    @GetMapping("/id")
    public ResponseEntity <GeneroDTO> buscarPorId(@PathVariable Long id){
        log.info("Chamar buscar GeneroController id={}", id);
        Optional<GeneroDTO> genero = generoService.buscarPorId(id);
        return genero.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GeneroDTO> criarGenero(@RequestBody GeneroDTO generoDTO){
        log.info("Chamar criar GeneroController genero={}", generoDTO);
        GeneroDTO generoCriado = generoService.criarGenero(generoDTO);
        return ResponseEntity.ok(generoCriado);
    }

    @PutMapping("/id")
    public ResponseEntity<GeneroDTO> atualizarGenero(@PathVariable Long id, @RequestBody GeneroDTO generoDTO){
        log.info("Chamar atualizar GeneroController id={} e dados: {}", id, generoDTO);
        Optional<GeneroDTO> generoAtualizado = generoService.atualizarGenero(id, generoDTO);
        return generoAtualizado.map(ResponseEntity::ok)
         .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/id")
    public ResponseEntity<GeneroDTO> deletarGenero(@PathVariable Long id) {
        log.info("Chamar deletar GeneroController id={}", id);
        boolean deletado = generoService.deletarGenero(id);
        if (deletado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

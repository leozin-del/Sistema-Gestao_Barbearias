package com.barbearia.BARBEARIAPRO.controller;

import com.barbearia.BARBEARIAPRO.DTO.AtualizarBarbeiroDTO;
import com.barbearia.BARBEARIAPRO.DTO.BarbeiroDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarNovoBarbeiroDTO;
import com.barbearia.BARBEARIAPRO.service.BarbeiroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/barbeirosBarbearia")
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    public BarbeiroController(BarbeiroService barbeiroService) {
        this.barbeiroService = barbeiroService;
    }

    @PostMapping
    public ResponseEntity<BarbeiroDTO> criarBarbeiro(@RequestBody @Valid CriarNovoBarbeiroDTO criarNovoBarbeiroDTO){
        var barbeiroCriado = barbeiroService.criarNovoBarbeiro(criarNovoBarbeiroDTO);
        URI location = URI.create("/barbeirosBarbearia/" + barbeiroCriado.id());
        return ResponseEntity.created(location).body(barbeiroCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BarbeiroDTO> listarPorId(@PathVariable  Long id) {
        var barbeiroAchado = barbeiroService.listarPorId(id);
        if (barbeiroAchado.isPresent()) {
            return ResponseEntity.ok(barbeiroAchado.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<BarbeiroDTO>> listarTodos() {
        var barbeirosAchados = barbeiroService.listarTodos();
        return ResponseEntity.ok(barbeirosAchados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarBarbeiro(@PathVariable Long id, @RequestBody @Valid AtualizarBarbeiroDTO atualizarBarbeiroDTO) {
        barbeiroService.atualizarBarbeiro(id, atualizarBarbeiroDTO);
        return ResponseEntity.ok("Barbeiro atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarBarbeiro(@PathVariable Long id) {
        barbeiroService.deletarBarbeiro(id);
        return ResponseEntity.ok("Barbeiro deletado com sucesso!");
    }

}

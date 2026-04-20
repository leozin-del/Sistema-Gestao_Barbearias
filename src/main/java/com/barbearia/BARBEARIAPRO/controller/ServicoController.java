package com.barbearia.BARBEARIAPRO.controller;

import com.barbearia.BARBEARIAPRO.DTO.AtualizarServicoDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarServicoDTO;
import com.barbearia.BARBEARIAPRO.DTO.ServicoDTO;
import com.barbearia.BARBEARIAPRO.service.ServicoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/servicosBarbearia")
public class ServicoController {

    private final ServicoService servicoService;

    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    @PostMapping
    public ResponseEntity<ServicoDTO> criarNovo(@RequestBody @Valid CriarServicoDTO criarServicoDTO) {
        var servicoCriado = servicoService.criarServico(criarServicoDTO);
        URI location = URI.create("/servicosBarbearia/" + servicoCriado.id());
        return ResponseEntity.created(location).body(servicoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicoDTO> listarPorId(@PathVariable Long id) {
        var servicoAchado = servicoService.listarPorId(id);
        if (servicoAchado.isPresent()) {
            return ResponseEntity.ok(servicoAchado.get());
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ServicoDTO>> listarTodos() {
        var servicosAchados = servicoService.listarTodos();
        return ResponseEntity.ok(servicosAchados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarServico(@PathVariable Long id, @RequestBody @Valid AtualizarServicoDTO atualizarServicoDTO) {
        servicoService.atualizarServico(id, atualizarServicoDTO);
        return ResponseEntity.ok("Serviço atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarServico(@PathVariable Long id) {
        servicoService.deletarServico(id);
        return ResponseEntity.ok("Serviço deletado com sucesso!");
    }
}


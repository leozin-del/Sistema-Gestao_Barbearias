package com.barbearia.BARBEARIAPRO.controller;

import com.barbearia.BARBEARIAPRO.DTO.AgendamentoDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarAgendamentoDTO;
import com.barbearia.BARBEARIAPRO.service.AgendamentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/agendamentosBarbearia")
public class AgendamentoController {

    private final AgendamentoService agendamentoService;

    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping
    public ResponseEntity<AgendamentoDTO> criarNovo(@RequestBody  CriarAgendamentoDTO criarAgendamentoDTO) {
        var agendamentoCriado = agendamentoService.criarNovo(criarAgendamentoDTO);
        URI location = URI.create("/agendamentosBarbearia/" + agendamentoCriado.id());
        return ResponseEntity.created(location).body(agendamentoCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AgendamentoDTO> listarPorId(@PathVariable Long id) {

        var agendamento = agendamentoService.listarPorId(id);

        if (agendamento.isPresent()) {
            return ResponseEntity.ok(agendamento.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/barbeiro/{barbeiroId}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorBarbeiro(
            @PathVariable Long barbeiroId) {
        return ResponseEntity.ok(agendamentoService.listarPorBarbeiro(barbeiroId));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<AgendamentoDTO>> listarPorCliente(
            @PathVariable Long clienteId) {

        return ResponseEntity.ok(
                agendamentoService.listarPorCliente(clienteId)
        );
    }

    @PutMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Long id) {

        agendamentoService.cancelar(id);
        return ResponseEntity.noContent().build();
    }

}

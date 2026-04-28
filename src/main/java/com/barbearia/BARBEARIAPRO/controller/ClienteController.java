package com.barbearia.BARBEARIAPRO.controller;

import com.barbearia.BARBEARIAPRO.DTO.AtualizarClienteDTO;
import com.barbearia.BARBEARIAPRO.DTO.ClienteDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarClienteDTO;
import com.barbearia.BARBEARIAPRO.exception.ClientNotFoundException;
import com.barbearia.BARBEARIAPRO.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientesBarbearia")
public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @PostMapping
    public ResponseEntity<ClienteDTO> criarNovo(@RequestBody @Valid CriarClienteDTO criarClienteDTO) {
        var clienteCriado = clienteService.criarCliente(criarClienteDTO);
        URI location = URI.create("/clientesBarbearia/" + clienteCriado.id());
        return ResponseEntity.created(location).body(clienteCriado);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> listarPorId(@PathVariable Long id) {
        var clienteAchado = clienteService.listarPorId(id);
        return clienteAchado.map(ResponseEntity::ok)
                .orElseThrow(() -> new ClientNotFoundException("Cliente não encontrado!"));
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listarTodos() {
        var clientesAchados = clienteService.listarClientes();
        return ResponseEntity.ok(clientesAchados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> atualizarCliente(@PathVariable Long id, @RequestBody @Valid AtualizarClienteDTO atualizarClienteDTO) {
        clienteService.atualizarCliente(id, atualizarClienteDTO);
        return ResponseEntity.ok("Cliente atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletarCliente(@PathVariable Long id) {
        clienteService.deletarCliente(id);
        return ResponseEntity.ok("Cliente deletado com sucesso!");
    }
}
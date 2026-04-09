package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.DTO.AtualizarClienteDTO;
import com.barbearia.BARBEARIAPRO.DTO.ClienteDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarClienteDTO;
import com.barbearia.BARBEARIAPRO.Role;
import com.barbearia.BARBEARIAPRO.entity.Cliente;
import com.barbearia.BARBEARIAPRO.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final UsuarioService usuarioService;

    public ClienteService(ClienteRepository clienteRepository, UsuarioService usuarioService) {
        this.clienteRepository = clienteRepository;
        this.usuarioService = usuarioService;
    }

    public ClienteDTO criarCliente(CriarClienteDTO criarClienteDTO) {

        var usuario = usuarioService.criarUsuario(
                criarClienteDTO.email(),
                criarClienteDTO.senha(),
                Role.CLIENTE
        );

        Cliente cliente = new Cliente(null, criarClienteDTO.name(),
                criarClienteDTO.tell(), usuario);

        var clienteCriado = clienteRepository.save(cliente);

        return new ClienteDTO(clienteCriado.getId(), clienteCriado.getName(), clienteCriado.getTell());
    }

    public Optional<ClienteDTO> listarPorId(Long id) {
        return clienteRepository.findById(id).map(cliente -> new ClienteDTO(
                cliente.getId(),
                cliente.getName(),
                cliente.getTell()
        ));
    }

    public List<ClienteDTO> listarClientes() {
        return clienteRepository.findAll().stream().map(cliente -> new ClienteDTO(
                cliente.getId(),
                cliente.getName(),
                cliente.getTell()
        )).toList();
    }

    public void atualizarCliente(Long id, AtualizarClienteDTO atualizarClienteDTO) {
        var clienteBuscado = clienteRepository.findById(id);
        if (clienteBuscado.isPresent()) {
            var clienteAchado = clienteBuscado.get();

            if (atualizarClienteDTO.name() != null) {
                clienteAchado.setName(atualizarClienteDTO.name());
            }
            if (atualizarClienteDTO.tell() != null) {
                clienteAchado.setTell(atualizarClienteDTO.tell());
            }

            clienteRepository.save(clienteAchado);
        }
    }

    public void deletarCliente(Long id) {
        clienteRepository.deleteById(id);
    }

}

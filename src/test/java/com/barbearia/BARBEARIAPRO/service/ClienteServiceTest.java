package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.DTO.CriarClienteDTO;
import com.barbearia.BARBEARIAPRO.Role;
import com.barbearia.BARBEARIAPRO.entity.Cliente;
import com.barbearia.BARBEARIAPRO.entity.UsuarioBarbearia;
import com.barbearia.BARBEARIAPRO.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepository;
    @Mock
    private UsuarioService usuarioService;
    @InjectMocks
    private ClienteService clienteService;

    @Captor
    private ArgumentCaptor<Cliente> argumentCaptor;


    //arrange
    @Nested
    class criarNovoCliente {
        @Test
        @DisplayName("DeveCriarNovoCliente")
        void deveCriarCliente() {
            var criarClienteInput = new  CriarClienteDTO("Leo",
                    "61996178447",
                    "leo@gmail.com",
                    "123456");

            var usuarioEsperado = new UsuarioBarbearia(1L,
                    "leo@gmail.com",
                    "123456",
                    Role.CLIENTE);
            var clienteEsperado = new Cliente(1L, "leo", "61996178447", usuarioEsperado);

            when(usuarioService.criarUsuario(criarClienteInput.email(),
                    criarClienteInput.senha(),
                    Role.CLIENTE)).thenReturn(usuarioEsperado);
            when(clienteRepository.save(any(Cliente.class))).thenReturn(clienteEsperado);

            //act
            var clienteCriado = clienteService.criarCliente(criarClienteInput);
            verify(clienteRepository).save(argumentCaptor.capture());
            var clienteCapturado = argumentCaptor.getValue();

            assertNotNull(clienteCriado);
            assertEquals("leo", clienteCapturado.getName());
            assertEquals("61996178447", clienteCapturado.getTell());
            assertEquals(usuarioEsperado, clienteCapturado.getUsuario());
        }
    }

    @Nested
    class listarPorId {
        @Test
        @DisplayName("DeveEncontrarPeloId")
        void listarPorId() {
            Long id = 1L;
            var usuarioExistente = new UsuarioBarbearia(id,
                    "leo@gmail,com",
                    "123456",
                    Role.CLIENTE);

            var clienteExistente = new Cliente(id,
                    "Leo",
                    "61996178447",
                    usuarioExistente);

            when(clienteRepository.findById(id)).thenReturn(Optional.of(clienteExistente));

            var resultado = clienteService.listarPorId(id);

            assertTrue(resultado.isPresent());
            assertEquals("Leo", resultado.get().name());
            assertEquals("61996178447", resultado.get().tell());

        }
    }


    @Nested
    class listarTodos {
        @Test
        @DisplayName("DeveListarTodos")
        void listarTodos() {
            var usuarioExistente = new UsuarioBarbearia(1L, "leo@gmail.com",
                    "123456",
                    Role.CLIENTE);
            var clienteExistente = new Cliente(1L, "Leo", "61996178447", usuarioExistente);

            when(clienteRepository.findAll()).thenReturn(List.of(clienteExistente));

            var resultado = clienteService.listarClientes();
            assertNotNull(resultado);

            assertEquals(1, resultado.size());

            assertEquals("Leo", resultado.get(0).name());
            assertEquals("61996178447", resultado.get(0).tell());
        }
    }

}
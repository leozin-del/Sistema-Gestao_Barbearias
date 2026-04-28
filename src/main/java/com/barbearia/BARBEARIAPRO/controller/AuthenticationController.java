package com.barbearia.BARBEARIAPRO.controller;

import com.barbearia.BARBEARIAPRO.DTO.CriarClienteDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarNovoBarbeiroDTO;
import com.barbearia.BARBEARIAPRO.DTO.LoginRequestDTO;
import com.barbearia.BARBEARIAPRO.DTO.LoginResponseDTO;
import com.barbearia.BARBEARIAPRO.config.TokenConfig;
import com.barbearia.BARBEARIAPRO.service.AuthenticationService;
import com.barbearia.BARBEARIAPRO.service.BarbeiroService;
import com.barbearia.BARBEARIAPRO.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final ClienteService clienteService;
    private final BarbeiroService barbeiroService;


    public AuthenticationController(AuthenticationService authenticationService,
                                   ClienteService clienteService,
                                   BarbeiroService barbeiroService) {
        this.authenticationService = authenticationService;
        this.clienteService = clienteService;
        this.barbeiroService = barbeiroService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO) {
        var resultado = authenticationService.login(loginRequestDTO);
        return ResponseEntity.ok(resultado);
    }

    @PostMapping("/registrar-cliente")
    public ResponseEntity<?> registrarCliente(@RequestBody @Valid CriarClienteDTO criarClienteDTO) {
        var cliente = clienteService.criarCliente(criarClienteDTO);
        return ResponseEntity.status(201).body(cliente);
    }

    @PostMapping("/registrar-barbeiro")
    public ResponseEntity<?> registrarBarbeiro(@RequestBody @Valid CriarNovoBarbeiroDTO criarNovoBarbeiroDTO) {
        var barbeiro = barbeiroService.criarNovoBarbeiro(criarNovoBarbeiroDTO);
        return ResponseEntity.status(201).body(barbeiro);
    }
}


package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.DTO.LoginRequestDTO;
import com.barbearia.BARBEARIAPRO.DTO.LoginResponseDTO;
import com.barbearia.BARBEARIAPRO.entity.UsuarioBarbearia;
import com.barbearia.BARBEARIAPRO.exception.UserNotFoundException;
import com.barbearia.BARBEARIAPRO.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        UsuarioBarbearia usuario = usuarioRepository.findByEmail(loginRequestDTO.email())
                .orElseThrow(() -> new UserNotFoundException("Usuário ou senha inválidos"));

        // Validar senha (comparação simples - em produção usar BCrypt)
        if (!passwordEncoder.matches(loginRequestDTO.senha(), usuario.getSenha())) {
            throw new UserNotFoundException("Usuário ou senha inválidos");
        }

        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRole(),
                "Login realizado com sucesso!"
        );
    }
}


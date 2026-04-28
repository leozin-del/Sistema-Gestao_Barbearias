package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.DTO.LoginRequestDTO;
import com.barbearia.BARBEARIAPRO.DTO.LoginResponseDTO;
import com.barbearia.BARBEARIAPRO.config.TokenConfig;
import com.barbearia.BARBEARIAPRO.entity.UsuarioBarbearia;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final TokenConfig tokenConfig;

    public AuthenticationService(AuthenticationManager authenticationManager, TokenConfig tokenConfig) {
        this.authenticationManager = authenticationManager;
        this.tokenConfig = tokenConfig;
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        UsernamePasswordAuthenticationToken userAndPass =
                new UsernamePasswordAuthenticationToken(
                        loginRequestDTO.email(),
                        loginRequestDTO.senha()
                );

        Authentication authentication =
                authenticationManager.authenticate(userAndPass);

        UsuarioBarbearia usuario =
                (UsuarioBarbearia) authentication.getPrincipal();

        String token = tokenConfig.generateToken(usuario);
        
        return new LoginResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getRole(),
                "Login realizado com sucesso!",
                token
        );
    }
}


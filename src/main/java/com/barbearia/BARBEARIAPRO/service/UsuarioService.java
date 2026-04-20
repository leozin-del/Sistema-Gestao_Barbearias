package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.Role;
import com.barbearia.BARBEARIAPRO.entity.UsuarioBarbearia;
import com.barbearia.BARBEARIAPRO.exception.EmailAlreadyInUseException;
import com.barbearia.BARBEARIAPRO.exception.UserNotFoundException;
import com.barbearia.BARBEARIAPRO.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioBarbearia criarUsuario(String email, String senha, Role role) {


        if (usuarioRepository.existsByEmail(email)) {
            throw new EmailAlreadyInUseException("Email já esta em uso!");
        }


        UsuarioBarbearia usuario = new UsuarioBarbearia(
                null,
                email,
                senha,
                role
        );

        return usuarioRepository.save(usuario);
    }

    public UsuarioBarbearia buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("Usuário não encontrado!"));
    }
}

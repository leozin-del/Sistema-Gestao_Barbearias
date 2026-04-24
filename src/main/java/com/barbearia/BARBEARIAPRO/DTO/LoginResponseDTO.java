package com.barbearia.BARBEARIAPRO.DTO;

import com.barbearia.BARBEARIAPRO.Role;

public record LoginResponseDTO(
        Long usuarioId,
        String email,
        Role role,
        String mensagem
) {
}


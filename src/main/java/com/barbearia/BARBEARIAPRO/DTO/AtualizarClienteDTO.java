package com.barbearia.BARBEARIAPRO.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AtualizarClienteDTO(@NotBlank String name, @Pattern(
        regexp = "^[0-9]{10,11}$",
        message = "O telefone deve conter apenas números e ter entre 10 e 11 dígitos") String tell) {
}


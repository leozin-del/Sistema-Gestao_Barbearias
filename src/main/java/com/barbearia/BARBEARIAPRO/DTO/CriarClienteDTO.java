package com.barbearia.BARBEARIAPRO.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record CriarClienteDTO(@NotBlank String name, @NotBlank String tell, @NotBlank String email,
                              @NotBlank String senha) {
}

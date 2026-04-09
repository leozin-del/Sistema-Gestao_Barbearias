package com.barbearia.BARBEARIAPRO.DTO;

import jakarta.validation.constraints.NotBlank;

public record CriarNovoBarbeiroDTO(@NotBlank String name, @NotBlank String especialidade, @NotBlank String email,
                                   @NotBlank String senha) {
}

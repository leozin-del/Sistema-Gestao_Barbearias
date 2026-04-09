package com.barbearia.BARBEARIAPRO.DTO;

import jakarta.validation.constraints.NotBlank;

public record AtualizarBarbeiroDTO(@NotBlank String name, @NotBlank String especialidade) {
}

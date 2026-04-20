package com.barbearia.BARBEARIAPRO.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizarServicoDTO(
        @NotBlank(message = "Nome do serviço não pode estar vazio")
        String name,
        
        @NotNull(message = "Preço não pode ser nulo")
        @Positive(message = "Preço deve ser positivo")
        Double preco) {
}


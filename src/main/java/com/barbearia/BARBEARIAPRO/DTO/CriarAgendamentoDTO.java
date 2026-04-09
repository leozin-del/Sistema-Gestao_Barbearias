package com.barbearia.BARBEARIAPRO.DTO;

import java.time.LocalDateTime;

public record CriarAgendamentoDTO(LocalDateTime dataHora,
                                  Long clienteId,
                                  Long barbeiroId,
                                  Long servicoId) {
}

package com.barbearia.BARBEARIAPRO.DTO;

import java.time.LocalDateTime;
import java.util.List;

public record CriarAgendamentoDTO(LocalDateTime dataHora,
                                  Long clienteId,
                                  Long barbeiroId,
                                  List<Long> servicoIds) {
}

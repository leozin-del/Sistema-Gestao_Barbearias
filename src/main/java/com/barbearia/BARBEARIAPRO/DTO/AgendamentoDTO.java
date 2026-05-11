package com.barbearia.BARBEARIAPRO.DTO;

import com.barbearia.BARBEARIAPRO.StatusAgendamento;

import java.time.LocalDateTime;
import java.util.List;

public record AgendamentoDTO(Long id,
                             LocalDateTime dataHora,
                             String cliente,
                             String barbeiro,
                             List<String> servico,
                             StatusAgendamento status) {
}

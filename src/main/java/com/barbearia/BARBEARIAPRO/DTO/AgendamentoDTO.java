package com.barbearia.BARBEARIAPRO.DTO;

import com.barbearia.BARBEARIAPRO.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoDTO(Long id,
                             LocalDateTime dataHora,
                             String cliente,
                             String barbeiro,
                             String servico,
                             StatusAgendamento status) {
}

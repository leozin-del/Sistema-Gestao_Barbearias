package com.barbearia.BARBEARIAPRO.service;


import com.barbearia.BARBEARIAPRO.DTO.AgendamentoDTO;
import com.barbearia.BARBEARIAPRO.DTO.BarbeiroDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarAgendamentoDTO;
import com.barbearia.BARBEARIAPRO.StatusAgendamento;
import com.barbearia.BARBEARIAPRO.entity.Agendamento;
import com.barbearia.BARBEARIAPRO.entity.Barbeiro;
import com.barbearia.BARBEARIAPRO.entity.Cliente;
import com.barbearia.BARBEARIAPRO.entity.Servico;
import com.barbearia.BARBEARIAPRO.repository.AgendamentoRepository;
import com.barbearia.BARBEARIAPRO.repository.BarbeiroRepository;
import com.barbearia.BARBEARIAPRO.repository.ClienteRepository;
import com.barbearia.BARBEARIAPRO.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {


    private final AgendamentoRepository agendamentoRepository;
    private final ClienteRepository clienteRepository;
    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

    public AgendamentoService(AgendamentoRepository agendamentoRepository, ClienteRepository clienteRepository, BarbeiroRepository barbeiroRepository, ServicoRepository servicoRepository) {
        this.agendamentoRepository = agendamentoRepository;
        this.clienteRepository = clienteRepository;
        this.barbeiroRepository = barbeiroRepository;
        this.servicoRepository = servicoRepository;
    }


    public AgendamentoDTO criarNovo(CriarAgendamentoDTO dto) {

        Cliente cliente = clienteRepository.findById(dto.clienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Barbeiro barbeiro = barbeiroRepository.findById(dto.barbeiroId())
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        Servico servico = servicoRepository.findById(dto.servicoId())
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        boolean horarioOcupado =
                agendamentoRepository.existsByBarbeiroAndDataHora(barbeiro, dto.dataHora());

        if (horarioOcupado) {
            throw new RuntimeException("Horário já ocupado para esse barbeiro");
        }


        Agendamento agendamento = new Agendamento();
        agendamento.setDataHora(dto.dataHora());
        agendamento.setCliente(cliente);
        agendamento.setBarbeiro(barbeiro);
        agendamento.setServico(servico);
        agendamento.setStatus(StatusAgendamento.AGENDADO);

        Agendamento salvo = agendamentoRepository.save(agendamento);

        return new AgendamentoDTO(
                salvo.getId(),
                salvo.getDataHora(),
                salvo.getCliente().getName(),
                salvo.getBarbeiro().getName(),
                salvo.getServico().getName(),
                salvo.getStatus()
        );
    }

    public Optional<AgendamentoDTO> listarPorId(Long id) {
        return agendamentoRepository.findById(id)
                .map(agendamento -> new AgendamentoDTO(
                        agendamento.getId(),
                        agendamento.getDataHora(),
                        agendamento.getCliente().getName(),
                        agendamento.getBarbeiro().getName(),
                        agendamento.getServico().getName(),
                        agendamento.getStatus()
                ));
    }

    public List<AgendamentoDTO> listarPorBarbeiro(Long barbeiroId) {

        List<Agendamento> agendamentos =
                agendamentoRepository.findByBarbeiroId(barbeiroId);

        List<AgendamentoDTO> resultado = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            resultado.add(new AgendamentoDTO(
                    agendamento.getId(),
                    agendamento.getDataHora(),
                    agendamento.getCliente().getName(),
                    agendamento.getBarbeiro().getName(),
                    agendamento.getServico().getName(),
                    agendamento.getStatus()
            ));
        }

        return resultado;
    }

    public List<AgendamentoDTO> listarPorCliente(Long clienteId) {

        List<Agendamento> agendamentos =
                agendamentoRepository.findByClienteId(clienteId);

        List<AgendamentoDTO> resultado = new ArrayList<>();

        for (Agendamento agendamento : agendamentos) {
            resultado.add(new AgendamentoDTO(
                    agendamento.getId(),
                    agendamento.getDataHora(),
                    agendamento.getCliente().getName(),
                    agendamento.getBarbeiro().getName(),
                    agendamento.getServico().getName(),
                    agendamento.getStatus()
            ));
        }

        return resultado;
    }

    public void cancelar(Long id) {

        Agendamento agendamento = agendamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Agendamento não encontrado"));

        agendamento.setStatus(StatusAgendamento.CANCELADO);
        agendamentoRepository.save(agendamento);
    }

    // ===================== MAPPER =====================
    private AgendamentoDTO mapToDTO(Agendamento agendamento) {
        return new AgendamentoDTO(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getCliente().getName(),
                agendamento.getBarbeiro().getName(),
                agendamento.getServico().getName(),
                agendamento.getStatus()
        );
    }

}

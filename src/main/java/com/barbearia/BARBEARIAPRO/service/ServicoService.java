package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.DTO.AtualizarServicoDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarServicoDTO;
import com.barbearia.BARBEARIAPRO.DTO.ServicoDTO;
import com.barbearia.BARBEARIAPRO.entity.Servico;
import com.barbearia.BARBEARIAPRO.repository.ServicoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicoService {

    private final ServicoRepository servicoRepository;

    public ServicoService(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public ServicoDTO criarServico(CriarServicoDTO criarServicoDTO) {
        Servico servico = new Servico();
        servico.setName(criarServicoDTO.name());
        servico.setPreco(criarServicoDTO.preco());

        Servico servicoSalvo = servicoRepository.save(servico);

        return new ServicoDTO(
                servicoSalvo.getId(),
                servicoSalvo.getName(),
                servicoSalvo.getPreco()
        );
    }

    public Optional<ServicoDTO> listarPorId(Long id) {
        return servicoRepository.findById(id)
                .map(servico -> new ServicoDTO(
                        servico.getId(),
                        servico.getName(),
                        servico.getPreco()
                ));
    }

    public List<ServicoDTO> listarTodos() {
        return servicoRepository.findAll()
                .stream()
                .map(servico -> new ServicoDTO(
                        servico.getId(),
                        servico.getName(),
                        servico.getPreco()
                ))
                .toList();
    }

    public void atualizarServico(Long id, AtualizarServicoDTO atualizarServicoDTO) {
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Serviço não encontrado"));

        servico.setName(atualizarServicoDTO.name());
        servico.setPreco(atualizarServicoDTO.preco());

        servicoRepository.save(servico);
    }

    public void deletarServico(Long id) {
        if (!servicoRepository.existsById(id)) {
            throw new RuntimeException("Serviço não encontrado");
        }
        servicoRepository.deleteById(id);
    }
}


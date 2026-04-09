package com.barbearia.BARBEARIAPRO.service;

import com.barbearia.BARBEARIAPRO.DTO.AtualizarBarbeiroDTO;
import com.barbearia.BARBEARIAPRO.DTO.BarbeiroDTO;
import com.barbearia.BARBEARIAPRO.DTO.CriarNovoBarbeiroDTO;
import com.barbearia.BARBEARIAPRO.Role;
import com.barbearia.BARBEARIAPRO.entity.Barbeiro;
import com.barbearia.BARBEARIAPRO.repository.BarbeiroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final UsuarioService usuarioService;

    public BarbeiroService(BarbeiroRepository barbeiroRepository, UsuarioService usuarioService) {
        this.barbeiroRepository = barbeiroRepository;
        this.usuarioService = usuarioService;
    }

    public BarbeiroDTO criarNovoBarbeiro(CriarNovoBarbeiroDTO criarNovoBarbeiroDTO) {

        var usuario = usuarioService.criarUsuario(
                criarNovoBarbeiroDTO.email(),
                criarNovoBarbeiroDTO.senha(),
                Role.BARBEIRO
        );

        Barbeiro barbeiro = new Barbeiro(null, criarNovoBarbeiroDTO.name(),
                criarNovoBarbeiroDTO.especialidade(), usuario);



        var barbeiroCriado = barbeiroRepository.save(barbeiro);

        return new BarbeiroDTO(barbeiroCriado.getId(), barbeiroCriado.getName(), barbeiroCriado.getEspecialidade());
    }

    public Optional<BarbeiroDTO> listarPorId(Long id) {
        return barbeiroRepository.findById(id).map(barbeiro -> new BarbeiroDTO(
                barbeiro.getId(),
                barbeiro.getName(),
                barbeiro.getEspecialidade()
        ));
    }

    public List<BarbeiroDTO> listarTodos() {
        return barbeiroRepository.findAll().stream().map(barbeiro -> new BarbeiroDTO(
                barbeiro.getId(),
                barbeiro.getName(),
                barbeiro.getEspecialidade()
        )).toList();
    }

    public void atualizarBarbeiro(Long id, AtualizarBarbeiroDTO atualizarBarbeiroDTO) {
        var barbeiroBuscado = barbeiroRepository.findById(id);
        if (barbeiroBuscado.isPresent()) {
            var barbeiroAchado = barbeiroBuscado.get();

            if (atualizarBarbeiroDTO.name() != null) {
                barbeiroAchado.setName(atualizarBarbeiroDTO.name());
            }

            if (atualizarBarbeiroDTO.especialidade() != null) {
                barbeiroAchado.setEspecialidade(atualizarBarbeiroDTO.especialidade());
            }

            barbeiroRepository.save(barbeiroAchado);
        }
    }

    public void deletarBarbeiro(Long id) {
        barbeiroRepository.deleteById(id);
    }
}

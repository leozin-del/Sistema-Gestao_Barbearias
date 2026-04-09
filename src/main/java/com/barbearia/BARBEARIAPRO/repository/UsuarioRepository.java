package com.barbearia.BARBEARIAPRO.repository;

import com.barbearia.BARBEARIAPRO.entity.Barbeiro;
import com.barbearia.BARBEARIAPRO.entity.UsuarioBarbearia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioBarbearia, Long> {

    Optional<UsuarioBarbearia> findByEmail(String email);

}

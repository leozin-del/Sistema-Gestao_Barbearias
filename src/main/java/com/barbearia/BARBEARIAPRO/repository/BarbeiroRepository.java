package com.barbearia.BARBEARIAPRO.repository;

import com.barbearia.BARBEARIAPRO.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
}

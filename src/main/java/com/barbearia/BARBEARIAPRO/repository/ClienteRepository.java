package com.barbearia.BARBEARIAPRO.repository;

import com.barbearia.BARBEARIAPRO.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}

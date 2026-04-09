package com.barbearia.BARBEARIAPRO.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Clientes")
public class Cliente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String tell;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private UsuarioBarbearia usuario;


}
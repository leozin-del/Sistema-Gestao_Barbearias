package com.barbearia.BARBEARIAPRO.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Barbeiros")
public class Barbeiro {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String especialidade;

    @OneToOne
    @JoinColumn(name = "usuario_id", unique = true)
    private UsuarioBarbearia usuario;


}

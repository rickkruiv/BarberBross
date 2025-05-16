package com.barberbross.barberbross.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HorariosFuncionamento {
    
    @Id @GeneratedValue
    private Long id;

    private LocalTime horarioInicio;
    private LocalTime horarioFim;
}

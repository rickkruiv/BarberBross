package com.barberbross.barberbross.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class HorarioDisponivelBarbeiro {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    private LocalTime horario;

    @ManyToOne
    @JoinColumn( name = "barbeiro_id" )
    private Barbeiro barbeiro;
}

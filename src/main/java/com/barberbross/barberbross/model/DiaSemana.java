package com.barberbross.barberbross.model;

import java.util.List;

import com.barberbross.barberbross.enums.DiaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class DiaSemana {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;

    @Enumerated( EnumType.STRING )
    private DiaEnum dia;

    @ManyToMany( mappedBy = "diasDisponiveis" )
    @JsonIgnore
    private List<Barbeiro> barbeiros;
}

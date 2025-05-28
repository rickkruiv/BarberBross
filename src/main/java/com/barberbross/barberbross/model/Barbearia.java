package com.barberbross.barberbross.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.barberbross.barberbross.enums.DiaEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Barbearia {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "Nome e obrigatorio" )
    private String nome;

    private LocalDate dataCadastro;

    @Embedded
    private Endereco endereco;

    private DiaEnum diaInicio;
    private DiaEnum diaFim;

    private LocalTime horaInicio;
    private LocalTime horaFim;

    @OneToMany( mappedBy = "barbearia", cascade = CascadeType.ALL )
    @JsonIgnore
    private List<Barbeiro> barbeiros;
    
    @OneToMany( mappedBy = "barbearia", cascade = CascadeType.ALL )
    @JsonIgnore
    private List<Servico> servicos;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }
}

package com.barberbross.barberbross.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Barbeiro {
    
    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "Nome e obrigatorio" )
    private String nome;

    private LocalDate dataCadastro;

    // especialidade nao e obrigatorio
    private String especialidade;

    @ManyToMany
    @JoinTable(
        name = "barbeiro_dias",
        joinColumns = @JoinColumn( name = "barbeiro_id" ),
        inverseJoinColumns = @JoinColumn( name = "dia_id" )
    )
    @JsonIgnore
    private List<DiaSemana> diasDisponiveis;
    
    @OneToMany( mappedBy = "barbeiro", cascade = CascadeType.ALL )
    @JsonIgnore
    private List<HorarioDisponivelBarbeiro> horariosExecao;
    
    @ManyToOne
    @JoinColumn( name = "barbearia_id" )
    private Barbearia barbearia;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }
}

package com.barberbross.barberbross.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
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
public class Cliente {

    @Id
    @GeneratedValue( strategy =  GenerationType.IDENTITY )
    private Long id;

    @NotBlank( message = "Nome e obrigatorio" )
    private String nome;

    @NotBlank( message = "Telefone e obrigatorio" )
    private String telefone;

    // nao e obrigatorio
    private String email;

    private LocalDate dataCadastro;

    @OneToMany( mappedBy = "cliente", cascade = CascadeType.ALL  )
    @JsonIgnore
    private List<Agendamento> agendamentos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDate.now();
    }
}

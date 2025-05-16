package com.barberbross.barberbross.dto;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarbeiroDTO {

    private Long id;
    private String nome;
    private LocalDate dataCadastro;
    private String especialidade;
    private Long barbeariaId;
}

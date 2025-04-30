package com.barberbross.barberbross.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.barberbross.barberbross.enums.StatusAgendamento;

import lombok.Data;

@Data
public class AgendamentDTO {

    private LocalDate data;
    private LocalTime hora;
    private Long clienteId;
    private Long barbeiroId;
    private Long servicoId;
    private Long barbeariaId;
    private StatusAgendamento status;
}

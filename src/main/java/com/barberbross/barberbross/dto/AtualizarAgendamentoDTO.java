package com.barberbross.barberbross.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarAgendamentoDTO {

    private Long agendamentoId;

    @JsonFormat( pattern = "yyyy-MM-dd" )
    private LocalDate data;

    @JsonFormat( pattern = "HH:mm:ss" )
    private LocalTime hora;
    
    private Long barbeiroId;
    private Long servicoId;
}

package com.barberbross.barberbross.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.barberbross.barberbross.enums.StatusAgendamento;
import com.barberbross.barberbross.model.Agendamento;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AgendamentDTO {

    private Long agendamentoId;

    @JsonFormat( pattern = "yyyy-MM-dd" )
    private LocalDate data;

    @JsonFormat( pattern = "HH:mm:ss" )
    private LocalTime hora;
    
    private Long clienteId;
    private Long barbeiroId;
    private Long servicoId;
    private Long barbeariaId;
    private StatusAgendamento status;

    public static AgendamentDTO fromEntity( Agendamento agendamento ) {
        return AgendamentDTO.fromEntity( agendamento );
    }
}

package com.barberbross.barberbross.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.barberbross.barberbross.model.HorariosFuncionamento;

public class Horarios {

    public List<LocalTime> gerarHorarios( HorariosFuncionamento horarioFuncionamento ) {

        List<LocalTime> horarios = new ArrayList<>();
        LocalTime inicio = horarioFuncionamento.getHorarioInicio();
        LocalTime fim = horarioFuncionamento.getHorarioFim();

        while ( inicio.isBefore( fim.plusMinutes( 1 ) ) ) {
            horarios.add( inicio );
            inicio = inicio.plusMinutes( 30 );
        }

        return horarios;
    }
}

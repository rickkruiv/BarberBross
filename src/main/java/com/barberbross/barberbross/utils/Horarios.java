package com.barberbross.barberbross.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.barberbross.barberbross.model.Barbearia;


public class Horarios {

    public List<LocalTime> gerarHorarios( Barbearia barbearia ) {

        List<LocalTime> horarios = new ArrayList<>();
        LocalTime inicio = barbearia.getHoraInicio();
        LocalTime fim = barbearia.getHoraFim();

        while ( inicio.isBefore( fim.plusMinutes( 1 ) ) ) {
            horarios.add( inicio );
            inicio = inicio.plusMinutes( 30 );
        }

        return horarios;
    }
}

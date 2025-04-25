package com.barberbross.barberbross.utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Horarios {

    public List<LocalTime> gerarHorarios() {

        List<LocalTime> horarios = new ArrayList<>();
        LocalTime inicio = LocalTime.of( 10, 0 );
        LocalTime fim = LocalTime.of( 19, 0 );

        while ( inicio.isBefore( fim.plusMinutes( 1 ) ) ) {
            horarios.add( inicio );
            inicio.plusMinutes( 30 );
        }

        return horarios;
    }
}

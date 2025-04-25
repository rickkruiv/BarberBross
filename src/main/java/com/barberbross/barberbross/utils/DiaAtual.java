package com.barberbross.barberbross.utils;

import java.time.DayOfWeek;
import java.time.LocalDate;

import com.barberbross.barberbross.enums.DiaEnum;

public class DiaAtual {

    public static DiaEnum diaAtual() {
        DayOfWeek diaAtual = LocalDate.now().getDayOfWeek();

        switch ( diaAtual ) {
            case MONDAY: return DiaEnum.SEGUNDA;
            case TUESDAY: return DiaEnum.TERCA;
            case WEDNESDAY: return DiaEnum.QUARTA;
            case THURSDAY: return DiaEnum.QUINTA;
            case FRIDAY: return DiaEnum.SEXTA;
            case SATURDAY: return DiaEnum.SABADO;
            case SUNDAY: return DiaEnum.DOMINGO;
            default: throw new IllegalStateException( "Dia inválido: " + diaAtual );
        }
    }
}

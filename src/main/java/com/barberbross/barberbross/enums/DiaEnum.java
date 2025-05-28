package com.barberbross.barberbross.enums;

import java.time.DayOfWeek;

public enum DiaEnum {
    DOMINGO( 1 ),
    SEGUNDA( 2 ),
    TERCA( 3 ),
    QUARTA( 4 ),
    QUINTA( 5 ),
    SEXTA( 6 ),
    SABADO( 7 );

    private final int valor;

    DiaEnum( int valor ) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public static DiaEnum fromValor( int valor ) {

        for ( DiaEnum dia : DiaEnum.values() ) {
            if ( dia.getValor() == valor ) {
                return dia;
            }
        }

        throw new IllegalArgumentException( "Dia invalido: " + valor );
    }

    public static DiaEnum mapearDayOfWeek( DayOfWeek dayOfWeek ) {
        return switch (dayOfWeek) {
            case SUNDAY -> DiaEnum.DOMINGO;
            case MONDAY -> DiaEnum.SEGUNDA;
            case TUESDAY -> DiaEnum.TERCA;
            case WEDNESDAY -> DiaEnum.QUARTA;
            case THURSDAY -> DiaEnum.QUINTA;
            case FRIDAY -> DiaEnum.SEXTA;
            case SATURDAY -> DiaEnum.SABADO;
        };
    }
}

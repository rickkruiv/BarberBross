package com.barberbross.barberbross.enums;

import java.time.DayOfWeek;

public enum DiaEnum {
    SEGUNDA,
    TERCA,
    QUARTA,
    QUINTA,
    SEXTA,
    SABADO,
    DOMINGO;

    public static DiaEnum mapearDayOfWeek( DayOfWeek dayOfWeek ) {
        return switch (dayOfWeek) {
            case MONDAY -> DiaEnum.SEGUNDA;
            case TUESDAY -> DiaEnum.TERCA;
            case WEDNESDAY -> DiaEnum.QUARTA;
            case THURSDAY -> DiaEnum.QUINTA;
            case FRIDAY -> DiaEnum.SEXTA;
            case SATURDAY -> DiaEnum.SABADO;
            case SUNDAY -> DiaEnum.DOMINGO;
    };
}
}

package com.barberbross.barberbross.model;

import com.barberbross.barberbross.enums.DiaEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DiasFuncionamento {

    @Id @GeneratedValue
    private Long id;

    @Enumerated( EnumType.STRING )
    private DiaEnum diaInicio;

    @Enumerated( EnumType.STRING )
    private DiaEnum diaFim;
}

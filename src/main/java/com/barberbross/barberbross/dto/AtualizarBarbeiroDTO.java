package com.barberbross.barberbross.dto;

import java.util.List;

import com.barberbross.barberbross.enums.DiaEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarBarbeiroDTO {
    private String especialidade;
    private List<DiaEnum> diasDisponiveisValores;
    private List<HorarioDisponivelBarbeiroDTO> horariosExecao;
}

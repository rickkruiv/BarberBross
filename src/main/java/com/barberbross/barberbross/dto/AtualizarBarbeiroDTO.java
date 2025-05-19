package com.barberbross.barberbross.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AtualizarBarbeiroDTO {
    private String especialidade;
    private List<Long> diasDisponiveisIds; // IDs dos dias da semana
    private List<HorarioDisponivelBarbeiroDTO> horariosExecao;
}

package com.barberbross.barberbross.dto;

import java.time.LocalDate;

import com.barberbross.barberbross.model.Barbeiro;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BarbeiroDTO {

    private Long id;
    private String nome;
    private LocalDate dataCadastro;
    private String especialidade;
    private Long barbeariaId;

    public static BarbeiroDTO fromEntity( Barbeiro barbeiro ) {
        
        BarbeiroDTO dto = new BarbeiroDTO();
        
        dto.setId( barbeiro.getId() );
        dto.setNome( barbeiro.getNome() );
        dto.setDataCadastro( barbeiro.getDataCadastro() );
        dto.setBarbeariaId( barbeiro.getBarbearia().getId() );
        dto.setEspecialidade( barbeiro.getEspecialidade() );
        return dto;
    } 
}

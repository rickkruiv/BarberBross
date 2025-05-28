package com.barberbross.barberbross.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberbross.barberbross.dto.AtualizarBarbeiroDTO;
import com.barberbross.barberbross.dto.BarbeiroDTO;
import com.barberbross.barberbross.model.Barbearia;
import com.barberbross.barberbross.model.Barbeiro;
import com.barberbross.barberbross.model.HorarioDisponivelBarbeiro;
import com.barberbross.barberbross.repository.BarbeariaRepository;
import com.barberbross.barberbross.repository.BarbeiroRepository;

@Service
public class BarbeiroService {

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    public Barbeiro salvarBarbeiro( BarbeiroDTO dto ) {

        Barbeiro barbeiro = new Barbeiro();

        Barbearia barbearia = barbeariaRepository.findById( dto.getBarbeariaId() )
                                .orElseThrow( () -> new RuntimeException( "barbearia nao encontrada" ) );

        barbeiro.setEspecialidade( dto.getEspecialidade() == null ? "Especialidade nao cadastrada" : dto.getEspecialidade() );
        barbeiro.setNome( dto.getNome() );
        barbeiro.setBarbearia( barbearia );

        return barbeiroRepository.save( barbeiro );
    }

    public List<BarbeiroDTO> listarBarbeiros( Long barbeariaId ) {

        return barbeiroRepository
                .findByBarbeariaId( barbeariaId )
                .stream()
                .map( BarbeiroDTO::fromEntity ).toList();
    }

    public BarbeiroDTO verBarbeiro( Long barbeiroId ) {
        
        Barbeiro barbeiro = barbeiroRepository
                                .findById( barbeiroId )
                                .orElseThrow( () -> new RuntimeException( "Barbeiro nao encontrado" ) );

        return BarbeiroDTO.fromEntity( barbeiro );
    }

    public void atualizarBarbeiro( Long barbeiroId, AtualizarBarbeiroDTO dto ) {

        Barbeiro barbeiro = barbeiroRepository
                                .findById( barbeiroId )
                                .orElseThrow( () -> new RuntimeException( "Barbeiro nao encontrado" ) );

        barbeiro.setEspecialidade( dto.getEspecialidade() );

        if ( dto.getDiasDisponiveisValores() != null ) {
            barbeiro.setDiasNaoDisponiveis( dto.getDiasDisponiveisValores() );
        }

        if (dto.getHorariosExecao() != null) {
            List<HorarioDisponivelBarbeiro> horarios = dto.getHorariosExecao().stream().map(h -> {
                HorarioDisponivelBarbeiro horario = new HorarioDisponivelBarbeiro();
                horario.setHorario( LocalTime.parse( h.getHora() ) );
                horario.setData( LocalDate.parse( h.getData() ) );
                horario.setBarbeiro( barbeiro );
                return horario;
            }).collect( Collectors.toList() );

            barbeiro.setHorariosExecao( horarios );
        }

        barbeiroRepository.save( barbeiro );
    }

    public boolean deletarBarbeiro( Long id ) {

        if ( barbeiroRepository.existsById( id ) ) {
            barbeiroRepository.deleteById( id );
            return true;
        }
        return false;
    }
}

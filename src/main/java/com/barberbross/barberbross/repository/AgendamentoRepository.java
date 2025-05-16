package com.barberbross.barberbross.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberbross.barberbross.enums.StatusAgendamento;
import com.barberbross.barberbross.model.Agendamento;
import com.barberbross.barberbross.model.Barbeiro;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> {
    boolean existsByDataAndHoraAndBarbeiroAndStatus( LocalDate data, LocalTime hora, Barbeiro barbeiro, StatusAgendamento statusAgendamento ); 
    List<Agendamento> findByStatus( StatusAgendamento statusAgendamento );
    List<Agendamento> findByBarbeariaId( Long barbeariaId );
}

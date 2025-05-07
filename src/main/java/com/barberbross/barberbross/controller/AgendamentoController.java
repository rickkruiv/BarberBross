package com.barberbross.barberbross.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barberbross.barberbross.dto.AgendamentDTO;
import com.barberbross.barberbross.dto.AtualizarAgendamentoDTO;
import com.barberbross.barberbross.model.Agendamento;
import com.barberbross.barberbross.service.AgendamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( "/agendamentos" )
@CrossOrigin( origins = "*" )
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @GetMapping 
    public ResponseEntity<?> listarAgendamentos() {
        try {
            return ResponseEntity.ok( agendamentoService.listarAgendamentos() );
        } catch ( RuntimeException e ) {
            return ResponseEntity
                        .status( HttpStatus.BAD_REQUEST )
                        .body( e.getMessage() );
        }
    }

    @PostMapping
    public ResponseEntity<Agendamento> salvarAgendamento( @Valid @RequestBody AgendamentDTO dto ) {
        return ResponseEntity.ok( agendamentoService.salvarAgendamento( dto ) );
    }
    
    @PutMapping( "/{agendamentoId}" )
    public ResponseEntity<?> atualizarAgendamento( @PathVariable Long agendamentoId, @RequestBody AtualizarAgendamentoDTO dto ) {
        return ResponseEntity.ok( agendamentoService.atualizarAgendamento( agendamentoId, dto ) );
    }

    @DeleteMapping( "/{id}" )
    public ResponseEntity<Void> deletarAgendamento( @PathVariable Long id ) {
        return agendamentoService.deletarAgendamento( id ) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

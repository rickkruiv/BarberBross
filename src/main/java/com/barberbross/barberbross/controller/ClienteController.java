package com.barberbross.barberbross.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barberbross.barberbross.dto.AtualizarClienteDTO;
import com.barberbross.barberbross.service.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( "/cliente" )
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<?> buscarClientes( @RequestParam( required = false ) Long id ) {
     
        if ( id != null ) {
            return ResponseEntity.ok( clienteService.buscarCliente( id ) );    
       } else {
     
        try {
                return ResponseEntity.ok( clienteService.listarClientes() );
            } catch ( RuntimeException e ) {
     
                return ResponseEntity
                        .status( HttpStatus.BAD_REQUEST )
                        .body( e.getMessage() );
            }
       }
    }

    @GetMapping( "/agendamentos" )
    public ResponseEntity<?> listarAgendamentosCliente( @RequestParam Long clienteId ) {

        return ResponseEntity.ok( clienteService.listarAgendamentosCliente( clienteId ) );
    }

    @PutMapping
    public ResponseEntity<Void> atualizarCliente( @RequestParam Long id, @Valid @RequestBody AtualizarClienteDTO dto ) {

        clienteService.atualizarCliente( id, dto );
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarCliente( @RequestParam Long id ) {
        return clienteService.deletarCliente( id ) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

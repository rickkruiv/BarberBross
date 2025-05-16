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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barberbross.barberbross.dto.BarbeiroDTO;
import com.barberbross.barberbross.model.Barbeiro;
import com.barberbross.barberbross.service.BarbeiroService;

import jakarta.validation.Valid;

@RestController
@RequestMapping( "/barbeiro" )
@CrossOrigin( origins = "*" )
public class BarbeiroController {

    @Autowired
    private BarbeiroService barbeiroService;

    @GetMapping
    public ResponseEntity<?> listarBarbeiros( @RequestParam Long barbeariaId ) {
        
        try {
            return ResponseEntity.ok( barbeiroService.listarBarbeiros( barbeariaId ) );
        } catch ( RuntimeException e ) {
            return ResponseEntity
                    .status( HttpStatus.BAD_REQUEST )
                    .body( e.getMessage() );
        }
    }

    @GetMapping( "/{barbeiroId}" )
    public ResponseEntity<BarbeiroDTO> verBarbeiro( @PathVariable Long barbeiroId ) {
        return ResponseEntity.ok( barbeiroService.verBarbeiro( barbeiroId ) );
    }

    @PostMapping
    public ResponseEntity<Barbeiro> salvarBarbeiro( @Valid @RequestBody BarbeiroDTO barbeiroDTO ) {
        return ResponseEntity.ok( barbeiroService.salvarBarbeiro( barbeiroDTO ) );
    }

    @PutMapping( "/{id}" )
    public ResponseEntity<Void> atualizarBarbeiro(
            @PathVariable Long id,
            @RequestBody AtualizarBarbeiroDTO dto ) {
        
        barbeiroService.atualizarBarbeiro( id, dto );
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping( "/id" )
    public ResponseEntity<Void> deletarBarbeiro( @PathVariable Long id ) {
        return barbeiroService.deletarBarbeiro( id ) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

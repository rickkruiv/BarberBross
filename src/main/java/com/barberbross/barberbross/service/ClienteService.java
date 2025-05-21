package com.barberbross.barberbross.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barberbross.barberbross.dto.AgendamentDTO;
import com.barberbross.barberbross.dto.AtualizarClienteDTO;
import com.barberbross.barberbross.model.Cliente;
import com.barberbross.barberbross.repository.AgendamentoRepository;
import com.barberbross.barberbross.repository.ClienteRepository;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public void salvarCliente() {

    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente buscarCliente( Long id ) {
        return clienteRepository.findById( id ).orElseThrow( () -> new RuntimeException( "Cliente nao encontrado" ) );
    }

    public List<AgendamentDTO> listarAgendamentosCliente( Long id ) {

        return agendamentoRepository
                    .findByClienteId( id )
                    .stream()
                    .map( AgendamentDTO::fromEntity )
                    .collect( Collectors.toList() );
    }

    public void atualizarCliente( Long id, AtualizarClienteDTO dto ) {

        Cliente cliente = clienteRepository
                            .findById( id )
                            .orElseThrow( () -> new RuntimeException( "Cliente nao existe" ) );

        
        if ( dto.getNome() != null ) 
            cliente.setNome( dto.getNome() );
        if ( dto.getTelefone() != null )
            cliente.setTelefone( dto.getTelefone() );
        if ( dto.getEmail() != null ) 
            cliente.setEmail( dto.getEmail() );

        clienteRepository.save( cliente );
    }

    public boolean deletarCliente( Long id ) {

        if ( clienteRepository.existsById( id ) ) {
            clienteRepository.deleteById( id );
            return true;
        }
        return false;
    }
}

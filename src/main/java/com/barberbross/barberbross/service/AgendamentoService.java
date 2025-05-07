package com.barberbross.barberbross.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.barberbross.barberbross.dto.AgendamentDTO;
import com.barberbross.barberbross.dto.AtualizarAgendamentoDTO;
import com.barberbross.barberbross.enums.StatusAgendamento;
import com.barberbross.barberbross.model.Agendamento;
import com.barberbross.barberbross.model.Barbearia;
import com.barberbross.barberbross.model.Barbeiro;
import com.barberbross.barberbross.model.Cliente;
import com.barberbross.barberbross.model.Servico;
import com.barberbross.barberbross.repository.AgendamentoRepository;
import com.barberbross.barberbross.repository.BarbeariaRepository;
import com.barberbross.barberbross.repository.BarbeiroRepository;
import com.barberbross.barberbross.repository.ClienteRepository;
import com.barberbross.barberbross.repository.ServicoRepository;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private BarbeiroRepository barbeiroRepository;

    @Autowired
    private BarbeariaRepository barbeariaRepository;

    @Autowired
    private ServicoRepository servicoRepository;

    public List<AgendamentDTO> listarAgendamentos() {

        return agendamentoRepository.findAll().stream().map( agendamento -> {
            AgendamentDTO dto = new AgendamentDTO();
            dto.setAgendamentoId( agendamento.getId() );
            dto.setBarbeariaId( agendamento.getBarbearia().getId() );
            dto.setBarbeiroId( agendamento.getBarbeiro().getId() );
            dto.setClienteId( agendamento.getCliente().getId() );
            dto.setData( agendamento.getData() );
            dto.setHora( agendamento.getHora() );
            dto.setServicoId( agendamento.getServico().getId() );
            dto.setStatus( agendamento.getStatus() );
            return dto;
        } ).toList();
    }

    public AgendamentDTO verAgendamento( Long id ) {

        Agendamento agendamento = agendamentoRepository
            .findById( id )
            .orElseThrow( () -> new RuntimeException( "Agendamento nao encontrado" ) );
        
        AgendamentDTO dto = new AgendamentDTO();

        dto.setAgendamentoId( agendamento.getId() );
        dto.setBarbeariaId( agendamento.getBarbearia().getId() );
        dto.setBarbeiroId( agendamento.getBarbeiro().getId() );
        dto.setClienteId( agendamento.getCliente().getId() );
        dto.setServicoId( agendamento.getServico().getId() );
        dto.setData( agendamento.getData() );
        dto.setHora( agendamento.getHora() );
        dto.setStatus( agendamento.getStatus() );

        return dto;
    }

    public Agendamento salvarAgendamento( AgendamentDTO dto ) {

        Agendamento agendamento = new Agendamento();
        
        Cliente cliente = clienteRepository.findById( dto.getClienteId() )
                                .orElseThrow( () -> new RuntimeException( "Cliente nao encontrado" ) );

        Barbeiro barbeiro = barbeiroRepository.findById( dto.getBarbeiroId() )
                                .orElseThrow( () -> new RuntimeException( "Barbeiro nao encontrado" ) );

        Barbearia barbearia = barbeariaRepository.findById( dto.getBarbeariaId() )
                                .orElseThrow( () -> new RuntimeException( "barbearia nao encontrada" ) );
        
        Servico servico = servicoRepository.findById( dto.getServicoId() )
                                .orElseThrow( () -> new RuntimeException( "Servico nao encontrado" ) );

        agendamento.setData( dto.getData() );
        agendamento.setHora( dto.getHora() );

        agendamento.setCliente( cliente );
        agendamento.setBarbeiro( barbeiro );
        agendamento.setBarbearia( barbearia );
        agendamento.setServico( servico );
        
        agendamento.setStatus( StatusAgendamento.AGENDADO );

        validarAgendamento( agendamento );
                                
        return agendamentoRepository.save( agendamento );
    }

    @Scheduled( fixedRate = 60000 )
    public void atualizarStatusFinalizado() {

        List<Agendamento> agendamentos = agendamentoRepository.findByStatus( StatusAgendamento.AGENDADO );

        for ( Agendamento agendamento : agendamentos ) {
            
            LocalDateTime limite = LocalDateTime.now().minusMinutes( agendamento.getServico().getDuracao() );
            LocalDateTime dataHorarioAgendamento = LocalDateTime.of( 
                agendamento.getData(), agendamento. getHora() 
            );

            if ( dataHorarioAgendamento.isBefore( limite ) ) {
                agendamento.setStatus( StatusAgendamento.FINALIZADO );
                agendamentoRepository.save( agendamento );
            }
        }
    }

    public Agendamento atualizarAgendamento( Long agendamentoId, AtualizarAgendamentoDTO atualizarAgendamentoDTO ) {

        Agendamento agendamento = agendamentoRepository.findById( agendamentoId )
            .orElseThrow( () -> new RuntimeException( "Agendamento não encontrado" ) );

        Cliente cliente = agendamento.getCliente();

        Barbeiro barbeiro = atualizarAgendamentoDTO.getBarbeiroId() != null 
                ? barbeiroRepository.findById( atualizarAgendamentoDTO.getBarbeiroId() )
                    .orElseThrow( () -> new RuntimeException( "Barbeiro não encontrado" ) ) 
                : agendamento.getBarbeiro();

        Servico servico = atualizarAgendamentoDTO.getServicoId() != null
                ? servicoRepository.findById( atualizarAgendamentoDTO.getServicoId() )
                    .orElseThrow( () -> new RuntimeException( "Servico não encontrado" ) ) 
                : agendamento.getServico();

        Barbearia barbearia = agendamento.getBarbearia();

        agendamento.setData( atualizarAgendamentoDTO.getData() != null ? atualizarAgendamentoDTO.getData() : agendamento.getData() );
        agendamento.setHora( atualizarAgendamentoDTO.getHora() != null ? atualizarAgendamentoDTO.getHora() : agendamento.getHora() );
        agendamento.setServico( servico );
        agendamento.setCliente( cliente );
        agendamento.setBarbeiro( barbeiro );
        agendamento.setBarbearia( barbearia );

        validarAgendamento( agendamento );

        return agendamentoRepository.save( agendamento );
    }

    public boolean deletarAgendamento( Long id ) {

        if ( agendamentoRepository.existsById( id ) ) {
            agendamentoRepository.deleteById( id );
            return true;
        }

        return false;
    }

    private void validarAgendamento( Agendamento agendamento ) {

        if ( !agendamento.getBarbeiro().getBarbearia().getId().equals( agendamento.getBarbearia().getId() ) ) {
            throw new RuntimeException( "Barbeiro nao pertence a barbearia informada" );
        }

        if ( !agendamento.getServico().getBarbearia().getId().equals( agendamento.getBarbearia().getId() ) ) {
            throw new RuntimeException( "Servico nao pertence a barbearia informada" );
        }

        boolean existeConflito = agendamentoRepository.existsByDataAndHoraAndBarbeiroAndStatus(
            agendamento.getData(),
            agendamento.getHora(),
            agendamento.getBarbeiro(),
            StatusAgendamento.AGENDADO
        );
    
        if ( existeConflito && ( agendamento.getId() == null || !agendamentoRepository.findById( agendamento.getId() ).isPresent()) ) {
            throw new RuntimeException("Já existe um agendamento nesse horário para o barbeiro");
        }
    }
}

package com.barberbross.barberbross.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.barberbross.barberbross.dto.AgendamentDTO;
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

        /*List<Agendamento> agendamentos =  agendamentoRepository.findAll();
        List<AgendamentDTO> agendamentDTOs = new ArrayList<>();
        
        for ( Agendamento agendamento : agendamentos ) {
            
            AgendamentDTO agendamentDTO = new AgendamentDTO();

            agendamentDTO.setBarbeariaId( agendamento.getBarbearia().getId() );
            agendamentDTO.setBarbeiroId( agendamento.getBarbeiro().getId() );
            agendamentDTO.setClienteId( agendamento.getCliente().getId() );
            agendamentDTO.setData( agendamento.getData() );
            agendamentDTO.setHora( agendamento.getHora() );
            agendamentDTO.setServicoId( agendamento.getServico().getId() );
            
            agendamentDTOs.add( agendamentDTO );
        }
        return agendamentDTOs;
        */

        return agendamentoRepository.findAll().stream().map( agendamento -> {
            AgendamentDTO dto = new AgendamentDTO();
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
        

        if ( agendamentoRepository.existsByDataAndHoraAndBarbeiroAndStatus( dto.getData(), dto.getHora(), barbeiro, StatusAgendamento.AGENDADO ) ) {
            throw new RuntimeException( "Ja existe um agendamento" );
        }

        if ( !barbeiro.getBarbearia().getId().equals( barbearia.getId() ) ) {
            throw new RuntimeException( "Barbeiro nao pertence a barbearia informada" );
        }

        if ( !servico.getBarbearia().getId().equals( barbearia.getId() ) ) {
            throw new RuntimeException( "Servico nao pertence a barbearia informada" );
        }

        agendamento.setData( dto.getData() );
        agendamento.setHora( dto.getHora() );
        agendamento.setCliente( cliente );
        agendamento.setBarbeiro( barbeiro );
        agendamento.setBarbearia( barbearia );
        agendamento.setServico( servico );
        agendamento.setStatus( StatusAgendamento.AGENDADO );

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

    public boolean deletarAgendamento( Long id ) {

        if ( agendamentoRepository.existsById( id ) ) {
            agendamentoRepository.deleteById( id );
            return true;
        }

        return false;
    }
}

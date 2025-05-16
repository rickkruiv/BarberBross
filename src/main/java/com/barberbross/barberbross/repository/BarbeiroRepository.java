package com.barberbross.barberbross.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.barberbross.barberbross.model.Barbeiro;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    List<Barbeiro> findByBarbeariaId(Long barbeariaId);
}

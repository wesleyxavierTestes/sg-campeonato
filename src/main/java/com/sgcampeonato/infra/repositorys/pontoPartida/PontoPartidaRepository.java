package com.sgcampeonato.infra.repositorys.pontoPartida;

import java.util.UUID;

import com.sgcampeonato.core.entitys.pontoPartida.PontoPartida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PontoPartidaRepository extends JpaRepository<PontoPartida, UUID>{
    
}
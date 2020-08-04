package com.sgcampeonato.infra.repositorys.partidaCampeonato;

import java.util.UUID;

import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaCampeonatoRepository extends JpaRepository<PartidaCampeonato, UUID>{
    
}
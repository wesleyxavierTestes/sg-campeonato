package com.sgcampeonato.infra.repositorys.time;

import java.util.UUID;

import com.sgcampeonato.core.entitys.time.Time;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<Time, UUID>{
    
}
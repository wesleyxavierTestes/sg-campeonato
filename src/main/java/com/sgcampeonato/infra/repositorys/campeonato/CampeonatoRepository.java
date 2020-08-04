package com.sgcampeonato.infra.repositorys.campeonato;

import java.util.UUID;

import com.sgcampeonato.core.entitys.campeonato.Campeonato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, UUID> {

}
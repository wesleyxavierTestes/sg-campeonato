package com.sgcampeonato.infra.repositorys.partidaCampeonato;

import java.util.Collection;
import java.util.UUID;

import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PartidaCampeonatoRepository extends JpaRepository<PartidaCampeonato, UUID>{

    @Query(
        nativeQuery = true,
         value = "SELECT (p.*) FROM partida_campeonato p where p.campeonato_id = ?1 order by p.data_partida asc"
    )
	Collection<PartidaCampeonato> findPartidas(UUID uuid);
}
package com.sgcampeonato.core.services.campeonato;

import java.util.List;
import java.util.stream.Collectors;

import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.campeonato.CampeonatoRepository;
import com.sgcampeonato.infra.repositorys.partidaCampeonato.PartidaCampeonatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService extends BaseService<Campeonato> {


    @Autowired
    private PartidaCampeonatoRepository partidaRepository;

    @Autowired
    public CampeonatoService(CampeonatoRepository repository) {
        super(repository);
    }

    public void configurePartidas(Campeonato entity) {
        List<PartidaCampeonato> partidasCampeonato = this.partidaRepository.findPartidas(entity.getId()).stream()
                .map(c -> this.partidaRepository.findById(c.getId()).get()).collect(Collectors.toList());
        entity.setPartidasCampeonato(partidasCampeonato);
    }

    public Page<Time> times(Campeonato entity, int page) {
        configurePartidas(entity);
        Page<Time> findAllByIds = new PageImpl<>(entity.times(), PageRequest.of((page - 1), 10), entity.times().size());
        return findAllByIds;
    }
}
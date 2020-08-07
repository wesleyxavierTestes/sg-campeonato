package com.sgcampeonato.core.services.campeonato;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.sgcampeonato.application.dto.ColocacaoDto;
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
public class CampeonatoService extends BaseService<Campeonato, CampeonatoRepository> {

    @Autowired
    private PartidaCampeonatoRepository partidaRepository;

    @Autowired
    public CampeonatoService(CampeonatoRepository repository) {
        super(repository);
    }

    public List<PartidaCampeonato> buscaPartidasCampeonato(UUID id) {
        
        Collection<PartidaCampeonato> findPartidas = this.partidaRepository.findPartidas(id);

        Stream<PartidaCampeonato> partidasCampeonato = findPartidas.stream()
                .map(c -> this.partidaRepository.findById(c.getId()).get());

        return partidasCampeonato.collect(Collectors.toList());

    }

    /**
     * Busca no banco todas as partidas do campeonato, seta no campeonato
     * E lista todas os times dessas partidas;
     * @param entity
     * @param page
     * @return
     */
    public Page<Time> findAllTimesCampeonato(Campeonato entity, int page) {
        List<PartidaCampeonato> partidasCampeonato = buscaPartidasCampeonato(entity.getId());

        entity.setPartidasCampeonato(partidasCampeonato);

        List<Time> timesPorPartidasCampeonato = entity.buscaTimesPorPartidasCampeonato();

        Page<Time> findAllByIds = new PageImpl<>(timesPorPartidasCampeonato, PageRequest.of((page - 1), 10),
                timesPorPartidasCampeonato.size());

        return findAllByIds;
    }

	public List<ColocacaoDto> colocacaoTimes(Campeonato entity) {
		List<PartidaCampeonato> buscaPartidasCampeonato = this.buscaPartidasCampeonato(entity.getId());
        
        return entity.colocacaoTimes(buscaPartidasCampeonato);
	}
}
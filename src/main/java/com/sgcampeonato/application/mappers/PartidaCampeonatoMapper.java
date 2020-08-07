package com.sgcampeonato.application.mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sgcampeonato.application.dto.CampeonatoDto;
import com.sgcampeonato.application.dto.PartidaCampeonatoDto;
import com.sgcampeonato.application.dto.TimeDto;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.entitys.time.Time;

public class PartidaCampeonatoMapper {

    public static PartidaCampeonatoDto to(PartidaCampeonato model) {
        PartidaCampeonatoDto map = new PartidaCampeonatoDto();

        map.id = model.getId().toString();
        map.dataPartida = model.getDataPartida().toString();

        map.vencedor = model.getVencedor();
        map.situacao = model.getSituacao();

        map.campeonato = ModelMapperBase.get().map(model.getCampeonato(), CampeonatoDto.class);

        map.timeA = ModelMapperBase.get().map(model.getTimeA(), TimeDto.class);

        map.timeB = ModelMapperBase.get().map(model.getTimeB(), TimeDto.class);

        return map;
    }

    public static PartidaCampeonato to(PartidaCampeonatoDto model) {
        PartidaCampeonato map = new PartidaCampeonato();
        if (model.id == null)
            model.id = UUID.randomUUID().toString();

        map.setId(UUID.fromString(model.id));

        map.setDataPartida(LocalDateTime.parse(model.dataPartida));

        map.setVencedor(model.vencedor);
        map.setSituacao(model.situacao);

        map.setGolsA(model.golsA);
        map.setGolsB(model.golsB);

        if (model.campeonato.id == null)
            model.campeonato.id = UUID.randomUUID().toString();
            
        map.setCampeonato(ModelMapperBase.get().map(model.campeonato, Campeonato.class));
        map.getCampeonato().setId(UUID.fromString(model.campeonato.id));

        map.setTimeA(ModelMapperBase.get().map(model.timeA, Time.class));
        map.getTimeA().setId(UUID.fromString(model.timeA.id));

        map.setTimeB(ModelMapperBase.get().map(model.timeB, Time.class));
        map.getTimeB().setId(UUID.fromString(model.timeB.id));

        return map;
    }

}
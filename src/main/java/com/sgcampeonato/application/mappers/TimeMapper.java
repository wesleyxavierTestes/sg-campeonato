package com.sgcampeonato.application.mappers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.sgcampeonato.application.dto.CampeonatoDto;
import com.sgcampeonato.application.dto.PartidaCampeonatoDto;
import com.sgcampeonato.application.dto.TimeDto;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.entitys.time.Time;

public class TimeMapper {

    public static TimeDto to(Time model) {

        List<PartidaCampeonatoDto> partidas = new ArrayList<>();
        for (PartidaCampeonato partidaCampeonato : model.getPartidas()) {
            partidaCampeonato.setCampeonato(null);
            partidaCampeonato.setTimeA(null);
            partidaCampeonato.setTimeB(null);
            partidas.add(ModelMapperBase.get().map(partidaCampeonato, PartidaCampeonatoDto.class));
        }

        List<CampeonatoDto> campeonatos = new ArrayList<>();
        for (Campeonato campeonato : model.getCampeonatos()) {
            campeonato.setPartidasCampeonato(null);
            campeonatos.add(ModelMapperBase.get().map(campeonato, CampeonatoDto.class));
        }

        TimeDto map = ModelMapperBase.get().map(model, TimeDto.class);

        map.partidas = partidas;
        map.campeonatos = campeonatos;

        return map;
    }

    public static Time to(TimeDto model) {
        List<PartidaCampeonato> partidas = new ArrayList<>();
        if (Objects.nonNull(model.partidas))
            for (PartidaCampeonatoDto partidaCampeonato : model.partidas) {
                partidaCampeonato.campeonato = (null);
                partidaCampeonato.timeA = (null);
                partidaCampeonato.timeB = (null);
                if (partidaCampeonato.id == null)
                    partidaCampeonato.id = UUID.randomUUID().toString();
                partidas.add(ModelMapperBase.get().map(partidaCampeonato, PartidaCampeonato.class));
            }

        List<Campeonato> campeonatos = new ArrayList<>();
        if (Objects.nonNull(model.campeonatos))
            for (CampeonatoDto campeonato : model.campeonatos) {
                if (campeonato.id == null)
                    campeonato.id = UUID.randomUUID().toString();
                campeonatos.add(ModelMapperBase.get().map(campeonato, Campeonato.class));
            }

        if (model.id == null)
            model.id = UUID.randomUUID().toString();
        Time map = ModelMapperBase.get().map(model, Time.class);

        map.setPartidas(partidas);
        map.setCampeonatos(campeonatos);

        return map;
    }

}

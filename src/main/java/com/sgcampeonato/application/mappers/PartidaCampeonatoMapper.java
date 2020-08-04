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

        map.campeonato = ModelMapperBase.get()
        .map(model.getCampeonato(), CampeonatoDto.class);

        map.timeA = ModelMapperBase.get()
        .map(model.getTimeA(), TimeDto.class);

        map.timeB = ModelMapperBase.get()
        .map(model.getTimeB(), TimeDto.class);

        return map;
    }

    public static PartidaCampeonato to(PartidaCampeonatoDto model) {
        PartidaCampeonato map = new PartidaCampeonato();
        map.setId(UUID.fromString(model.id));
        
        map.setDataPartida(LocalDateTime.parse(model.dataPartida));
    
        map.setVencedor(model.vencedor);
        map.setSituacao(model.situacao);

        map.setCampeonato(ModelMapperBase.get()
        .map(model.campeonato, Campeonato.class));

        map.setTimeA(ModelMapperBase.get()
        .map(model.timeA, Time.class));

        map.setTimeB(ModelMapperBase.get()
        .map(model.timeB, Time.class));

        return map; 
    }
    
}
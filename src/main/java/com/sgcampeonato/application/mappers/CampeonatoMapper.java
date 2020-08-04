package com.sgcampeonato.application.mappers;

import java.time.LocalDateTime;
import java.util.UUID;

import com.sgcampeonato.application.dto.CampeonatoDto;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;

public class CampeonatoMapper {

    public static CampeonatoDto to(Campeonato model) {
        CampeonatoDto map = new CampeonatoDto();
        map.id = model.getId().toString();
        map.dateInit = model.getDateInit().toString();
        map.dateEnd = model.getDateEnd().toString();
        map.name = model.getName().toString();
        return map;
    }

    public static Campeonato to(CampeonatoDto dto) {
        Campeonato map = new Campeonato();
        map.setId(UUID.fromString(dto.id));
        map.setDateInit(LocalDateTime.parse(dto.dateInit));
        map.setDateEnd(LocalDateTime.parse(dto.dateEnd));
        map.setName(dto.name);
        return map; 
    }
    
}
package com.sgcampeonato.application.dto;

import java.util.List;

public class TimeDto  extends BaseEntityDto {
    public String name;
    public List<CampeonatoDto> campeonatos;
    public List<PartidaCampeonatoDto> partidas;
}
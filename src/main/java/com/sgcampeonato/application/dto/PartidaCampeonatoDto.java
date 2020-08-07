package com.sgcampeonato.application.dto;

import com.sgcampeonato.core.enuns.EnumSituacao;
import com.sgcampeonato.core.enuns.EnumVencedor;

public class PartidaCampeonatoDto  extends BaseEntityDto {
    
    public CampeonatoDto campeonato;
    public String dataPartida;
    public int golsA;
    public int golsB;
    public TimeDto timeA;
    public TimeDto timeB;
    public EnumVencedor vencedor;
    public EnumSituacao situacao;
}
package com.sgcampeonato.application.dto;

import lombok.Builder;

@Builder
public class ColocacaoDto {

    public String nome;
    public int colocacao;
    public int pontuacao;
    public int gols;
}
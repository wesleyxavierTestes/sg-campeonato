package com.sgcampeonato.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Builder;

@Builder
public class ColocacaoDto {
    @JsonIgnore
    public static int colocacaoAtual;
    
    public String nome;
    public int colocacao;
    public int pontuacao;
    public int gols;
	public static void zerarColocacaoAtual() {
        colocacaoAtual = 0;   
    }
    

    public static int getColocacaoAtual() {
        colocacaoAtual += 1;
        return colocacaoAtual;
    }
}
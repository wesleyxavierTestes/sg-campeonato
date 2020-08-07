package com.sgcampeonato.core.entitys.time;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.sgcampeonato.core.entitys.BaseEntity;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Time extends BaseEntity {

    public static int compareByNameThenAge(Time lhs, Time rhs) {
        int pontosA = lhs.getPontos();
        int pontosB = rhs.getPontos();
        return pontosA - pontosB;
    }

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Campeonato> campeonatos;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PartidaCampeonato> partidas;

    /**
     * Get Partidas custom para evitar retornar nullo
     * @return
     */
    public List<PartidaCampeonato> getPartidas() {
        return (!Objects.nonNull(partidas)) ? new ArrayList<>() : partidas;
    }

    /**
     * Faz a contagem de pontos bom base na lista de partidas do time
     * 
     * @return
     */
    public int getPontos() {
        int ponto = 0;

        for (PartidaCampeonato partida : this.getPartidas())
            ponto += partida.getPontoPorTime(this);

        return ponto;
    }

    /**
     * Faz a contagem do saldo de gols bom base na lista de partidas do time
     * 
     * @return
     */
    public int getSaldoGols() {
        int gols = 0;

        for (PartidaCampeonato partida : this.getPartidas())
            gols += partida.getSaldoGolsPorTime(this);

        return gols;
    }

    /**
     * Faz a contagem de gols efetuados bom base na lista de partidas do time
     * 
     * @return
     */
    public int getGolsEfetuado() {
        int gols = 0;

        for (PartidaCampeonato partida : this.getPartidas())
            gols += partida.getGolsEfetuadoPorTime(this);

        return gols;
    }

    /**
     * Faz a contagem de gols recebidos bom base na lista de partidas do time
     * 
     * @return
     */
    public int getGolsRecebido() {
        int gols = 0;

        for (PartidaCampeonato partida : this.getPartidas())
            gols += partida.getGolsRecebidoPorTime(this);

        return gols;
    }
}
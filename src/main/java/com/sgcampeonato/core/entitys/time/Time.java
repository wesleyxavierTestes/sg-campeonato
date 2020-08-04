package com.sgcampeonato.core.entitys.time;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

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

    @Column(nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Campeonato> campeonatos;

    @OneToMany(fetch = FetchType.LAZY)
    private List<PartidaCampeonato> partidas;

    public int pontos() {
        int ponto = 0;
        
        for (PartidaCampeonato partida : partidas)
            ponto += partida.ponto(this);

        return ponto;
    }
}
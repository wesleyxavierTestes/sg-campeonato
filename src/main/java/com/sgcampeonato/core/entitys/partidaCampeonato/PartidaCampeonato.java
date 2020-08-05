package com.sgcampeonato.core.entitys.partidaCampeonato;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.sgcampeonato.core.entitys.BaseEntity;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.enuns.EnumSituacao;
import com.sgcampeonato.core.enuns.EnumVencedor;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PartidaCampeonato extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    private Campeonato campeonato;

    @Column(nullable = false)
    private LocalDateTime dataPartida;

    @Column(nullable = false)
    private int golsA;

    @Column(nullable = false)
    private int golsB;

    @ManyToOne(fetch = FetchType.EAGER)
    private Time timeA;

    @ManyToOne(fetch = FetchType.EAGER)
    private Time timeB;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private EnumVencedor vencedor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnumSituacao situacao;

    public int ponto(Time time) {
        if (situacao == EnumSituacao.Finalizada) {
            if (this.vencedor.equals(EnumVencedor.Empate))
                return 1;
            if (validarVitoriaTimeA(time))
                return 3;
            if (validarVitoriaTimeB(time))
                return 3;
        }
        return 0;
    }

    public int gols(Time time) {
        if (isTimeA(time))
            return this.golsA;
        else if (isTimeB(time))
            return this.golsB;
        else
            return 0;
    }

    private boolean validarVitoriaTimeB(Time time) {
        return this.vencedor.equals(EnumVencedor.TimeB) && isTimeB(time);
    }

    private boolean isTimeB(Time time) {
        return time.getId() == (this.timeB.getId());
    }

    private boolean validarVitoriaTimeA(Time time) {
        return this.vencedor.equals(EnumVencedor.TimeA) && isTimeA(time);
    }

    private boolean isTimeA(Time time) {
        return time.getId() == (this.timeA.getId());
    }
}
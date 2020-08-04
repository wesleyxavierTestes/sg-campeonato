package com.sgcampeonato.core.entitys.partidaCampeonato;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
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

    @JsonSetter("dataPartida")
    public void setJsonDataPartida(String data) {
        this.dataPartida = LocalDateTime.parse(data);
    }

    @JsonGetter("dataPartida")
    public String getJsonDataPartida(String data) {
        return this.dataPartida.toString();
    }

    @ManyToOne(fetch = FetchType.EAGER)
    private Time timeA;

    @ManyToOne(fetch = FetchType.EAGER)
    private Time timeB;

    @Enumerated(EnumType.STRING)
    private EnumVencedor vencedor;

    @Enumerated(EnumType.STRING)
    private EnumSituacao situacao;

    public int ponto(Time time) {
        if (this.vencedor.equals(EnumVencedor.Empate)) {
            return 1;
        } else if (validarTimeA(time)) {
            return 3;
        } else if (validarTimeB(time)) {
            return 3;
        } else {
            return 0;
        }
    }

    private boolean validarTimeB(Time time) {
        return this.vencedor.equals(EnumVencedor.TimeB) && time.getId() == (this.timeB.getId());
    }

    private boolean validarTimeA(Time time) {
        return this.vencedor.equals(EnumVencedor.TimeA) && time.getId() == (this.timeA.getId());
    }
}
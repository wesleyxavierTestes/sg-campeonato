package com.sgcampeonato.core.entitys.campeonato;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.sgcampeonato.application.dto.ColocacaoDto;
import com.sgcampeonato.core.entitys.BaseEntity;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.entitys.time.Time;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campeonato extends BaseEntity {
    private static int colocacao = 0;
    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateInit;

    @Column(nullable = false)
    private LocalDateTime dateEnd;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<PartidaCampeonato> partidasCampeonato;

    public boolean isFinished() {
        return LocalDateTime.now().isAfter(dateEnd);
    }

    public List<ColocacaoDto> colocacaoTimes() {
        if (!Objects.nonNull(this.partidasCampeonato) || this.partidasCampeonato.isEmpty())
            return new ArrayList<>();
        colocacao = 0;
        return times().stream().sorted(Comparator.comparing(Time::pontos).thenComparing(Time::gols)).map(c -> c)
                .map(model -> {
                    colocacao = colocacao + 1;
                    model.setPartidas(this.partidasCampeonato);
                    return ColocacaoDto.builder().gols(model.gols()).pontuacao(model.pontos()).colocacao(colocacao)
                            .nome(model.getName()).build();
                }).collect(Collectors.toList());
    }

    public List<Time> times() {
        List<Time> times = new ArrayList<>();

        times.addAll(this.partidasCampeonato.stream().map(PartidaCampeonato::getTimeA).collect(Collectors.toList()));

        times.addAll(this.partidasCampeonato.stream().map(PartidaCampeonato::getTimeA).collect(Collectors.toList()));

        return times;
    }
}
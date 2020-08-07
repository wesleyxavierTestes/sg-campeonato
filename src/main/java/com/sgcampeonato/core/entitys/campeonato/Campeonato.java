package com.sgcampeonato.core.entitys.campeonato;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.sgcampeonato.application.dto.ColocacaoDto;
import com.sgcampeonato.application.exceptions.RegraBaseException;
import com.sgcampeonato.core.entitys.BaseEntity;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.utils.CustomFunction;

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

    /**
     * Seta lista de partidas e busca a colocação dos times do campeonato
     * 
     * @param partidasCampeonato
     * @return List<ColocacaoDto>
     */
    public List<ColocacaoDto> colocacaoTimes(List<PartidaCampeonato> partidasCampeonato) {

        this.setPartidasCampeonato(partidasCampeonato);

        if (!Objects.nonNull(this.partidasCampeonato) || this.partidasCampeonato.isEmpty())
            return new ArrayList<>();

        List<Time> timesPorPartidasCampeonato = buscaTimesPorPartidasCampeonato();

        List<ColocacaoDto> listaColocacaoTimes = listaColocacaoTimes(timesPorPartidasCampeonato);

        return listaColocacaoTimes;
    }

    /**
     * lista a colocação dos times do campeonato com base na lista de times
     * 
     * @param listaTimes
     * @return
     */
    public List<ColocacaoDto> listaColocacaoTimes(List<Time> listaTimes) {
        ColocacaoDto.zerarColocacaoAtual();

        Stream<ColocacaoDto> colocacaoComparandoPontosGols = geraColocacaoComparandoPontosGols(listaTimes)
                .map(time -> colocacaoComparandoPontosGolsMap(time));

        return colocacaoComparandoPontosGols.collect(Collectors.toList());
    }

    private ColocacaoDto colocacaoComparandoPontosGolsMap(Time time) {
        time.setPartidas(partidasCampeonato);

        ColocacaoDto colocacaoDto = colocacaoMapeadaPorTime(time);

        return colocacaoDto;
    }

    public static ColocacaoDto colocacaoMapeadaPorTime(Time time) {
        return ColocacaoDto.builder().gols(time.getSaldoGols()).pontuacao(time.getPontos())
                .colocacao(ColocacaoDto.getColocacaoAtual()).nome(time.getName()).build();
    }

    private Stream<Time> geraColocacaoComparandoPontosGols(List<Time> listaTimes) {
        return listaTimes.stream().sorted(Time::compareByNameThenAge)
                .sorted(Comparator.comparing(Time::getPontos).thenComparing(Time::getSaldoGols)).map(c -> c);
    }

    public List<Time> buscaTimesPorPartidasCampeonato() {
        Stream<Time> timeA = this.partidasCampeonato.stream().map(timeAPorPartidasCampeonatoMap());

        Stream<Time> timeB = this.partidasCampeonato.stream().map(timeBPorPartidasCampeonatoMap());

        return removeDuplicacaoTimes(Stream.concat(timeA, timeB));
    }

    /**
     * Todo time contido em uma partida deve retornar uma lista das partidas qual participa
     * @return
     */
    private Function<? super PartidaCampeonato, ? extends Time> timeAPorPartidasCampeonatoMap() {
        return partida -> {
            Time time = partida.getTimeA();
            List<PartidaCampeonato> partidas = time.getPartidas();
            if (Objects.nonNull(partidas) && !partidas.isEmpty())
                return time;
            throw new RegraBaseException("Configuração de partida inválida");
        };
    }

    /**
     * Todo time contido em uma partida deve retornar uma lista das partidas qual participa
     * @return
     */
    private Function<? super PartidaCampeonato, ? extends Time> timeBPorPartidasCampeonatoMap() {
        return partida -> {
            Time time = partida.getTimeB();
            List<PartidaCampeonato> partidas = time.getPartidas();
            if (Objects.nonNull(partidas) && !partidas.isEmpty())
                return time;
            throw new RegraBaseException("Configuração de partida inválida");
        };
    }

    /**
     * Remove ids duplicados
     * @param timesConcat
     * @return
     */
    public List<Time> removeDuplicacaoTimes(Stream<Time> timesConcat) {
        Stream<Time> times = timesConcat.filter(CustomFunction.distinctByKey(p -> p.getId()));

        return times.collect(Collectors.toList());
    }
}
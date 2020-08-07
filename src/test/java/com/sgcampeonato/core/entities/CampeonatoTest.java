package com.sgcampeonato.core.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicTest.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

import com.sgcampeonato.application.dto.ColocacaoDto;
import com.sgcampeonato.application.exceptions.RegraBaseException;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.enuns.EnumSituacao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CampeonatoTest {

    private Campeonato campeonato;

    @BeforeEach
    public void setup() {
        campeonato = new Campeonato();
    }

    @Test
    @DisplayName("Valida se foi passado um lista de times extraidos de uma lista de Partidas que estão sem partidas")
    public void colocacaoTimesTest() throws RegraBaseException {
        List<PartidaCampeonato> partidasCampeonato = new ArrayList<>();

        for (int y = 0; y < 6; y++) {
            PartidaCampeonato partida = new PartidaCampeonato();
            partida.setId(UUID.randomUUID());

            List<Time> times = new ArrayList<>();

            Time timeA = new Time();
            timeA.setId(UUID.randomUUID());
            times.add(timeA);
            partida.setTimeA(timeA);

            Time timeB = new Time();
            timeB.setId(UUID.randomUUID());
            times.add(timeB);
            partida.setTimeB(timeB);

            partida.setSituacao(EnumSituacao.Aguardando);

            partidasCampeonato.add(partida);
        }
        RegraBaseException ex = assertThrows(RegraBaseException.class, () -> {
            campeonato.colocacaoTimes(partidasCampeonato);
        });
        assertEquals("Configuração de partida inválida", ex.getMessage());
    }

    @Test
    @DisplayName("Busca Times por Partidas")
    public void buscaTimesPorPartidasCampeonatoTest() {

        List<PartidaCampeonato> partidasCampeonato = new ArrayList<>();

        for (int y = 0; y < 6; y++) {
            PartidaCampeonato partida = new PartidaCampeonato();
            partida.setId(UUID.randomUUID());

            List<Time> times = new ArrayList<>();

            Time timeA = new Time();
            timeA.setId(UUID.randomUUID());
            times.add(timeA);
            timeA.setPartidas(new ArrayList<PartidaCampeonato>() {{ add(partida);}});

            Time timeB = new Time();
            timeB.setId(UUID.randomUUID());
            times.add(timeB);           
            timeB.setPartidas(new ArrayList<PartidaCampeonato>() {{ add(partida);}});

            partida.setTimeA(timeA);
            partida.setTimeB(timeB);

            partidasCampeonato.add(partida);
        }
        campeonato.setPartidasCampeonato(partidasCampeonato);

        List<Time> lista = campeonato.buscaTimesPorPartidasCampeonato();

        assertEquals(12, lista.size());
    }

    @Test
    @DisplayName("Removerá ids duplicados")
    public void removeDuplicacaoTimesTest() {
        List<Time> times = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Time time = new Time();
            time.setId(UUID.randomUUID());

            times.add(time);
            times.add(time);
        }

        Stream<Time> stream = times.stream();
        times = campeonato.removeDuplicacaoTimes(stream);

        assertNotNull(times);
        assertEquals(10, times.size());
    }
}
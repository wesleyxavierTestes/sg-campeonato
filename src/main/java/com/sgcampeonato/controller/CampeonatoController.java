package com.sgcampeonato.controller;

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;

import com.sgcampeonato.application.dto.CampeonatoDto;
import com.sgcampeonato.application.dto.ColocacaoDto;
import com.sgcampeonato.application.dto.TimeDto;
import com.sgcampeonato.application.mappers.CampeonatoMapper;
import com.sgcampeonato.application.mappers.TimeMapper;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.services.campeonato.CampeonatoService;
import com.sgcampeonato.utils.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campeonato")
@Validated
public class CampeonatoController extends BaseController {

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping("novo")
    public ResponseEntity<Campeonato> novo() {
        return ResponseEntity.ok(new Campeonato());
    }

    @GetMapping("list")
    public ResponseEntity<Page<CampeonatoDto>> list(@RequestParam(name = "page") int page) {

        Page<CampeonatoDto> list = campeonatoService.list(page)
        .map(model -> CampeonatoMapper.to(model));

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<CampeonatoDto> find(@RequestParam(name = "id") @NotNull String id) {

        Campeonato entity = campeonatoService.find(UUID.fromString(id));

        return ResponseEntity.ok(CampeonatoMapper.to(entity));
    }

    /**
     * lista os times de um campeonato específico
     * @param page
     * @param id
     * @return
     */
    @GetMapping("list/times")
    public ResponseEntity<Page<TimeDto>> findTimes(
        @RequestParam(name = "page") int page, @RequestParam(name = "id") @NotNull String id) {

        Campeonato entity = campeonatoService.find(UUID.fromString(id));
        
        Page<Time> times = campeonatoService.findAllTimesCampeonato(entity, page);

        Page<TimeDto> timesDto = times.map(model -> TimeMapper.to(model));

        return ResponseEntity.ok(timesDto);
    }

    /**
     * lista a colocação dos times de um campeonato específico
     * @param page
     * @param id
     * @return
     */
    @GetMapping("list/times/colocacao")
    public ResponseEntity<List<ColocacaoDto>> findTimesColocacao(@RequestParam(name = "id") @NotNull String id) {

        Campeonato entity = campeonatoService.find(UUID.fromString(id));
        
        List<ColocacaoDto> times = campeonatoService.colocacaoTimes(entity);

        return ResponseEntity.ok(times);
    }

    @PostMapping("save")
    public ResponseEntity<CampeonatoDto> save(@RequestBody CampeonatoDto entityDto) {

        Campeonato entity = CampeonatoMapper.to(entityDto);
        
        campeonatoService.save(entity);

        return ResponseEntity.ok(CampeonatoMapper.to(entity));
    }

    @PutMapping("update")
    public ResponseEntity<CampeonatoDto> update(@RequestBody CampeonatoDto entityDto) {

        Campeonato entity = CampeonatoMapper.to(entityDto);
        
        campeonatoService.update(entity);

        return ResponseEntity.ok(CampeonatoMapper.to(entity));
    }

    @PutMapping("delete")
    public ResponseEntity<CampeonatoDto> delete(@RequestParam(name = "id") String id) {

        Campeonato entity = campeonatoService.delete(UUID.fromString(id));

        return ResponseEntity.ok(CampeonatoMapper.to(entity));
    }
}
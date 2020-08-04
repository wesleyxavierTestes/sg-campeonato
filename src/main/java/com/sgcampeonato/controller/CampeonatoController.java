package com.sgcampeonato.controller;

import java.util.UUID;

import com.sgcampeonato.application.dto.CampeonatoDto;
import com.sgcampeonato.application.mappers.CampeonatoMapper;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.services.campeonato.CampeonatoService;
import com.sgcampeonato.utils.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/campeonato")
public class CampeonatoController extends BaseController {

    @Autowired
    private CampeonatoService campeonatoService;

    @GetMapping("novo")
    public ResponseEntity<Campeonato> novo() {
        return ResponseEntity.ok(new Campeonato());
    }

    @GetMapping("list")
    public ResponseEntity<Page<CampeonatoDto>> list(@RequestParam(name = "page") int page) {

        Page<CampeonatoDto> list = campeonatoService.list(page).map(model -> CampeonatoMapper.to(model));

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<CampeonatoDto> find(@RequestParam(name = "id") String id) {

        Campeonato find = campeonatoService.find(UUID.fromString(id));
        CampeonatoDto findDto = CampeonatoMapper.to(find);

        return ResponseEntity.ok(findDto);
    }

    @PostMapping("save")
    public ResponseEntity<CampeonatoDto> save(@RequestBody CampeonatoDto entity) {

        Campeonato map = CampeonatoMapper.to(entity);
        Campeonato save = campeonatoService.save(map);
        CampeonatoDto find = CampeonatoMapper.to(save);

        return ResponseEntity.ok(find);
    }

    @PutMapping("update")
    public ResponseEntity<CampeonatoDto> update(@RequestBody CampeonatoDto entity) {

        Campeonato map = CampeonatoMapper.to(entity);
        Campeonato update = campeonatoService.update(map);
        CampeonatoDto find = CampeonatoMapper.to(update);

        return ResponseEntity.ok(find);
    }

    @PutMapping("delete")
    public ResponseEntity<CampeonatoDto> delete(@RequestParam(name = "id") String id) {

        CampeonatoDto find = CampeonatoMapper.to(campeonatoService.delete(UUID.fromString(id)));

        return ResponseEntity.ok(find);
    }
}
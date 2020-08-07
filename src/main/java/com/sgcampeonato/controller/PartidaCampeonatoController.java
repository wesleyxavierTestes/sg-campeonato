package com.sgcampeonato.controller;

import java.util.UUID;

import com.sgcampeonato.application.dto.PartidaCampeonatoDto;
import com.sgcampeonato.application.mappers.PartidaCampeonatoMapper;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.services.partidaCampeonato.PartidaCampeonatoService;
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
@RequestMapping("/api/partidacampeonato")
public class PartidaCampeonatoController extends BaseController {
    
    @Autowired
    private PartidaCampeonatoService partidaCampeonatoService;

    @GetMapping("novo")
    public ResponseEntity<PartidaCampeonato> novo() {
        return ResponseEntity.ok(new PartidaCampeonato());
    }

    @GetMapping("list")
    public ResponseEntity<Page<PartidaCampeonatoDto>> list(@RequestParam(name = "page") int page) {

        Page<PartidaCampeonatoDto> list = partidaCampeonatoService.list(page)
        .map(model -> PartidaCampeonatoMapper.to(model));

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<PartidaCampeonatoDto> find(@RequestParam(name = "id") String id) {

        PartidaCampeonato entity = partidaCampeonatoService.find(UUID.fromString(id));

        return ResponseEntity.ok(PartidaCampeonatoMapper.to(entity));
    }

    @PostMapping("save")
    public ResponseEntity<PartidaCampeonatoDto> save(@RequestBody PartidaCampeonatoDto entityDto) {

        PartidaCampeonato entity = PartidaCampeonatoMapper.to(entityDto);
        
        partidaCampeonatoService.save(entity);

        return ResponseEntity.ok(PartidaCampeonatoMapper.to(entity));
    }

    @PutMapping("update")
    public ResponseEntity<PartidaCampeonatoDto> update(@RequestBody PartidaCampeonatoDto entityDto) {

        PartidaCampeonato entity = PartidaCampeonatoMapper.to(entityDto);
        
        partidaCampeonatoService.update(entity);

        return ResponseEntity.ok(PartidaCampeonatoMapper.to(entity));
    }


    @PutMapping("delete")
    public ResponseEntity<PartidaCampeonatoDto> delete(@RequestParam(name = "id") String id) {

        PartidaCampeonato entity = partidaCampeonatoService.delete(UUID.fromString(id));
        
        return ResponseEntity.ok(PartidaCampeonatoMapper.to(entity));
    }
}
package com.sgcampeonato.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.sgcampeonato.application.dto.PartidaCampeonatoDto;
import com.sgcampeonato.application.mappers.PartidaCampeonatoMapper;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.enuns.EnumSituacao;
import com.sgcampeonato.core.enuns.EnumVencedor;
import com.sgcampeonato.core.services.partidaCampeonato.PartidaCampeonatoService;
import com.sgcampeonato.utils.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

        PartidaCampeonato find = partidaCampeonatoService.find(UUID.fromString(id));
        PartidaCampeonatoDto findDto = PartidaCampeonatoMapper.to(find);

        return ResponseEntity.ok(findDto);
    }

    @PostMapping("save")
    public ResponseEntity<PartidaCampeonatoDto> save(@RequestBody PartidaCampeonatoDto entity) {

        PartidaCampeonato map = PartidaCampeonatoMapper.to(entity);
        PartidaCampeonato save = partidaCampeonatoService.save(map);
        PartidaCampeonatoDto find = PartidaCampeonatoMapper.to(save);

        return ResponseEntity.ok(find);
    }

    @PutMapping("update")
    public ResponseEntity<PartidaCampeonatoDto> updateSituacao(
        @RequestParam(name = "id") String id, 
        @RequestParam(name = "situacao") EnumSituacao situacao,
        @RequestParam(name = "vencedor") EnumVencedor vencedor) {

        PartidaCampeonato find = partidaCampeonatoService.updateSituacao(UUID.fromString(id), situacao, vencedor);
        
        PartidaCampeonatoDto map = PartidaCampeonatoMapper.to(find);
        
        return ResponseEntity.ok(map);
    }

    @PutMapping("delete")
    public ResponseEntity<PartidaCampeonatoDto> delete(@RequestParam(name = "id") String id) {

        PartidaCampeonato delete = partidaCampeonatoService.delete(UUID.fromString(id));
        PartidaCampeonatoDto find = PartidaCampeonatoMapper.to(delete);
        
        return ResponseEntity.ok(find);
    }
}
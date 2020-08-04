package com.sgcampeonato.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.enuns.EnumSituacao;
import com.sgcampeonato.core.enuns.EnumVencedor;
import com.sgcampeonato.core.services.partidaCampeonato.PartidaCampeonatoService;
import com.sgcampeonato.utils.BaseController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
    public ResponseEntity<Page<PartidaCampeonato>> list(
        @RequestParam(name = "page") int page) {

        Page<PartidaCampeonato> list = partidaCampeonatoService.list(page);
        
        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<PartidaCampeonato> find(
        @RequestParam(name = "id") String id) {

        PartidaCampeonato find = partidaCampeonatoService.find(UUID.fromString(id));        
        return ResponseEntity.ok(find);
    }

    @PostMapping("save")
    public ResponseEntity<PartidaCampeonato> save(@RequestBody PartidaCampeonato entity)  {
        
        PartidaCampeonato find = partidaCampeonatoService.save(entity);
        
        return ResponseEntity.ok(find);
    }

    @PutMapping("update")
    public ResponseEntity<PartidaCampeonato> updateSituacao(
        @RequestParam(name = "id") String id, 
        @RequestParam(name = "situacao") EnumSituacao situacao,
        @RequestParam(name = "vencedor") EnumVencedor vencedor) {

        PartidaCampeonato find = partidaCampeonatoService.updateSituacao(UUID.fromString(id), situacao, vencedor);
        
        return ResponseEntity.ok(find);
    }

    @PutMapping("delete")
    public ResponseEntity<PartidaCampeonato> delete(@RequestParam(name = "id") String id) {
        
        PartidaCampeonato find = partidaCampeonatoService.delete(UUID.fromString(id));
        
        return ResponseEntity.ok(find);
    }
}
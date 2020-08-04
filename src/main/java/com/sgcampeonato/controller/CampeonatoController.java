package com.sgcampeonato.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.sgcampeonato.application.exceptions.RegraBaseException;
import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.services.campeonato.CampeonatoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/campeonato")
public class CampeonatoController {
    
    @Autowired
    private CampeonatoService campeonatoService;

    @ExceptionHandler(RegraBaseException.class)
    public ResponseEntity error(RegraBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    @GetMapping("list")
    public ResponseEntity<Page<Campeonato>> list(
        @RequestParam(name = "page") int page) {

        Page<Campeonato> list = campeonatoService.list(page);
        
        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Campeonato> find(
        @RequestParam(name = "id") String id) {

        Campeonato find = campeonatoService.find(UUID.fromString(id));
        
        return ResponseEntity.ok(find);
    }

    @PostMapping("save")
    public ResponseEntity<Campeonato> save(@RequestBody Campeonato entity)  {
        
        Campeonato find = campeonatoService.save(entity);
        
        return ResponseEntity.ok(find);
    }

    @PutMapping("update")
    public ResponseEntity<Campeonato> update(@RequestBody Campeonato entity) {
        
        Campeonato find = campeonatoService.update(entity);
        
        return ResponseEntity.ok(find);
    }
}
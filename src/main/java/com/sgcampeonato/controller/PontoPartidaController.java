package com.sgcampeonato.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.sgcampeonato.core.entitys.pontoPartida.PontoPartida;
import com.sgcampeonato.core.services.pontoPartida.PontoPartidaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/pontoPartida")
public class PontoPartidaController {
    
    @Autowired
    private PontoPartidaService pontoPartidaService;

    @ExceptionHandler()

    @GetMapping("list")
    public ResponseEntity<Page<PontoPartida>> list(
        @RequestParam(name = "page") int page) {

        Page<PontoPartida> list = pontoPartidaService.list(page);
        
        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<PontoPartida> find(
        @RequestParam(name = "id") String id) {

        PontoPartida find = pontoPartidaService.find(UUID.fromString(id));
        
        return ResponseEntity.ok(find);
    }

    @PostMapping("save")
    public ResponseEntity<PontoPartida> save(@RequestBody PontoPartida entity)  {
        
        PontoPartida find = pontoPartidaService.save(entity);
        
        return ResponseEntity.ok(find);
    }

    @PutMapping("update")
    public ResponseEntity<PontoPartida> update(@RequestBody PontoPartida entity) {
        
        PontoPartida find = pontoPartidaService.update(entity);
        
        return ResponseEntity.ok(find);
    }
}
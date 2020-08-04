package com.sgcampeonato.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.services.time.TimeService;
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
@RequestMapping("/api/time")
public class TimeController extends BaseController {
    
    @Autowired
    private TimeService timeService;

    @GetMapping("novo")
    public ResponseEntity<Time> novo() {
        return ResponseEntity.ok(new Time());
    }

    @GetMapping("list")
    public ResponseEntity<Page<Time>> list(
        @RequestParam(name = "page") int page) {

        Page<Time> list = timeService.list(page);
        
        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<Time> find(
        @RequestParam(name = "id") String id) {

        Time find = timeService.find(UUID.fromString(id));
        
        return ResponseEntity.ok(find);
    }

    @PostMapping("save")
    public ResponseEntity<Time> save(@RequestBody Time entity)  {
        
        Time find = timeService.save(entity);
        
        return ResponseEntity.ok(find);
    }

    @PutMapping("update")
    public ResponseEntity<Time> update(@RequestBody Time entity) {
        
        Time find = timeService.update(entity);
        
        return ResponseEntity.ok(find);
    }

    @PutMapping("delete")
    public ResponseEntity<Time> delete(@RequestParam(name = "id") String id) {
        
        Time find = timeService.delete(UUID.fromString(id));
        
        return ResponseEntity.ok(find);
    }
}
package com.sgcampeonato.controller;

import com.sgcampeonato.application.dto.TimeDto;
import com.sgcampeonato.application.mappers.TimeMapper;
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
    public ResponseEntity<Page<TimeDto>> list(@RequestParam(name = "page") int page) {

        Page<TimeDto> list = timeService.list(page)
        .map(model -> TimeMapper.to(model));

        return ResponseEntity.ok(list);
    }

    @GetMapping("list/name")
    public ResponseEntity<Page<TimeDto>> listName(@RequestParam(name = "page") int page,
    @RequestParam(name = "name") String name) {

        Page<TimeDto> list = timeService.listName(page, name)
        .map(model -> TimeMapper.to(model));

        return ResponseEntity.ok(list);
    }

    @GetMapping("find")
    public ResponseEntity<TimeDto> find(@RequestParam(name = "id") String id) {

        Time find = timeService.find(UUID.fromString(id));
        
        TimeDto findDto = TimeMapper.to(find);

        return ResponseEntity.ok(findDto);
    }

    @PostMapping("save")
    public ResponseEntity<TimeDto> save(@RequestBody TimeDto entityDto) {

        Time entity = TimeMapper.to(entityDto);
        
        timeService.save(entity);

        return ResponseEntity.ok(TimeMapper.to(entity));
    }

    @PutMapping("update")
    public ResponseEntity<TimeDto> update(@RequestBody TimeDto entityDto) {
                
        Time entity = TimeMapper.to(entityDto);
        
        timeService.update(entity);

        return ResponseEntity.ok(TimeMapper.to(entity));
    }

    @PutMapping("delete")
    public ResponseEntity<TimeDto> delete(@RequestParam(name = "id") String id) {

        Time entity = timeService.delete(UUID.fromString(id));

        return ResponseEntity.ok(TimeMapper.to(entity));
    }
}
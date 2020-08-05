package com.sgcampeonato.core.services.time;

import com.sgcampeonato.application.dto.TimeDto;
import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.time.TimeRepository;

import org.springframework.data.domain.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

@Service
public class TimeService extends BaseService<Time> {

    @Autowired
    public TimeService(TimeRepository repository) {
        super(repository);
    }

	public Page<Time> listName(int page, String name) {
        Time time = new Time();
        time.setName(name);
        Example<Time> example = Example.of(time, 
        ExampleMatcher.matching().withIgnoreCase()
                .withIgnorePaths("id")
                .withIgnorePaths("campeonatos")
                .withIgnorePaths("partidas")
                .withIgnoreNullValues()
                .withStringMatcher(StringMatcher.CONTAINING));

		return _repository.findAll(example, PageRequest.of((page - 1), 10));
	}
}
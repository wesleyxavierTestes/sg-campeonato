package com.sgcampeonato.core.services.time;

import com.sgcampeonato.core.entitys.time.Time;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.time.TimeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TimeService extends BaseService<Time> {

    @Autowired
    public TimeService(TimeRepository repository) {
        super(repository);
    }
    
    

}
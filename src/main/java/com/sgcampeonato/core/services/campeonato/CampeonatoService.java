package com.sgcampeonato.core.services.campeonato;

import com.sgcampeonato.core.entitys.campeonato.Campeonato;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.campeonato.CampeonatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CampeonatoService extends BaseService<Campeonato> {

    @Autowired
    public CampeonatoService(CampeonatoRepository repository) {
        super(repository);
    }
    
    

}
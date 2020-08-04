package com.sgcampeonato.core.services.pontoPartida;

import com.sgcampeonato.core.entitys.pontoPartida.PontoPartida;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.pontoPartida.PontoPartidaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PontoPartidaService extends BaseService<PontoPartida> {

    @Autowired
    public PontoPartidaService(PontoPartidaRepository repository) {
        super(repository);
    }
    
    

}
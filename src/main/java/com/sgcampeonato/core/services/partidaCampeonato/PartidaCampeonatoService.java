package com.sgcampeonato.core.services.partidaCampeonato;

import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.partidaCampeonato.PartidaCampeonatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaCampeonatoService extends BaseService <PartidaCampeonato, PartidaCampeonatoRepository> {

    @Autowired
    public PartidaCampeonatoService(PartidaCampeonatoRepository repository) {
        super(repository);
    }
}
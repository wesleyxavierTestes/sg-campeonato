package com.sgcampeonato.core.services.partidaCampeonato;

import java.util.UUID;

import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;
import com.sgcampeonato.core.enuns.EnumSituacao;
import com.sgcampeonato.core.enuns.EnumVencedor;
import com.sgcampeonato.core.services.BaseService;
import com.sgcampeonato.infra.repositorys.partidaCampeonato.PartidaCampeonatoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PartidaCampeonatoService extends BaseService <PartidaCampeonato> {

    @Autowired
    public PartidaCampeonatoService(PartidaCampeonatoRepository repository) {
        super(repository);
    }
    
	public PartidaCampeonato updateSituacao(UUID id, EnumSituacao situacao, EnumVencedor vencedor) {
        PartidaCampeonato entityupdate = this.find(id);
        
        return null;
	}

}
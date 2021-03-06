package com.sgcampeonato.core.services;

import java.util.Optional;
import java.util.UUID;

import com.sgcampeonato.application.exceptions.RegraBaseException;
import com.sgcampeonato.core.entitys.BaseEntity;
import com.sgcampeonato.infra.repositorys.BaseRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public abstract class BaseService<T extends BaseEntity, Y extends BaseRepository<T>> {

    protected final Y _repository;

    public BaseService(Y repository) {
        _repository = repository;
    }

    public Page<T> list(int page) {
        return this._repository.findAll(PageRequest.of((page - 1), 10));
    }

    public T find(UUID id) {
        Optional<T> optional = this._repository.findById(id);

        if (!optional.isPresent())
            throw new RegraBaseException("Busca Inválida item inexistente");
            
        return optional.get();
    }

    public T save(T entity) {

        this._repository.save(entity);

        return entity;
    }

    public T update(T entity) {
        Optional<T> optional = this._repository.findById(entity.getId());

        if (!optional.isPresent())
            throw new RegraBaseException("Item inexistente");

        this._repository.save(entity);

        return entity;
    }

    public T delete(UUID id) {
        Optional<T> optional = this._repository.findById(id);
        
        if (!optional.isPresent())
            throw new RegraBaseException("Item inexistente");

        this._repository.deleteById(id);

        return optional.get();
    }
}
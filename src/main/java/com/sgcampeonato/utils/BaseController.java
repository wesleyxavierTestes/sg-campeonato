package com.sgcampeonato.utils;

import com.sgcampeonato.application.exceptions.RegraBaseException;

import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public abstract class BaseController {

    @ExceptionHandler(RegraBaseException.class)
    public ResponseEntity<String> error(RegraBaseException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    } 
    
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> error(DataAccessException ex) {
        String mensagemErro = String.format("Erro de banco de dados %s", ex.getMessage());
        return ResponseEntity.badRequest().body(mensagemErro);
    } 
}
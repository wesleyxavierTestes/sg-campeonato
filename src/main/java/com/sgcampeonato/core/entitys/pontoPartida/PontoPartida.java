package com.sgcampeonato.core.entitys.pontoPartida;

import javax.persistence.Entity;

import com.sgcampeonato.core.entitys.BaseEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PontoPartida extends BaseEntity {
    
    private String nome;
}
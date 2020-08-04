package com.sgcampeonato.core.entitys.time;

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
public class Time extends BaseEntity {
    
    private String nome;
}
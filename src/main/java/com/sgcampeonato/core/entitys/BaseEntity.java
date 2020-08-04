package com.sgcampeonato.core.entitys;

import java.util.UUID;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity {
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    private UUID id;
}
package com.sgcampeonato.core.entitys.campeonato;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.sgcampeonato.core.entitys.BaseEntity;
import com.sgcampeonato.core.entitys.partidaCampeonato.PartidaCampeonato;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Campeonato extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDateTime dateInit;

    @Column(nullable = false)
    private LocalDateTime dateEnd;

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<PartidaCampeonato> partidasCampeonato;

    public boolean isFinished() {
        return LocalDateTime.now().isAfter(dateEnd);
    }
}
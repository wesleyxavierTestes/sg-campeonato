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
    @JsonIgnore
    private LocalDateTime dateInit;

    @Column(nullable = false)
    @JsonIgnore
    private LocalDateTime dateEnd;

    @JsonSetter("dateInit")
    public void setJsonDataInit(String data) {
        this.dateInit = LocalDateTime.parse(data);
    }

    @JsonGetter("dateInit")
    public String getJsonDataInit(String data) {
        return this.dateInit.toString();
    }

    @JsonSetter("dateEnd")
    public void setJsonDataEnd(String data) {
        this.dateEnd = LocalDateTime.parse(data);
    }

    @JsonGetter("dateEnd")
    public String getJsonDataEnd(String data) {
        return this.dateEnd.toString();
    }

    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<PartidaCampeonato> partidasCampeonato;

    public boolean isFinished() {
        return LocalDateTime.now().isAfter(dateEnd);
    }
}
package com.av3poo.models;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Professor.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Professor extends Pessoa {
    
    public static final String TABLE_NAME = "professor";

    @Column(nullable = false)
    private String disciplinaPrincipal;

    @OneToMany(mappedBy = "professor")
    private List<Disciplina> disciplinas;

}

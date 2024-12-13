package com.av3poo.models;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = Aluno.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Aluno extends Pessoa {

    public static final String TABLE_NAME = "aluno";
    
    @Column(name = "matricula", nullable = false, unique = true, updatable = false)
    @NotBlank
    private String matricula;

    @Column(name = "dataNasc", nullable = false)
    private LocalDate dataNascimento;

    @ManyToOne
    @JoinColumn(name = "turma_id", referencedColumnName = "id")
    @JsonIgnoreProperties({"alunos"})
    private Turma turma;

    @ManyToMany(mappedBy = "alunos")
    @JsonIgnoreProperties("disciplinas")
    private List<Disciplina> disciplinas;

}

package com.av3poo.models;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Nota.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Nota {

    public static final String TABLE_NAME = "nota";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "aluno_id", nullable = false, updatable = true)
    @JsonIgnoreProperties({"disciplinas", "turma", "dataNascimento", "email"})
    private Aluno aluno;

    @ManyToOne
    @JoinColumn(name = "disciplina_id", nullable = false, updatable = true)
    @JsonIgnoreProperties({ "professor", "alunos"})
    private Disciplina disciplina;

    @Column(name = "valor", nullable = false, updatable = true)
    private double valor;

    @Column(name = "dataAvaliacao", nullable = false, updatable = true)
    private LocalDate dataAvaliacao;

}

package com.av3poo.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = Turma.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Turma {

    public static final String TABLE_NAME = "turma";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", nullable = false)
    @NotBlank
    @Size(min = 5, max = 155)
    private String nome;

    @Column(name = "ano", nullable = false)
    private int ano;

    @OneToMany(mappedBy = "turma", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"disciplinas", "alunos", "turma"})
    private List<Aluno> alunos;

    @ManyToMany
    @JoinTable(
        name = "turma_disciplina",
        joinColumns = @JoinColumn(name = "turma_id"),
        inverseJoinColumns = @JoinColumn(name = "disciplina_id")
    )
    @JsonIgnoreProperties({"professor", "alunos"})
    private List<Disciplina> disciplinas;

}

package com.av3poo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.av3poo.models.Professor;
import com.av3poo.services.ProfessorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private ProfessorService professorService;

    @GetMapping("/{id}")
    public ResponseEntity<Professor> findById(@PathVariable Long id) {
        Professor obj = this.professorService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/professores")
    public ResponseEntity<List<Professor>> findAllProfessores() {
        List<Professor> professores = this.professorService.procurarProfessores();
        return ResponseEntity.ok().body(professores);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> criar(@Valid @RequestBody Professor professor) {

        Professor aluno = this.professorService.criarProfessor(professor);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Professor professor, @PathVariable Long id) {

        professor.setId(id);
        this.professorService.atualizaProfessor(professor);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        
        this.professorService.deletarProfessor(id);;
        return ResponseEntity.noContent().build();

    }
    
}

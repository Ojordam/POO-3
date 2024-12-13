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

import com.av3poo.models.Disciplina;
import com.av3poo.services.DisciplinaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/disciplina")
public class DisciplinaController {
    
    @Autowired
    private DisciplinaService disciplinaService;

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> findById(@PathVariable Long id) {
        Disciplina obj = this.disciplinaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/disciplinas")
    public ResponseEntity<List<Disciplina>> encontrarTodasDisciplinas() {
        List<Disciplina> objs = this.disciplinaService.encontrarTodasDisciplinas();
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> criar(@Valid @RequestBody Disciplina obj) {

        Disciplina aluno = this.disciplinaService.criar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Disciplina obj, @PathVariable Long id) {

        obj.setId(id);
        this.disciplinaService.atualizar(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        
        this.disciplinaService.deletar(id);
        return ResponseEntity.noContent().build();

    }

}

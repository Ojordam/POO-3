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

import com.av3poo.models.Nota;
import com.av3poo.services.NotaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/nota")
public class NotaController {

    @Autowired
    private NotaService notaService;

    @GetMapping("/{id}")
    public ResponseEntity<Nota> findById(@PathVariable Long id) {
        Nota obj = this.notaService.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @GetMapping("/notas")
    public ResponseEntity<List<Nota>> encontrarTodasAsNotas() {
        List<Nota> objs = this.notaService.encontrarTodasNotas();
        return ResponseEntity.ok().body(objs);
    }

    @GetMapping("/notas/{id}")
    public ResponseEntity<List<Nota>> buscarTodasAsNotasAluno(@PathVariable Long id) {
        List<Nota> objs = this.notaService.encontrarTodasNotasId(id);
        return ResponseEntity.ok().body(objs);
    }

    @PostMapping
    @Validated
    public ResponseEntity<Void> criar(@Valid @RequestBody Nota obj) {

        Nota aluno = this.notaService.criar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> atualizar(@Valid @RequestBody Nota obj, @PathVariable Long id) {

        obj.setId(id);
        this.notaService.atualizar(obj);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {

        this.notaService.deletar(id);
        return ResponseEntity.noContent().build();

    }

}

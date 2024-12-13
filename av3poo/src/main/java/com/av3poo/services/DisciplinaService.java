package com.av3poo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.av3poo.models.Disciplina;
import com.av3poo.repositories.DisciplinaRepository;


@Service
public class DisciplinaService {
    
    @Autowired
    private DisciplinaRepository disciplinaRepository;

    public Disciplina findById(Long id) {
        Disciplina disciplina = this.disciplinaRepository.findById(id).orElseThrow(() -> new RuntimeException(
            "Disciplina não encontrada! Id: " + id + ", Tipo: " + Disciplina.class.getName()
        ));

        return disciplina;
    }

    public List<Disciplina> encontrarTodasDisciplinas(){
        
        List<Disciplina> disciplinas = this.disciplinaRepository.findAll();
        return disciplinas;
    }

    @Transactional
    public Disciplina criar(Disciplina disciplina) {
        disciplina.setId(null);
        disciplina = this.disciplinaRepository.save(disciplina);
        return disciplina;
    }

    @Transactional
    public Disciplina atualizar(Disciplina disciplina) {
        Disciplina disciplina2 = findById(disciplina.getId());
        disciplina2.setNome(disciplina.getNome());
        disciplina2.setCargaHoraria(disciplina.getCargaHoraria());
        disciplina2.setProfessor(disciplina.getProfessor());
        return this.disciplinaRepository.save(disciplina2);
    }

    public void deletar(Long id) {
        
        findById(id);
        try{
            this.disciplinaRepository.deleteById(id);
        } catch(Exception e) {
            throw new RuntimeException("Não foi possivel realizar a operação, segue a mesagem de error: " + e.getMessage());
        }
 
    }

    


}

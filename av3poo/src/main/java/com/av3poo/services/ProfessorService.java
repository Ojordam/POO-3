package com.av3poo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.av3poo.models.Professor;
import com.av3poo.repositories.ProfessorRepository;


@Service
public class ProfessorService {
    
    @Autowired
    private ProfessorRepository professorRepository;

    public Professor findById(Long id) {
        Professor professor = this.professorRepository.findById(id).orElseThrow(() -> new RuntimeException(
            "Professor não encontrado! Id: " + id + ", Tipo: " + Professor.class.getName()
        ));

        return professor;
    }

    public List<Professor> procurarProfessores(){
        
        List<Professor> professores = this.professorRepository.findAll();
        return professores;
    }

    @Transactional
    public Professor criarProfessor(Professor obj) {

        obj.setId(null);
        obj = this.professorRepository.save(obj);
        return obj;

    }

    @Transactional
    public Professor atualizaProfessor(Professor obj) {

        Professor newObj = findById(obj.getId());
        newObj.setDisciplinaPrincipal(obj.getDisciplinaPrincipal());
        newObj.setEmail(obj.getEmail());
        newObj.setNome(obj.getNome());
        return this.professorRepository.save(newObj);
    }

    public void deletarProfessor(Long id){
        findById(id);
        try {
            this.professorRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel realizar a operação, segue a mesagem de error: " + e.getMessage());
        }
    }

}

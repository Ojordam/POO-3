package com.av3poo.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.av3poo.models.Aluno;
import com.av3poo.repositories.AlunoRepository;


@Service
public class AlunoService {
    
    @Autowired
    private AlunoRepository alunoRepository;

    public Aluno findById(Long id){
        Aluno aluno = this.alunoRepository.findById(id).orElseThrow(() -> new RuntimeException(
            "Aluno não encontrado! Id: " + id + ", Tipo: " + Aluno.class.getName()
            ));
        return aluno;   
    }

    public List<Aluno> encontrarTodosAlunos(){
        
        List<Aluno> alunos = this.alunoRepository.findAll();
        return alunos;
    }

    @Transactional
    public Aluno criar(Aluno aluno) {
        aluno.setId(null);
        aluno = this.alunoRepository.save(aluno);
        return aluno;
    }

    @Transactional
    public Aluno atualizar(Aluno aluno) {

        Aluno aluno2 = findById(aluno.getId());
        aluno2.setDataNascimento(aluno.getDataNascimento());
        aluno2.setEmail(aluno.getEmail());
        aluno2.setNome(aluno.getNome());
        aluno2.setTurma(aluno.getTurma());
        return this.alunoRepository.save(aluno2);

    }

    public void deletar(Long id) {

        findById(id);
        try {
            this.alunoRepository.deleteById(id);
        } catch(Exception e){
            throw new RuntimeException("Não foi possivel realizar a operação, segue a mesagem de error: " + e.getMessage());
        }

    }
    

}

package com.av3poo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.av3poo.models.Turma;
import com.av3poo.repositories.TurmaRepository;

@Service
public class TurmaService {

    @Autowired
    private TurmaRepository turmaRepository;


    public Turma findById(Long id){
        Turma turma = this.turmaRepository.findById(id).orElseThrow(() -> new RuntimeException(
                "Turma não encontrado! Id: " + id + ", Tipo: " + Turma.class.getName()
        ));

        return turma;
    }

    public List<Turma> encontrarTurmas(){
        
        List<Turma> turmas = this.turmaRepository.findAll();
        return turmas;
    }

    @Transactional
    public Turma criarTurma(Turma turma) {

        turma.setId(null);
        turma = this.turmaRepository.save(turma);
        return turma;

    }

    @Transactional
    public Turma atualizarTurma(Turma turma){

        Turma turma2 = findById(turma.getId());
        turma2.setAlunos(turma.getAlunos());;
        turma2.setAno(turma.getAno());
        turma2.setDisciplinas(turma.getDisciplinas());
        turma2.setNome(turma.getNome());
        return this.turmaRepository.save(turma2);

    }

    public void deletarTurma(Long id){
        findById(id);
        try {
            this.turmaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel realizar a operação, segue a mesagem de error: " + e.getMessage());
        }
    }
    
}

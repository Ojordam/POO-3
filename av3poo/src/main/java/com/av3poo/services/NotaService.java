package com.av3poo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.av3poo.models.Nota;
import com.av3poo.repositories.NotaRepository;


@Service
public class NotaService {
    
    @Autowired
    private NotaRepository notaRepository;

    public Nota findById(Long id) {

        Nota nota = this.notaRepository.findById(id).orElseThrow(() -> new RuntimeException(
            "Cliente não encontrado! Id: " + id + ", Tipo: " + Nota.class.getName()
    ));

    return nota;

    }

    public List<Nota> encontrarTodasNotas(){
        
        List<Nota> notas = this.notaRepository.findAll();
        return notas;
    }

    public List<Nota> encontrarTodasNotasId(Long id){
        
        List<Nota> notas = this.notaRepository.findAllByAluno_id(id);
        return notas;
    }

    @Transactional
    public Nota criar(Nota nota) {

        nota.setId(null);
        nota = this.notaRepository.save(nota);
        return nota;

    }

    @Transactional
    public Nota atualizar(Nota nota){

        Nota nota2 = findById(nota.getId());
        nota2.setAluno(nota.getAluno());
        nota2.setDataAvaliacao(nota.getDataAvaliacao());
        nota2.setDisciplina(nota.getDisciplina());
        nota2.setValor(nota.getValor());
        return this.notaRepository.save(nota2);

    }

    public void deletar(Long id){
        findById(id);
        try {
            this.notaRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não foi possivel realizar a operação, segue a mesagem de error: " + e.getMessage());
        }
    }
    

}

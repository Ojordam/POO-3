package com.av3poo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av3poo.models.Nota;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {

    List<Nota> findAllByAluno_id(Long id);
    
}

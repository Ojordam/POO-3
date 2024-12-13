package com.av3poo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av3poo.models.Turma;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {
    
}

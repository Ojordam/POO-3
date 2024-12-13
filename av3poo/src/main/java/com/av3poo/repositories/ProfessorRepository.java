package com.av3poo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.av3poo.models.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    
}

package com.example.Assignment1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Assignment1.Model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial,Long> {
    
}

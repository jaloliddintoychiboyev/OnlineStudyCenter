package com.example.onlinestudycenter.repository;

import com.example.onlinestudycenter.entity.StudyCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudyRepository extends JpaRepository<StudyCenter, Integer>
{
boolean existsById(Integer id);
}

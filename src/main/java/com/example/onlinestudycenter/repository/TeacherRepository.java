package com.example.onlinestudycenter.repository;

import com.example.onlinestudycenter.entity.Teachers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teachers, Integer> {
}

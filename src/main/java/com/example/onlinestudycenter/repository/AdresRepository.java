package com.example.onlinestudycenter.repository;

import com.example.onlinestudycenter.entity.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdresRepository extends JpaRepository<Adress,Integer> {
    boolean existsById(Integer id);
}

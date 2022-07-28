package com.angga.backendtest.repository;

import com.angga.backendtest.model.Matkul;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface MatkulRepository extends JpaRepository<Matkul, Integer> {
    @Query("Select a from Matkul a where id = ?1")
    Matkul getByIdMatkul(Integer id);
}

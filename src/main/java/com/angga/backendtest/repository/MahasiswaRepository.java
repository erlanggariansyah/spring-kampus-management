package com.angga.backendtest.repository;

import com.angga.backendtest.model.Mahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MahasiswaRepository extends JpaRepository<Mahasiswa, Integer> {
    @Query("Select a from Mahasiswa a where nim = ?1")
    Mahasiswa getByNim(Integer nim);

    @Query("Select a from Mahasiswa a where nim = ?1 AND id != ?2")
    Mahasiswa getByNimUpdate(Integer nim, Integer id);

    @Query("Select a from Mahasiswa a where jurusan_id = ?1")
    List<Mahasiswa> getMahasiswaByJurusan(Integer jurusan_id);
}

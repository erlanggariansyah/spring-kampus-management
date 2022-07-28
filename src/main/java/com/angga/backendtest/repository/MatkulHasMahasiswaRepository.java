package com.angga.backendtest.repository;

import com.angga.backendtest.model.MatkulHasMahasiswa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MatkulHasMahasiswaRepository extends JpaRepository<MatkulHasMahasiswa, Integer> {
    @Query("Select a from MatkulHasMahasiswa a where mahasiswa_id = ?1")
    List<MatkulHasMahasiswa> getByMahasiswaId(Integer id);

    @Query(value = "Select count(distinct mahasiswa_id) as val from matkul_has_mahasiswa", nativeQuery = true)
    Integer countMahasiswaUseMatkul();

    @Query(value = "Delete from matkul_has_mahasiswa where mahasiswa_id = ?1", nativeQuery = true)
    void deleteByMahasiswaId(Integer mahasiswa_id);
}

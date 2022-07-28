package com.angga.backendtest.service;

import com.angga.backendtest.model.Mahasiswa;

import java.util.List;

public interface MahasiswaService {
    void tambahMahasiswa(Mahasiswa mahasiswa);
    Mahasiswa updateMahasiswa(Integer id, String nama, Integer nim);
    void deleteMahasiswa(Integer id);
    List<Mahasiswa> getAllMahasiswa();
    Object getMahasiswaById(Integer id);
    Mahasiswa getMahasiswaByNim(Integer nim);
    Mahasiswa getMahasiswaByNimUpdate(Integer nim, Integer id);
    Integer getMahasiswaUseMatkul();
    List<Mahasiswa> getMahasiswaByJurusan(Integer jurusan_id);
}

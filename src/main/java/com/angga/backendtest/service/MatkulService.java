package com.angga.backendtest.service;

import com.angga.backendtest.model.Matkul;

import java.util.List;

public interface MatkulService {
    List<Matkul> getAllMatkul();
    Matkul getMatkulById(Integer id);
    void assign(Integer mahasiswa_id, Integer matkul_id);
    void editMatkulMhs(Integer mahasiswa_id, Integer[] matkul_id);
}

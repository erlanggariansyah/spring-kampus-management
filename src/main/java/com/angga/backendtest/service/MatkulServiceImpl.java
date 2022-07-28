package com.angga.backendtest.service;

import com.angga.backendtest.model.Matkul;
import com.angga.backendtest.model.MatkulHasMahasiswa;
import com.angga.backendtest.repository.MatkulHasMahasiswaRepository;
import com.angga.backendtest.repository.MatkulRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class MatkulServiceImpl implements MatkulService {
    private final MatkulRepository matkulRepository;
    private final MatkulHasMahasiswaRepository matkulHasMahasiswaRepository;

    @Override
    public List<Matkul> getAllMatkul() {
        return matkulRepository.findAll();
    }

    @Override
    public Matkul getMatkulById(Integer id) {
        return matkulRepository.findById(id).orElseThrow();
    }

    @Override
    public void assign(Integer mahasiswa_id, Integer matkul_id) {
        MatkulHasMahasiswa matkulHasMahasiswa = new MatkulHasMahasiswa();
        matkulHasMahasiswa.setMahasiswa_id(mahasiswa_id);
        matkulHasMahasiswa.setMatkul_id(matkul_id);
        matkulHasMahasiswaRepository.save(matkulHasMahasiswa);
    }

    @Override
    public void editMatkulMhs(Integer mahasiswa_id, Integer[] matkul_id) {
        matkulHasMahasiswaRepository.deleteByMahasiswaId(mahasiswa_id);

        Arrays.stream(matkul_id).forEach(matkuls -> {
            MatkulHasMahasiswa matkulHasMahasiswa = new MatkulHasMahasiswa();
            matkulHasMahasiswa.setMatkul_id(matkuls);
            matkulHasMahasiswa.setMahasiswa_id(mahasiswa_id);

            matkulHasMahasiswaRepository.save(matkulHasMahasiswa);
        });
    }
}

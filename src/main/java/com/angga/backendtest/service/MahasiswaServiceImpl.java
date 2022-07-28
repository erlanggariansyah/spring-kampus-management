package com.angga.backendtest.service;

import com.angga.backendtest.model.Mahasiswa;
import com.angga.backendtest.model.Matkul;
import com.angga.backendtest.model.MatkulHasMahasiswa;
import com.angga.backendtest.repository.MahasiswaRepository;
import com.angga.backendtest.repository.MatkulHasMahasiswaRepository;
import com.angga.backendtest.repository.MatkulRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class MahasiswaServiceImpl implements MahasiswaService {
    private final MahasiswaRepository mahasiswaRepository;
    private final MatkulHasMahasiswaRepository matkulHasMahasiswaRepository;
    private final MatkulRepository matkulRepository;

    @Override
    public void tambahMahasiswa(Mahasiswa mahasiswa) {
        mahasiswaRepository.save(mahasiswa);
    }

    @Override
    public Mahasiswa updateMahasiswa(Integer id, String nama, Integer nim) {
        Mahasiswa mahasiswa = null;

        try {
            mahasiswa = mahasiswaRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.info("error update mahasiswa: " + e.getMessage());
        }

        assert mahasiswa != null;
        mahasiswa.setNama(nama);
        mahasiswa.setNim(nim);

        return mahasiswa;
    }

    @Override
    public void deleteMahasiswa(Integer id) {
        Mahasiswa mahasiswa = null;

        try {
            mahasiswa = mahasiswaRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.info("error delete mahasiswa: " + e.getMessage());
        }

        assert mahasiswa != null;
        mahasiswaRepository.delete(mahasiswa);
    }

    @Override
    public List<Mahasiswa> getAllMahasiswa() {
        return mahasiswaRepository.findAll();
    }

    @Override
    public Object getMahasiswaById(Integer id) {
        Mahasiswa mahasiswa = null;

        try {
            mahasiswa = mahasiswaRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.info("error get mahasiswa: " + e.getMessage());
        }

        assert mahasiswa != null;
        List<Matkul> matkulList = new ArrayList<>();
        List<MatkulHasMahasiswa> matkulHasMahasiswas = matkulHasMahasiswaRepository.getByMahasiswaId(id);
        matkulHasMahasiswas.forEach(matkulHasMahasiswa -> {
            Matkul matkul = matkulRepository.getByIdMatkul(matkulHasMahasiswa.getMatkul_id());
            matkulList.add(matkul);
        });

        MahasiswaWithMatkul mahasiswaWithMatkul = new MahasiswaWithMatkul();
        mahasiswaWithMatkul.setId(mahasiswa.getId());
        mahasiswaWithMatkul.setNim(mahasiswa.getNim());
        mahasiswaWithMatkul.setNama(mahasiswa.getNama());
        mahasiswaWithMatkul.setJurusan_id(mahasiswa.getJurusan_id());
        mahasiswaWithMatkul.setUpdated_at(mahasiswa.getUpdated_at());
        mahasiswaWithMatkul.setCreated_at(mahasiswa.getCreated_at());
        mahasiswaWithMatkul.setMatkulList(matkulList);

        return mahasiswaWithMatkul;
    }

    @Override
    public Mahasiswa getMahasiswaByNim(Integer nim) {
        return mahasiswaRepository.getByNim(nim);
    }

    @Override
    public Mahasiswa getMahasiswaByNimUpdate(Integer nim, Integer id) {
        return mahasiswaRepository.getByNimUpdate(nim, id);
    }

    @Override
    public Integer getMahasiswaUseMatkul() {
        return matkulHasMahasiswaRepository.countMahasiswaUseMatkul();
    }

    @Override
    public List<Mahasiswa> getMahasiswaByJurusan(Integer jurusan_id) {
        return mahasiswaRepository.getMahasiswaByJurusan(jurusan_id);
    }
}

@Data
class MahasiswaWithMatkul {
    private int id;
    private String nama;
    private int nim;
    private int jurusan_id;
    private Timestamp updated_at;
    private Timestamp created_at;
    private List<Matkul> matkulList;
}

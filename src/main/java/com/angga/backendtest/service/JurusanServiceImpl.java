package com.angga.backendtest.service;

import com.angga.backendtest.model.Jurusan;
import com.angga.backendtest.repository.JurusanRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class JurusanServiceImpl implements JurusanService {
    private final JurusanRepository jurusanRepository;

    @Override
    public Jurusan getJurusanById(Integer id) {
        Jurusan jurusan = null;

        try {
            jurusan = jurusanRepository.findById(id).orElseThrow();
        } catch (NoSuchElementException e) {
            log.info("error get mahasiswa: " + e.getMessage());
        }

        assert jurusan != null;
        return jurusan;
    }
}

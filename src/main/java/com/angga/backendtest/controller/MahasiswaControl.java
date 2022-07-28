package com.angga.backendtest.controller;

import com.angga.backendtest.model.Mahasiswa;
import com.angga.backendtest.service.JurusanService;
import com.angga.backendtest.service.MahasiswaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1")
@RequiredArgsConstructor
public class MahasiswaControl {
    private final MahasiswaService mahasiswaService;
    private final JurusanService jurusanService;

    @GetMapping("/mahasiswa")
    public ResponseEntity<List<Mahasiswa>> getAllMahasiswa() {
        return ResponseEntity.ok(mahasiswaService.getAllMahasiswa());
    }

    @GetMapping("/mahasiswa/get")
    public ResponseEntity<Object> getMahasiswaById(@RequestParam Integer id) {
        return ResponseEntity.ok(mahasiswaService.getMahasiswaById(id));
    }

    @PostMapping("/mahasiswa")
    public ResponseEntity<Map<String, String>> tambahMahasiswa(
            @RequestParam String nama,
            @RequestParam Integer nim,
            @RequestParam Integer jurusan_id
    ) {
        Map<String, String> response = new HashMap<>();
        Mahasiswa mahasiswa = new Mahasiswa();
        mahasiswa.setNama(nama);
        mahasiswa.setNim(nim);
        mahasiswa.setJurusan_id(jurusan_id);

        if (mahasiswaService.getMahasiswaByNim(mahasiswa.getNim()) != null) {
            response.put("error", "maaf nim sudah terdaftar.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        if (jurusanService.getJurusanById(jurusan_id) == null) {
            response.put("error", "maaf jurusan tidak terdaftar.");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        mahasiswaService.tambahMahasiswa(mahasiswa);
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/mahasiswa").toUriString());
        response.put("success", "mahasiswa berhasil ditambahkan.");

        return ResponseEntity.created(uri).body(response);
    }

    @DeleteMapping("/mahasiswa")
    public ResponseEntity<Map<String, String>> deleteMahasiswa(@RequestParam Integer id) {
        Map<String, String> response = new HashMap<>();
        if (mahasiswaService.getMahasiswaById(id) == null) {
            response.put("error", "maaf mahasiswa dengan id: " + id + " tidak terdaftar.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        mahasiswaService.deleteMahasiswa(id);
        response.put("success", "mahasiswa berhasil dihapus.");

        return ResponseEntity.ok(response);
    }

    @PutMapping("/mahasiswa")
    public ResponseEntity<Object> updateMahasiswa(
            @RequestParam Integer id,
            @RequestParam String nama,
            @RequestParam Integer nim
    ) {
        Map<String, String> response = new HashMap<>();
        if (mahasiswaService.getMahasiswaById(id) == null) {
            response.put("error", "maaf mahasiswa dengan id: " + id + " tidak terdaftar.");

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        if (mahasiswaService.getMahasiswaByNimUpdate(nim, id) != null) {
            response.put("error", "maaf nim sudah terdaftar");

            return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
        }

        Mahasiswa mahasiswa = mahasiswaService.updateMahasiswa(id, nama, nim);
        return ResponseEntity.ok(mahasiswa);
    }

    @GetMapping("/mahasiswa/matkul/count")
    public ResponseEntity<Integer> countMahasiswaWithMatkul() {
        return ResponseEntity.ok(mahasiswaService.getMahasiswaUseMatkul());
    }

    @GetMapping("/mahasiswa/jurusan")
    public ResponseEntity<List<Mahasiswa>> getMahasiswaByJurusan(@RequestParam Integer jurusan_id) {
        return ResponseEntity.ok(mahasiswaService.getMahasiswaByJurusan(jurusan_id));
    }
}

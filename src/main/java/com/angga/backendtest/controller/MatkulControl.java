package com.angga.backendtest.controller;

import com.angga.backendtest.model.Matkul;
import com.angga.backendtest.service.MatkulService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class MatkulControl {
    private final MatkulService matkulService;

    @GetMapping("/matkul")
    public ResponseEntity<List<Matkul>> getAllMatkul() {
        return ResponseEntity.ok(matkulService.getAllMatkul());
    }

    @GetMapping("/matkul/get")
    public ResponseEntity<Matkul> getMatkulById(@RequestParam Integer id) {
        return ResponseEntity.ok(matkulService.getMatkulById(id));
    }

    @PostMapping("/matkul/assign")
    public ResponseEntity<Object> assignMahasiswaToMatkul(
            @RequestParam Integer mahasiswa_id,
            @RequestParam Integer matkul_id
    ) {
        matkulService.assign(mahasiswa_id, matkul_id);

        Map<String, String> response = new HashMap<>();
        response.put("success", "mahasiswa berhasil diassign ke matkul tsb.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("/matkul/mahasiswa")
    public ResponseEntity<Object> editMatkulMahasiswa(
            @RequestParam Integer mahasiswa_id,
            @RequestParam Integer[] matkul_id
    ) {
        matkulService.editMatkulMhs(mahasiswa_id, matkul_id);

        Map<String, String> response = new HashMap<>();
        response.put("success", "matkul mahasiswa berhasil diassign ke matkul tsb.");
        return ResponseEntity.ok(response);
    }
}

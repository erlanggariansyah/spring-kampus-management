package com.angga.backendtest;

import com.angga.backendtest.model.*;
import com.angga.backendtest.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Random;

@SpringBootApplication
public class BackendTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendTestApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(
			MatkulHasMahasiswaRepository matkulHasMahasiswaRepository,
			MahasiswaRepository mahasiswaRepository,
			JurusanRepository jurusanRepository,
			FakultasRepository fakultasRepository,
			MatkulRepository matkulRepository
	) {
		return args -> {
			for (int i = 1; i < 5; i++) {
				Fakultas fakultas = new Fakultas();
				fakultas.setNama("Fakultas " + i);
				Fakultas createdFakultas = fakultasRepository.save(fakultas);

				for (int j = 1; j < 10; j++) {
					Jurusan jurusan = new Jurusan();
					jurusan.setNama("Jurusan " + j + " " + createdFakultas.getNama());
					jurusan.setFakultas_id(createdFakultas.getId());
					Jurusan createdJurusan = jurusanRepository.save(jurusan);

					Mahasiswa mahasiswa = new Mahasiswa();
					mahasiswa.setNim(new Random().nextInt());
					mahasiswa.setNama("Mahasiswa " + i);
					mahasiswa.setJurusan_id(createdJurusan.getId());
					Mahasiswa createdMahasiswa = mahasiswaRepository.save(mahasiswa);

					for (int k = 1; k < 15; k++) {
						Matkul matkul = new Matkul();
						matkul.setJurusan_id(createdJurusan.getId());
						matkul.setNama("Matkul " + k + " jurusan " + createdJurusan.getNama());
						Matkul createdMatkul = matkulRepository.save(matkul);

						MatkulHasMahasiswa matkulHasMahasiswa = new MatkulHasMahasiswa();
						matkulHasMahasiswa.setMahasiswa_id(createdMahasiswa.getId());
						matkulHasMahasiswa.setMatkul_id(createdMatkul.getId());
						matkulHasMahasiswaRepository.save(matkulHasMahasiswa);
					}
				}
			}
		};
	}
}

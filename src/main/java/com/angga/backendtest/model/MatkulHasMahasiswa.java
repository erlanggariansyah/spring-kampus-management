package com.angga.backendtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "matkul_has_mahasiswa")
public class MatkulHasMahasiswa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "matkul_id")
    private int matkul_id;

    @Column(name = "mahasiswa_id")
    private int mahasiswa_id;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created_at;
}

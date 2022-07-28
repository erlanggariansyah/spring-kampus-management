package com.angga.backendtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "mahasiswa")
public class Mahasiswa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "nama")
    private String nama;

    @Column(name = "nim")
    private int nim;

    @Column(name = "jurusan_id")
    private int jurusan_id;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private Timestamp updated_at;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created_at;
}

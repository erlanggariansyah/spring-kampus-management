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
@Table(name = "jurusan")
public class Jurusan {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "fakultas_id")
    private int fakultas_id;

    @Column(name = "nama")
    private String nama;

    @CreationTimestamp
    @Column(name = "created_at")
    private Timestamp created_at;
}

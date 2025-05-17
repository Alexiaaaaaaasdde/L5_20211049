package com.example.l5_20211049.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idrol")
    private Integer id;

    @Column(name = "nombre", nullable = false, unique = true)
    private String nombre;
}


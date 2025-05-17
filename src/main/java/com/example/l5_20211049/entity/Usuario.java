package com.example.l5_20211049.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUsuario")
    private Integer id;

    @Column(name = "nombre", nullable = false)
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @Column(name = "apellido", nullable = false)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(name = "dni", nullable = false, unique = true, length = 8)
    @NotBlank(message = "El DNI es obligatorio")
    @Pattern(regexp = "\\d{8}", message = "El DNI debe tener 8 dígitos")
    private String dni;

    @Column(name = "email", nullable = false, unique = true)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del email no es válido")
    private String email;

    @Column(name = "edad", nullable = false)
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 100, message = "La edad máxima es 100 años")
    private Integer edad;

    @Column(name = "pwd", nullable = false)
    @NotBlank(message = "La contraseña es obligatoria")
    private String password;

    @Column(name = "activo", nullable = false)
    private Boolean activo = true;

    @ManyToOne
    @JoinColumn(name = "idrol", nullable = false)
    private Rol rol;
}
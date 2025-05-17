package com.example.l5_20211049.controller;

import com.example.l5_20211049.entity.Rol;
import com.example.l5_20211049.entity.Usuario;
import com.example.l5_20211049.repository.RolRepository;
import com.example.l5_20211049.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@RequiredArgsConstructor
public class Trivia {

    private final RolRepository rolRepository;
    private final UsuarioService usuarioService;

    public static void main(String[] args) {
        SpringApplication.run(Trivia.class, args);
    }

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            // Crear roles si no existen
            if (rolRepository.count() == 0) {
                Rol rolAdmin = new Rol();
                rolAdmin.setNombre("ADMIN");
                rolRepository.save(rolAdmin);

                Rol rolUser = new Rol();
                rolUser.setNombre("USER");
                rolRepository.save(rolUser);

                // Crear usuario administrador por defecto
                if (!usuarioService.existePorEmail("admin@pucp.edu.pe")) {
                    Usuario admin = new Usuario();
                    admin.setNombre("Admin");
                    admin.setApellido("Sistema");
                    admin.setDni("00000000");
                    admin.setEmail("admin@pucp.edu.pe");
                    admin.setEdad(54);
                    admin.setPassword("admin123");
                    admin.setRol(rolAdmin);
                    usuarioService.guardar(admin);
                }

                // Crear usuario estándar por defecto
                if (!usuarioService.existePorEmail("user@pucp.edu.pe")) {
                    Usuario user = new Usuario();
                    user.setNombre("Juan");
                    user.setApellido("Usuario");
                    user.setDni("11111111");
                    user.setEmail("user@pucp.edu.pe");
                    user.setEdad(20);
                    user.setPassword("user123");
                    user.setRol(rolUser);
                    usuarioService.guardar(user);
                }
            }
        };
    }
}
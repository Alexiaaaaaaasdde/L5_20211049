package com.example.l5_20211049.service;

import com.example.l5_20211049.PasswordEncoder;
import com.example.l5_20211049.entity.Rol;
import com.example.l5_20211049.entity.Usuario;
import com.example.l5_20211049.repository.RolRepository;
import com.example.l5_20211049.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Integer id) {
        return usuarioRepository.findById(id);
    }

    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    @Transactional
    public Usuario guardar(Usuario usuario) {
        // Asignar rol USER por defecto si no tiene rol asignado
        if (usuario.getRol() == null) {
            Rol rolUser = rolRepository.findByNombre("USER")
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
            usuario.setRol(rolUser);
        }

        // Encriptar contraseña
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

        return usuarioRepository.save(usuario);
    }

    public boolean existePorDni(String dni) {
        return usuarioRepository.existsByDni(dni);
    }

    public boolean existePorEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }
}
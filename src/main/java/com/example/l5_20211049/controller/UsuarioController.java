package com.example.l5_20211049.controller;

import com.example.l5_20211049.entity.Rol;
import com.example.l5_20211049.entity.Usuario;
import com.example.l5_20211049.repository.RolRepository;
import com.example.l5_20211049.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final RolRepository rolRepository;

    @GetMapping
    public String listarUsuarios(Model model) {
        List<Usuario> usuarios = usuarioService.listarTodos();
        model.addAttribute("usuarios", usuarios);
        return "usuario/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevoUsuario(Model model) {
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("roles", rolRepository.findAll());
        return "usuario/formulario";
    }

    @PostMapping("/guardar")
    public String guardarUsuario(@Valid @ModelAttribute Usuario usuario,
                                 BindingResult result,
                                 Model model,
                                 RedirectAttributes redirectAttributes) {

        // Validaciones personalizadas
        if (usuario.getId() == null) {
            if (usuarioService.existePorDni(usuario.getDni())) {
                result.rejectValue("dni", "error.usuario", "Este DNI ya está registrado");
            }

            if (usuarioService.existePorEmail(usuario.getEmail())) {
                result.rejectValue("email", "error.usuario", "Este email ya está registrado");
            }
        }

        if (result.hasErrors()) {
            model.addAttribute("roles", rolRepository.findAll());
            return "usuario/formulario";
        }

        // Asignar rol USER por defecto
        if (usuario.getRol() == null) {
            Rol rolUser = rolRepository.findByNombre("USER")
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));
            usuario.setRol(rolUser);
        }

        usuarioService.guardar(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "Usuario guardado correctamente");
        return "redirect:/usuarios";
    }
}
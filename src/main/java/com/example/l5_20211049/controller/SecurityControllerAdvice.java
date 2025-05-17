package com.example.l5_20211049.controller;

import com.example.l5_20211049.entity.Usuario;
import com.example.l5_20211049.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class SecurityControllerAdvice {

    private final UsuarioService usuarioService;

    @ModelAttribute("usuarioActual")
    public Usuario getUsuarioActual() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return usuarioService.buscarPorEmail(auth.getName()).orElse(null);
        }
        return null;
    }

    @ModelAttribute("rolUsuario")
    public String getRolUsuario() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return auth.getAuthorities().stream().findFirst().map(Object::toString).orElse(null);
        }
        return null;
    }
}
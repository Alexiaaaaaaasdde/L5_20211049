package com.example.l5_20211049.controller;

import com.example.l5_20211049.service.JuegoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/partidas")
@RequiredArgsConstructor
public class PartidaController {

    private final JuegoService juegoService;

    @GetMapping
    public String listarPartidas(HttpSession session, Model model) {
        List<Partida> partidas = juegoService.obtenerHistorialPartidas(session);
        model.addAttribute("partidas", partidas);
        return "partida/historial";
    }

    @GetMapping("/{index}")
    public String verDetallePartida(@PathVariable("index") int index,
                                    HttpSession session,
                                    Model model) {
        List<Partida> partidas = juegoService.obtenerHistorialPartidas(session);

        if (index >= 0 && index < partidas.size()) {
            model.addAttribute("partida", partidas.get(index));
            return "partida/detalle";
        } else {
            return "redirect:/partidas";
        }
    }
}
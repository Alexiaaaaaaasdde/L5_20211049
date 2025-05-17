package com.example.l5_20211049.controller;

import com.example.l5_20211049.service.JuegoService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/juego")
@RequiredArgsConstructor
public class JuegoController {

    private final JuegoService juegoService;

    @GetMapping
    public String mostrarJuego(Model model, HttpSession session) {
        // Iniciar nuevo juego
        juegoService.iniciarNuevaPartida(session);

        @SuppressWarnings("unchecked")
        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute("preguntasActuales");
        model.addAttribute("preguntas", preguntas);

        return "juego/trivia";
    }

    @PostMapping("/procesar")
    public String procesarRespuestas(@RequestParam(required = false) List<Integer> respuestas,
                                     HttpSession session,
                                     Model model) {
        // Si respuestas es null, inicializamos como lista vacía
        if (respuestas == null) {
            respuestas = new ArrayList<>();
        }

        juegoService.guardarRespuestas(session, respuestas);

        // Obtener la última partida para mostrar resultados
        List<Partida> partidas = juegoService.obtenerHistorialPartidas(session);
        Partida ultimaPartida = partidas.get(partidas.size() - 1);

        model.addAttribute("partida", ultimaPartida);
        return "juego/resultado";
    }
}
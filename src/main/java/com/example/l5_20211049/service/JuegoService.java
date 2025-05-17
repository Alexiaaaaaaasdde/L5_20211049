package com.example.l5_20211049.service;
import com.example.l5_20211049.controller.Partida;
import com.example.l5_20211049.controller.Pregunta;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JuegoService {

    private static final String PARTIDAS_SESSION_KEY = "partidas";
    private static final String PREGUNTAS_ACTUALES_SESSION_KEY = "preguntasActuales";
    private static final int PREGUNTAS_POR_JUEGO = 5;
    private static final int MAX_OPCIONES = 4;

    public List<Pregunta> obtenerPreguntasAleatorias() {
        List<Pregunta> todasLasPreguntas = crearBancoDePreguntas();
        Collections.shuffle(todasLasPreguntas);
        return todasLasPreguntas.stream().limit(PREGUNTAS_POR_JUEGO).collect(Collectors.toList());
    }

    public void iniciarNuevaPartida(HttpSession session) {
        List<Pregunta> preguntas = obtenerPreguntasAleatorias();
        session.setAttribute(PREGUNTAS_ACTUALES_SESSION_KEY, preguntas);
    }

    public void guardarRespuestas(HttpSession session, List<Integer> respuestas) {
        @SuppressWarnings("unchecked")
        List<Pregunta> preguntas = (List<Pregunta>) session.getAttribute(PREGUNTAS_ACTUALES_SESSION_KEY);

        for (int i = 0; i < preguntas.size(); i++) {
            if (i < respuestas.size()) {
                preguntas.get(i).setRespuestaUsuario(respuestas.get(i));
            }
        }

        Partida partida = new Partida(preguntas);

        @SuppressWarnings("unchecked")
        List<Partida> partidas = (List<Partida>) session.getAttribute(PARTIDAS_SESSION_KEY);

        if (partidas == null) {
            partidas = new ArrayList<>();
        }

        partidas.add(partida);
        session.setAttribute(PARTIDAS_SESSION_KEY, partidas);
        session.removeAttribute(PREGUNTAS_ACTUALES_SESSION_KEY);
    }

    @SuppressWarnings("unchecked")
    public List<Partida> obtenerHistorialPartidas(HttpSession session) {
        List<Partida> partidas = (List<Partida>) session.getAttribute(PARTIDAS_SESSION_KEY);
        return partidas != null ? partidas : Collections.emptyList();
    }

    private List<Pregunta> crearBancoDePreguntas() {
        List<Pregunta> preguntas = new ArrayList<>();

        // Pregunta 1
        preguntas.add(new Pregunta(
                1,
                "¿Cuál es la capital de Perú?",
                List.of("Bogotá", "Lima", "Santiago", "Quito"),
                1
        ));

        // Pregunta 2
        preguntas.add(new Pregunta(
                2,
                "¿En qué año se fundó la PUCP?",
                List.of("1917", "1920", "1942", "1956"),
                1
        ));

        // Pregunta 3
        preguntas.add(new Pregunta(
                3,
                "¿Cuál es el río más largo del mundo?",
                List.of("Nilo", "Amazonas", "Misisipi", "Yangtsé"),
                1
        ));

        // Pregunta 4
        preguntas.add(new Pregunta(
                4,
                "¿Cuál es mi otome favorito'?",
                List.of("Mystic Messenger", "LoveUnholy", "DokiDoki Literature Club!", "Uno donde viajo en el tiempo y termino enamorada del abuelo de mi ex"),
                1
        ));

        // Pregunta 5
        preguntas.add(new Pregunta(
                5,
                "¿Cuál es el país más pequeño del mundo?",
                List.of("Perú", "Ecuador", "Zambia", "El Vaticano"),
                1
        ));

        // Pregunta 6
        preguntas.add(new Pregunta(
                6,
                "¿Cuál es el elemento químico con símbolo 'O'?",
                List.of("Oro", "Oxígeno", "Osmio", "Oganesón"),
                1
        ));

        // Pregunta 7
        preguntas.add(new Pregunta(
                7,
                "¿En qué año terminó la Segunda Guerra Mundial?",
                List.of("1943", "1945", "1947", "1950"),
                1
        ));

        // Pregunta 8
        preguntas.add(new Pregunta(
                8,
                "¿Cuál es la montaña más alta del mundo?",
                List.of("Monte Everest", "K2", "Monte Kilimanjaro", "Monte Huascarán"),
                1
        ));

        // Pregunta 9
        preguntas.add(new Pregunta(
                9,
                "¿Quién pintó La Mona Lisa?",
                List.of("Pablo Picasso", "Vincent Van Gogh", "Leonardo da Vinci", "Salvador Dalí"),
                1
        ));

        // Pregunta 10
        preguntas.add(new Pregunta(
                10,
                "¿Qué país tiene la mayor cantidad de islas en el mundo?",
                List.of("EEUU", "Suecia", "Suiza", "Austria"),
                1
        ));

        return preguntas;
    }
}
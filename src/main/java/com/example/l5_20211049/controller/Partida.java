package com.example.l5_20211049.controller;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Partida {
    private LocalDateTime fecha;
    private Integer puntajeTotal;
    private Integer respuestasCorrectas;
    private List<Pregunta> preguntas;
    private Boolean ganador;

    public Partida(List<Pregunta> preguntas) {
        this.fecha = LocalDateTime.now();
        this.preguntas = preguntas;
        this.respuestasCorrectas = calcularRespuestasCorrectas();
        this.puntajeTotal = respuestasCorrectas * 20;
        this.ganador = puntajeTotal >= 60;
    }

    private Integer calcularRespuestasCorrectas() {
        return (int) preguntas.stream().filter(Pregunta::esCorrecta).count();
    }
}
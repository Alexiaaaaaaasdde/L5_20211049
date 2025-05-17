package com.example.l5_20211049.controller;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Pregunta {
    private Integer id;
    private String enunciado;
    private List<String> opciones;
    private Integer respuestaCorrecta;
    private Integer respuestaUsuario;

    public Pregunta(Integer id, String enunciado, List<String> opciones, Integer respuestaCorrecta) {
        this.id = id;
        this.enunciado = enunciado;
        this.opciones = opciones;
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public boolean esCorrecta() {
        return respuestaUsuario != null && respuestaUsuario.equals(respuestaCorrecta);
    }
}
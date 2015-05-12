/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.LinkedList;

/**
 *
 * @author Sebastian
 */
public class PreguntaMultiple {
    private String pregunta;
    private LinkedList<String> respuestasCorrectas;
    private String respuestaCorrecta;
    private int puntos;

    public PreguntaMultiple() {
    }

    public PreguntaMultiple(String pregunta, LinkedList<String> respuestasCorrectas, String respuestaCorrecta, int puntos) {
        this.pregunta = pregunta;
        this.respuestasCorrectas = respuestasCorrectas;
        this.respuestaCorrecta = respuestaCorrecta;
        this.puntos = puntos;
    }
    
    
    
}

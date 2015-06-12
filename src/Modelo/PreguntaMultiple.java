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
    private LinkedList<String> respuestas;
    private String respuestaCorrecta;
    private int puntos;
    private int id;

    public PreguntaMultiple() {
    }

    public PreguntaMultiple(String pregunta, LinkedList<String> respuestas, String respuestaCorrecta, int puntos) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
        this.respuestaCorrecta = respuestaCorrecta;
        this.puntos = puntos;
    }

    public PreguntaMultiple(String pregunta, String a, String b, String c, String d, String respuestaCorrecta, int puntos, int id) {
        this.id = id;
        this.pregunta = pregunta;
        this.respuestaCorrecta = respuestaCorrecta;
        respuestas = new LinkedList<>();
        respuestas.add(a);
        respuestas.add(b);
        respuestas.add(c);
        respuestas.add(d);
        this.puntos = puntos;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public LinkedList<String> getRespuestas() {
        return respuestas;
    }

    public void setRespuestas(LinkedList<String> respuestas) {
        this.respuestas = respuestas;
    }

    public String getRespuestaCorrecta() {
        return respuestaCorrecta;
    }

    public void setRespuestaCorrecta(String respuestaCorrecta) {
        this.respuestaCorrecta = respuestaCorrecta;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    

}

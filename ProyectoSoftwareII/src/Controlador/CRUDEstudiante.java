/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estudiante;

/**
 *
 * @author Sebastian
 */
public class CRUDEstudiante {

    Estudiante estudiante;
    
//SOLO ENSAYO
    public CRUDEstudiante() {
        
        estudiante = new Estudiante();
    }

    public boolean agregarPuntos(String codigo, int puntos) {
        String sql = "Select function agregarpuntos("+codigo+","+puntos+")";
        return true;
    }

    public int consultarPuntos() {

        return 0;
    }

    public Estudiante consultarEstudiantes() {
        return estudiante;
    }

}

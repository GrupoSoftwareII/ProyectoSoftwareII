/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estudiante;

/**
 *buenas
 * @author Sebastian
 */
public class CRUDEstudiante {

    Estudiante estudiante;

    public CRUDEstudiante() {
        estudiante = new Estudiante();
    }

    public boolean agregarPuntos(String codigo, int puntos) {
        String sql = "Select function agregarpuntos("+codigo+","+puntos+")";
        return true;
    }


    public Estudiante loguearEstudiantes(String usuario, String pass) {
        Estudiante e=null;        
        String sql = "Select function loguin("+usuario+","+pass+")";
        
return e;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estudiante;
import Utilidades.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * buenas
 *
 * @author Sebastian
 */
public class CRUDEstudiante {

    ConexionBD cbd;
    Statement sentencia = null;
    Connection conn = null;
    ResultSet resultado = null;

    public CRUDEstudiante() {
        cbd = new ConexionBD();
        try {
            cbd.conectar();
        } catch (ClassNotFoundException ex) {
            System.out.println("no se pudo establecer la conexion");
        }
    }

    public boolean agregarPuntos(String codigo, int puntos) {
        String sql = "Select function agregarpuntos(" + codigo + "," + puntos + ")";
        return true;
    }

    public Estudiante loguearEstudiantes(String usuario, String pass) {
        Estudiante e = null;
        try {
            sentencia = cbd.getConn().createStatement();
            String sql = "Select * from estudiante where codigo='" + usuario + "';";
            resultado = sentencia.executeQuery(sql);
            System.out.println("baseeeeee");
            while (resultado.next()) {
                System.out.println("baseeee2"+resultado.getString("pass"));
                if (pass.equals(resultado.getString("pass"))) {
                    System.out.println("iguaes");
                    e = new Estudiante(resultado.getString("codigo"), Integer.parseInt(resultado.getString("puntos")), resultado.getString("nombre"), resultado.getString("apellido1"));
                }
            }

            cbd.getConn().close();

        } catch (SQLException ex) {
            System.out.println("no se pudo loguear");
        }
        
        return e;
    }
    
    public void actualizarPuntos(String puntos, String codEstduainte){
    try {
            sentencia = cbd.getConn().createStatement();
            String sql = "update estudiante set puntos ="+puntos+ " where codigo = '" + codEstduainte + "';";
            resultado = sentencia.executeQuery(sql);
            while (resultado.next()) {
                
            }

            cbd.getConn().close();

        } catch (SQLException ex) {
            System.out.println("no se pudo loguear");
        }
    
    }

}

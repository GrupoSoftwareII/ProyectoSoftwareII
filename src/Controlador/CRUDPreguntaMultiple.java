/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Estudiante;
import Modelo.PreguntaMultiple;
import Utilidades.ConexionBD;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Sebastian
 */
public class CRUDPreguntaMultiple {

    ConexionBD cbd;
    PreguntaMultiple pgm;
    Statement sentencia = null;
    Connection conn = null;
    ResultSet resultado = null;

    public CRUDPreguntaMultiple() {
        cbd = new ConexionBD();
        try {
            cbd.conectar();
        } catch (ClassNotFoundException ex) {
            System.out.println("no se pudo establecer la conexion");
        }
    }

    public PreguntaMultiple solicitarPregunta(String codigo) {
        PreguntaMultiple p = null;
        try {
            try {
                cbd.conectar();
            } catch (ClassNotFoundException ex) {
                System.out.println("no se pudo establecer la conexion");
            }
            System.out.println("mas arriba en la consulta...");
            sentencia = cbd.getConn().createStatement();
            System.out.println("Se realizo la conn");
            String sql = "select * from pregunta where id <> all(select id_pregunta from historial where id_estudiante=" + Integer.parseInt(codigo) + ");";
            resultado = sentencia.executeQuery(sql);
            System.out.println("resultadooooooo" + resultado.getRow());
            while (resultado.next()) {
                System.out.println("Eentre a la consulta  " + resultado.getString("pregunta"));
                p = new PreguntaMultiple(resultado.getString("pregunta"), resultado.getString("a"), resultado.getString("b"), resultado.getString("c"), resultado.getString("d"), resultado.getString("respuesta"), Integer.parseInt(resultado.getString("puntos")), Integer.parseInt(resultado.getString("id")));
            }

            cbd.getConn().close();

        } catch (SQLException ex) {
            System.out.println("no se pudo loguear" + ex.getMessage());
        }
        return p;
    }

}

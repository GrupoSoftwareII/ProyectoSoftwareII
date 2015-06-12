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

/**
 *
 * @author Santitigaga
 */
public class CRUDHistorial {
    
    ConexionBD cbd;
    Statement sentencia = null;
    Connection conn = null;
    ResultSet resultado = null;

    public CRUDHistorial() {
        cbd = new ConexionBD();
        try {
            cbd.conectar();
        } catch (ClassNotFoundException ex) {
            System.out.println("no se pudo establecer la conexion");
        }
    }
    
    public void guardarRespuesta(String respuesta, String codestudiante, String idpregunta){
    try {
        System.out.println("base de datos historial");
            sentencia = cbd.getConn().createStatement();
            String sql = "insert into historial(respuesta,id_pregunta,id_estudiante) values ('"+respuesta+"',"+Integer.parseInt(idpregunta)+","+Integer.parseInt(codestudiante)+");";
            System.out.println("+***"+sql);
            resultado = sentencia.executeQuery(sql);
            System.out.println("baseeeeeehistorial");
            while (resultado.next()) {
                System.out.println("baseeee2"+resultado.getString("pass"));
                
            }

            cbd.getConn().close();

        } catch (SQLException ex) {
            System.out.println("no se pudo loguear");
        }
    }
    
}

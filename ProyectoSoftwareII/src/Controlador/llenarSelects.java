/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JComboBox;

/**
 *
 * @author andres2288
 */
public class llenarSelects {

    public String llenarArea(JComboBox box, Connection conexion, Statement sentencia) {
        box.removeAllItems();
        box.addItem("Ninguno");
        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL = "SELECT * FROM \"Area\" ";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {

                box.addItem(resultado.getString("nombre"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }

    public String llenarGrupo(JComboBox box, Connection conexion, Statement sentencia) {
        box.removeAllItems();
        box.addItem("Ninguno");
        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL = "SELECT * FROM \"Estudiante\" ";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {

                box.addItem(resultado.getString("grupo"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }

    public String llenarEstudiante(JComboBox box, Connection conexion, Statement sentencia) {
        box.removeAllItems();
        box.addItem("Ninguno");

        String datos = "";
        ResultSet resultado = null;
        try {
            Class.forName("org.postgresql.Driver");
            String consultaSQL = "SELECT * FROM \"Estudiante\" ";
            resultado = sentencia.executeQuery(consultaSQL);
            while (resultado.next()) {

                box.addItem(resultado.getString("codigo"));

            }
        } catch (Exception e) {
            e.printStackTrace();
            conexion = null;
        } finally {
            if (resultado != null) {
                try {
                    resultado.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (sentencia != null) {
                try {
                    //   sentencia.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conexion != null) {
                try {
                    //   conexion.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return datos;
    }

}

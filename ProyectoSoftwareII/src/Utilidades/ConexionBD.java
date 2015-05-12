/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Sebastian
 */
public class ConexionBD {

    String driver = "org.postgresql.Driver"; // el nombre de nuestro driver Postgres. 
    String connectString = "jdbc:postgresql://localhost:5432/PruebasColegioBD"; // llamamos nuestra bd 
    String user = "postgres"; // usuario postgres 
    String password = "admin"; // no tiene password nuestra bd. 
    Statement sentencia = null;
    Connection conn = null;
    ResultSet resultado = null;

    public void conectar() throws ClassNotFoundException {
        try {
            Class.forName(driver);
            //Hacemos la coneccion. 
            conn = DriverManager.getConnection(connectString, user, password);
            //Si la conexion fue realizada con exito, muestra el sgte mensaje. 
            System.out.println("Conexion a la base de datos chat realizada con exito! ");
            //Cerramos la conexion 
        } //Si se produce una Excepcion y no nos podemos conectar, muestra el sgte. mensaje. 
        catch (SQLException e) {
            System.out.println("Se ha producido un error en la conexion a la base de datos chat! " + e.getMessage());
        }

    }

    public boolean insertar(String nombre, String usuario, String pass) throws SQLException {
        boolean b = false;
        try {
            System.out.println("::::P:");
            sentencia = conn.createStatement();
            String insertSQL = "insert into tb_usuario (nombre, usuario, clave) values('" + nombre + "','" + usuario + "','" + pass + "')";
            resultado = sentencia.executeQuery(insertSQL);
            conn.close();
        } catch (Exception e) {
            if (e.getMessage().contains("llave duplicada")) {
                conn.close();
            } else {
                conn.close();
                b = true;
            }

        }
        return b;
    }

    public boolean consultar(String usuario, String pass) throws SQLException {
        boolean b = false;
        try {
            sentencia = conn.createStatement();
            String consultarSQL = "select clave from tb_usuario where usuario = '" + usuario + "'";
            resultado = sentencia.executeQuery(consultarSQL);
            while (resultado.next()) {
                if (pass.equals(resultado.getString(1))) {
                    b = true;
                }
            }
            conn.close();
        } catch (Exception e) {
            System.out.println("Error al conectar... login" + e.getMessage());
        }
        return b;
    }

    public void insertarMensaje(String nombre, String mensaje) throws SQLException {
        boolean b = false;
        try {
            System.out.println("::::P:");
            sentencia = conn.createStatement();
            String insertSQL = "insert into tb_historial (usuario, mensaje) values('" + nombre + "','" + mensaje + "')";
            resultado = sentencia.executeQuery(insertSQL);
            conn.close();
        } catch (Exception e) {
            if (e.getMessage().contains("llave duplicada")) {
                conn.close();
            } else {
                conn.close();
                b = true;
            }

        }
        if(!b){
            JOptionPane.showMessageDialog(null, "Mensaje no enviado");
        }
    }

}

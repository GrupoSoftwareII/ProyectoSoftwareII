/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Modulo 2
 */
public class Estudiante implements Runnable {
    
    private String codigo;
    private String grupo;
    private int puntos;
    private String nombre;
    private String apellido;
    private String documento;
    DataInputStream dis;
    DataOutputStream dos;
    
    public Estudiante() {
    }
    
    public Estudiante(String codigo, String grupo, int puntos, String nombre, String apellido, String documento) {
        this.codigo = codigo;
        this.grupo = grupo;
        this.puntos = puntos;
        this.nombre = nombre;
        this.apellido = apellido;
        this.documento = documento;
    }
    
    public String getCodigo() {
        return codigo;
    }
    
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    
    public String getGrupo() {
        return grupo;
    }
    
    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public String getApellido() {
        return apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    
    public String getDocumento() {
        return documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    public void enviarRespuesta(String respuesta) {
        try {
            dos.writeUTF("respuesta" + ";" + respuesta);
        } catch (IOException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    @Override
    public void run() {
        
        leer();
        
    }
    
    public void leer() {
        try {
            while (true) {
                String respuesta = dis.readUTF();
                manejador(respuesta);
            }
        } catch (IOException ex) {
            System.out.println("se desconecto el estudiante");
            
        }
    }
    
    public void manejador(String evento) {
        String[] datos = evento.split(";");
        if (datos[0].equals("conectado")) {
            this.setCodigo(datos[1]);
            this.setPuntos(Integer.parseInt(datos[2]));
        } else if (datos[0].equals("desconectado")) {
            
        }
        
    }
    
    public void conectar(String usuario, String pass) {
        try {
            dos.writeUTF("conectar;" + usuario + ";" + pass);
        } catch (IOException ex) {
            Logger.getLogger(Estudiante.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}

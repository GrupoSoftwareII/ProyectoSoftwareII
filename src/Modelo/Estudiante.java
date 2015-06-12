/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Modulo 2
 */
public class Estudiante implements Runnable {

    private String codigo;
    private boolean conectado;
    private int puntos;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String documento;
    DataInputStream dis;
    DataOutputStream dos;
    private int puerto;
    private String ip;
    Socket socket;

    public Estudiante() {
        puerto = 1399;
        ip = "127.0.0.1";
        configurar();
    }

    public Estudiante(String codigo, int puntos, String nombre, String apellido1, String apellido2) {
        this.codigo = codigo;
        this.puntos = puntos;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        conectado=false;
    }
    

    public void configurar() {
        try {
            socket = new Socket(ip, puerto);
            dis = new DataInputStream(socket.getInputStream());
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Errorr: " + ex.getMessage());
        }

    }

    public Estudiante(String codigo, int puntos, String nombre, String apellido) {
        this.codigo = codigo;
        this.puntos = puntos;
        this.nombre = nombre;
        puerto = 1399;
        ip = "127.0.0.1";
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public boolean isConectado() {
        return conectado;
    }

    public void setConectado(boolean conectado) {
        this.conectado = conectado;
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
            this.conectado=true;
        } else if (datos[0].equals("nologin")) {
            System.out.println("no loguin--------");
            this.conectado=false;
            
        }
        

    }

    public void conectar(String usuario, String pass) {
        try {
            System.out.println("usu: "+usuario+ " pas: "+pass);
            dos.writeUTF("conectar;" + usuario + ";" + pass);
            String res=dis.readUTF();
            manejador(res);
            
        } catch (IOException ex) {
            System.out.println("Error al conectar: "+ex.getMessage()+ " en: "+ex.getLocalizedMessage());
        }

    }

}

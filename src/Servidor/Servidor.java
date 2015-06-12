/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Controlador.CRUDEstudiante;
import Controlador.CRUDHistorial;
import Controlador.CRUDPreguntaMultiple;
import Modelo.Estudiante;
import Modelo.PreguntaMultiple;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Santitigaga
 */
public class Servidor implements Runnable {
    
    CRUDEstudiante bdEstudiante;
    CRUDPreguntaMultiple bdPregunta;
    CRUDHistorial bdHistorial;
    ServerSocket ss;
    DataOutputStream dos;
    LinkedList<Ayudante> listaAyudantes;
    
    public Servidor() {
        configurar();
        bdPregunta = new CRUDPreguntaMultiple();
        listaAyudantes = new LinkedList<Ayudante>();
    }
    
    public ServerSocket getSs() {
        return ss;
    }
    
    public void setSs(ServerSocket ss) {
        this.ss = ss;
    }
    
    public DataOutputStream getDos() {
        return dos;
    }
    
    public void setDos(DataOutputStream dos) {
        this.dos = dos;
    }
    
    public LinkedList<Ayudante> getListaAyudantes() {
        return listaAyudantes;
    }
    
    public void setListaAyudantes(LinkedList<Ayudante> listaAyudantes) {
        this.listaAyudantes = listaAyudantes;
    }
    
    public void estudianteRespuesta(String respuesta, String idEstudiante, String idPregunta) {
        
    }
    
    public void configurar() {
        try {
            this.ss = new ServerSocket(1399);
        } catch (IOException ex) {
            System.out.println("n se pudo configurar el server socket");
        }
    }
    
    public boolean existe() {
        int cont = 0;
        String x = listaAyudantes.getLast().codigo;
        for (int i = 0; i < listaAyudantes.size(); i++) {
            System.out.println("listaaa" + listaAyudantes.get(i).codigo);
            if (listaAyudantes.get(i).codigo.equals(x)) {
                cont++;
            }
        }
        if (cont > 1) {
            return true;
        } else {
            return false;
        }
        
    }
    
    public void loguearUsuario(String[] datos, String codigo) {
        bdEstudiante = new CRUDEstudiante();
        Estudiante e = bdEstudiante.loguearEstudiantes(datos[1], datos[2]);
        if (e != null) {
            for (int i = 0; i < listaAyudantes.size(); i++) {
                System.out.println("listaaa1111" + listaAyudantes.get(i).codigo + "size" + listaAyudantes.size());
            }
            try {
                if (existe()) {
                    dos = new DataOutputStream(listaAyudantes.getLast().socket.getOutputStream());
                    dos.writeUTF("existe");
                    listaAyudantes.getLast().desconectar();
                    listaAyudantes.removeLast();
                } else {
                    for (int i = 0; i < listaAyudantes.size(); i++) {
                        if (listaAyudantes.get(i).codigo.equals(codigo)) {
                            dos = new DataOutputStream(listaAyudantes.get(i).socket.getOutputStream());
                            dos.writeUTF("conectado;" + e.getCodigo() + ";" + e.getPuntos() + ";" + e.getNombre() + ";" + e.getApellido1());
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("no se pudo enviar el mensaje de conectado");
            }
        } else {
            try {
                for (int i = 0; i < listaAyudantes.size(); i++) {
                    if (listaAyudantes.get(i).codigo.equals(codigo)) {
                        dos = new DataOutputStream(listaAyudantes.get(i).socket.getOutputStream());
                        dos.writeUTF("nologin");
                        listaAyudantes.get(i).desconectar();
                        listaAyudantes.remove(i);
                        
                    }
                }
            } catch (IOException ex) {
                System.out.println("no se pudo enviar el mensaje de que no se pudo conectar");
            }
        }
    }
    
    public void solicitarPregunta(String codigo) {
        PreguntaMultiple pm = bdPregunta.solicitarPregunta(codigo);
        if (pm != null) {
            System.out.println("Pregunta <> null");
            for (int i = 0; i < listaAyudantes.size(); i++) {
                if (listaAyudantes.get(i).codigo.equals(codigo)) {
                    try {
                        System.out.println("entrando al if " + codigo);
                        dos = new DataOutputStream(listaAyudantes.get(i).socket.getOutputStream());
                        dos.writeUTF("pregunta;" + pm.getPregunta() + ";" + pm.getRespuestas().get(0) + ";" + pm.getRespuestas().get(1) + ";" + pm.getRespuestas().get(2) + ";" + pm.getRespuestas().get(3) + ";" + pm.getRespuestaCorrecta() + ";" + pm.getId());
                    } catch (IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
            
        } else {
            for (int i = 0; i < listaAyudantes.size(); i++) {
                if (listaAyudantes.get(i).codigo.equals(codigo)) {
                    try {
                        dos = new DataOutputStream(listaAyudantes.get(i).socket.getOutputStream());
                        dos.writeUTF("nopregunta;");
                    } catch (IOException ex) {
                        Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
            }
        }
        
    }
    
    public void guardarRespuesta(String respuesta, String codestudiante, String idpregunta) {
        System.out.println("llegando al guardar respuesta servidor");
        bdHistorial=new CRUDHistorial();
        bdHistorial.guardarRespuesta(respuesta, codestudiante, idpregunta);
       
    }
    
   public void actualizarPuntos(String puntos, String codEstduainte){   
   bdEstudiante=new CRUDEstudiante();
   bdEstudiante.actualizarPuntos(puntos,codEstduainte);
   
   }
    
    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = ss.accept();
                Ayudante a = new Ayudante(socket, this);
                Thread hiloAyudante = new Thread(a);
                hiloAyudante.start();
                System.out.println("listaadd--------------------------------------------------------------------------");
                listaAyudantes.add(a);
                
            } catch (IOException ex) {
                System.out.println("no se pudo crear el ayudante");
            }
        }
    }
    
    public static void main(String[] args) {
        Servidor s = new Servidor();
        Thread hilo = new Thread(s);
        hilo.start();
    }
}

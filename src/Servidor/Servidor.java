/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import Controlador.CRUDEstudiante;
import Controlador.CRUDPreguntaMultiple;
import Modelo.Estudiante;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santitigaga
 */
public class Servidor implements Runnable {

    CRUDEstudiante bdEstudiante;
    CRUDPreguntaMultiple bdPregunta;
    ServerSocket ss;
    DataOutputStream dos;
    LinkedList<Ayudante> listaAyudantes;

    public Servidor() {
        configurar();
        bdEstudiante = new CRUDEstudiante();
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

    public void loguearUsuario(String[] datos, int id) {
        Estudiante e = bdEstudiante.loguearEstudiantes(datos[1], datos[2]);
        if (e != null) {
            try {
                System.out.println("quiero conectar-------" + "conectado;" + e.getCodigo() + ";" + e.getPuntos());
                for (int i = 0; i < listaAyudantes.size(); i++) {
                    if (listaAyudantes.get(i).id == id) {
                        dos = new DataOutputStream(listaAyudantes.get(i).socket.getOutputStream());
                        dos.writeUTF("conectado;" + e.getCodigo() + ";" + e.getPuntos());
                    }
                }

            } catch (IOException ex) {
                System.out.println("no se pudo enviar el mensaje de conectado");
            }
        } else {
            try {
                System.out.println("entre a nulo");
                for (int i = 0; i < listaAyudantes.size(); i++) {
                    System.out.println("lista: " + listaAyudantes.size());
                    if (listaAyudantes.get(i).id == id) {
                        dos = new DataOutputStream(listaAyudantes.get(i).socket.getOutputStream());
                        dos.writeUTF("nologin");
                    }
                    listaAyudantes.get(i).desconectar();
                    listaAyudantes.remove(i);
                }
            } catch (IOException ex) {
                System.out.println("no se pudo enviar el mensaje de que no se pudo conectar");
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = ss.accept();
                Ayudante a = new Ayudante(socket, this, listaAyudantes.size());
                Thread hiloAyudante = new Thread(a);
                hiloAyudante.start();
                System.out.println("tamano. "+listaAyudantes.size());
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

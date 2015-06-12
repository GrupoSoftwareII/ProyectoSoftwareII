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

    @Override
    public void run() {
        while (true) {
            try {
                Socket socket = ss.accept();
                Ayudante a = new Ayudante(socket, this);
                listaAyudantes.add(a);

            } catch (IOException ex) {
                System.out.println("no se pudo crear el ayudante");
            }
        }
    }

    public void loguearUsuario(String[] datos) {
      Estudiante e= bdEstudiante.loguearEstudiantes(datos[1], datos[2]);
        if (e!=null) {
            try {
                dos.writeUTF("conectado;"+e.getCodigo()+";"+e.getPuntos());
            } catch (IOException ex) {
                System.out.println("no se pudo enviar el mensaje de conectado");
            }
        } else {
            try {
                dos.writeUTF("desconectado;");
            } catch (IOException ex) {
                System.out.println("no se pudo enviar el mensaje de que no se pudo conectar");
            }
        }
    }
}

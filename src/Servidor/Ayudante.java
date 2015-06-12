/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Santitigaga
 */
public class Ayudante implements Runnable {

    Socket socket;
    DataInputStream dis;
    DataOutputStream dos;
    Servidor servidor;
    int id;

    public Ayudante(Socket socket, Servidor servidor, int id) {
        try {
            this.id = id;
            this.socket = socket;
            this.servidor = servidor;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Ayudante.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        if (datos[0].equals("conectar")) {
            servidor.loguearUsuario(datos, id);
        } else if (datos[0].equals("respuesta")) {

        }

    }

    public void desconectar() {
        this.socket = null;
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        leer();
    }

}

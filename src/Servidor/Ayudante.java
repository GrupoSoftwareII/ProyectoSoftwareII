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
    String codigo;

    public Ayudante(Socket socket, Servidor servidor) {
        try {
            this.socket = socket;
            this.servidor = servidor;
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(Ayudante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void manejador(String evento) {
        String[] datos = evento.split(";");
        if (datos[0].equals("conectar")) {
            this.codigo = datos[1];
            servidor.loguearUsuario(datos, datos[1]);
        } else if (datos[0].equals("pregunta")) {
            System.out.println("Llegue al ayudante Preguntaaaaaaaaa");
            servidor.solicitarPregunta(this.codigo);
        } else if (datos[0].equals("respuesta")) {
            System.out.println("guardar respuestaa yudante" + datos[3]);
            servidor.guardarRespuesta(datos[1], this.codigo, datos[3]);
        } else if (datos[0].equalsIgnoreCase("puntos")) {
            servidor.actualizarPuntos(datos[1], this.codigo);
        }

    }

    public void desconectar() {
        this.socket = null;
        Thread.currentThread().interrupt();
    }

    @Override
    public void run() {
        try {
            while (true) {
                String respuesta = dis.readUTF();
                manejador(respuesta);
            }
        } catch (IOException ex) {
            System.out.println("se desconecto el estudiante");

        }
    }

}
